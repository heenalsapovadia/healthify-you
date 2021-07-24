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
        int prescriptionId = prescription.getPrescription_id();


        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);

        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.REQUEST_MEDICATION + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println("Enter Prescription ID:");
        int current_PrescriptionId = sc.nextInt();

        System.out.println("Patient Name = " + Patient.getPatient().getPatientName());
        System.out.println("\n" );
        System.out.println("Prescribed List of above patient is below:" );

        List<Prescription> prescriptions = requestMedication.getPrescriptionDetails(current_PrescriptionId);
        double finalAmountForPayment = 0.0;
        ArrayList<MedicationsToUpdate> medicationsToUpdate = new ArrayList<>();
        for (Prescription currentPrescription : prescriptions) {
            System.out.println("Medicine Name: " + currentPrescription.getMedicine_name());
            int totalDoseNeeded = currentPrescription.getMorning() + currentPrescription.getAfternoon() + currentPrescription.getEvening();
            System.out.println("Medicine Dose: " + totalDoseNeeded);
            int medicinePrescirbedDays = currentPrescription.getDosage_days();
            System.out.println("Dosage is for : " + medicinePrescirbedDays + " days");
            int finalDoseAmount = totalDoseNeeded * medicinePrescirbedDays;

            PharmaInvoice invoice = requestMedication.getPharmaInvoice(currentPrescription.getMedicine_name());
            if (invoice == null) {
                System.out.println("Medicine not found in Pharmacy. Unable to proceed.");
                System.out.println("\n");
                continue;
            }
            int itemQuantityAvailable = invoice.getItemUpdatedQuantity();
            double unitPrice = invoice.getItemUnitPrice();

            // enough quantity
            if (finalDoseAmount < itemQuantityAvailable) {
                int itemLeft = itemQuantityAvailable - finalDoseAmount;
                if (itemLeft < 0) {
                    itemLeft = 0;
                }
                double totalPrice = unitPrice  * totalDoseNeeded * medicinePrescirbedDays;
                System.out.println("Total dose to add to payment - " + finalDoseAmount);
                System.out.println("Enough quantity of medicine availble in inventory.");//"  - Number left in inventory after after this prescription " + itemLeft);
                System.out.println("Total cost of " + currentPrescription.getMedicine_name() + " is " + totalPrice);
                System.out.println("\n");

                if (totalPrice == 0.0) {
                    System.out.println("Checkout amount is not eligible for payment.");
                    System.out.println("\n");
                } else {
                    finalAmountForPayment += totalPrice;
                    medicationsToUpdate.add(new MedicationsToUpdate(currentPrescription.getMedicine_name(), itemLeft));
                }
            }
            else {
                System.out.println("Not enough dose available in inventory. Unable to add to checkout amount.");
            }
        }
        System.out.println("Total medicine checkout amount - " + finalAmountForPayment);
        System.out.println("\n");
        if (finalAmountForPayment > 0) {
            PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
            int billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.M, finalAmountForPayment, "");
            if (billingId != 0) {
                for (MedicationsToUpdate medicationToUpdate: medicationsToUpdate) {
                    requestMedication.updatePharmaInvoice(medicationToUpdate.medicationName, medicationToUpdate.medicationLeft);
                }
            }
        }

        return null;
    }
}