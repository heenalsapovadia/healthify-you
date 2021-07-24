package presentation.patient;

import persistence.admin.model.PharmaInvoice;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.Patient;
import presentation.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static presentation.common.ScreenTitles.viewMedicineStock;


class MedicationsToUpdate {
    String medicationName;
    int medicationLeft;
    public MedicationsToUpdate(String medicationName, int medicationLeft) {
        this.medicationName = medicationName;
        this.medicationLeft = medicationLeft;
    }
}

public class RequestMedicationOutput {

    public String requestMedicationDetails() {
        RequestMedicationDAOImpl requestMedication = new RequestMedicationDAOImpl();

            int patientId = Patient.getPatient().getPatientId();
            Prescription prescription =  new Prescription();
            int prescriptionId = prescription.getPrescriptionId();

            Scanner sc = new Scanner(System.in);
            for ( int i = 0; i < 100; i++ )
                System.out.print(CommonConstants.HEADING_CHAR);

            System.out.println();
            System.out.println(CommonConstants.TITLE_SPACE + ScreenTitles.REQUEST_MEDICATION + CommonConstants.TITLE_SPACE);
            for ( int i = 0; i < 100; i++ )
                System.out.print(CommonConstants.HEADING_CHAR);
            System.out.println();
            System.out.println("Enter Prescription ID:");
            int current_PrescriptionId = sc.nextInt();



        List<Prescription> prescriptions = requestMedication.getPrescriptionDetails(current_PrescriptionId);
        double finalAmountForPayment = 0.0;
        ArrayList<MedicationsToUpdate> medicationsToUpdate = new ArrayList<>();
        System.out.println("Current logged in Patient Name = " + Patient.getPatient().getPatientName());

        if (!prescriptions.isEmpty()) {
        for (Prescription currentPrescription : prescriptions) {

            System.out.println("\n" );
            System.out.println("Prescribed list of above prescription id is below:" );
            System.out.println("Medicine Name: " + currentPrescription.getMedicineName());
            int totalDoseNeeded = currentPrescription.getMorning() + currentPrescription.getAfternoon() + currentPrescription.getEvening();
            System.out.println("Medicine Dose: " + totalDoseNeeded);
            int medicinePrescirbedDays = currentPrescription.getDosageDays();
            System.out.println("Dosage is for : " + medicinePrescirbedDays + " days");
            int finalDoseAmount = totalDoseNeeded * medicinePrescirbedDays;

            PharmaInvoice invoice = requestMedication.getPharmaInvoice(currentPrescription.getMedicineName());

                int itemQuantityAvailable = invoice.getItemQuantity();
                double unitPrice = invoice.getItemUnitPrice();

                // enough quantity
                if (finalDoseAmount < itemQuantityAvailable) {
                    int itemLeft = itemQuantityAvailable - finalDoseAmount;
                    if (itemLeft < 0) {
                        itemLeft = 0;
                    }
                    double totalPrice = unitPrice * totalDoseNeeded * medicinePrescirbedDays;
                    System.out.println("Payment needed of amount " + totalPrice);
                    System.out.println("Enough Doses of quantity availble in Stock " + finalDoseAmount );
                    requestMedication.updatePharmaInvoice(currentPrescription.getMedicineName(), itemLeft);

                    if (totalPrice == 0.0) {
                        System.out.println("Checkout amount is not eligible for payment.");
                        System.out.println("\n");
                    } else {
                        finalAmountForPayment += totalPrice;
                        medicationsToUpdate.add(new MedicationsToUpdate(currentPrescription.getMedicineName(), itemLeft));
                    }
                } else {
                    System.out.println("Not enough dose available in inventory. Unable to add to checkout amount.");
                }

        }
        }
        else{
            System.out.println("This is Incorrect prescription id for logged in patient.");
        }
        //System.out.println("Total medicine checkout amount - " + finalAmountForPayment);
        System.out.println("\n");
        String voucherID;
        if (finalAmountForPayment > 0) {
            PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
            System.out.println("Total medicine checkout amount - " + finalAmountForPayment);
            int billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.M, finalAmountForPayment, " ");
            if (billingId != 0) {
                for (MedicationsToUpdate medicationToUpdate: medicationsToUpdate) {
                    requestMedication.updatePharmaInvoice(medicationToUpdate.medicationName, medicationToUpdate.medicationLeft);
                }
                requestMedication.updatePrescription(current_PrescriptionId, billingId);
            }

        }

        return null;
    }
}