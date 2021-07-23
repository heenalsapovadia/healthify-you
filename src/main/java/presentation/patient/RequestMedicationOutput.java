package presentation.patient;

import persistence.admin.model.PharmaInvoice;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.Patient;
import presentation.common.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static presentation.common.ScreenTitles.viewMedicineStock;

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

            List<Prescription> prescriptions = requestMedication.getPrescriptionDetails(current_PrescriptionId);
            for (Prescription currentPrescription : prescriptions) {
                System.out.println("Medicine Name: " + currentPrescription.getMedicine_name());
                int totalDoseNeeded = currentPrescription.getMorning() + currentPrescription.getAfternoon() + currentPrescription.getEvening();
                System.out.println("Medicine Dose: " + totalDoseNeeded);

                PharmaInvoice invoice = requestMedication.getPharmaInvoice(currentPrescription.getMedicine_name());
                int itemQuantityAvailable = invoice.getItemQuantity();
                double unitPrice = invoice.getItemUnitPrice();

                // enough quantity
                if (totalDoseNeeded < itemQuantityAvailable) {
                    int itemLeft = itemQuantityAvailable - totalDoseNeeded;
                    if (itemLeft < 0) {
                        itemLeft = 0;
                    }
                    double totalPrice = unitPrice  * totalDoseNeeded;
                    System.out.println("Payment needed of amount " + totalPrice);
                    System.out.println("Enough Doses of quantity " + totalDoseNeeded + "  - Number left in inventory after after this prescription " + itemLeft);
                    requestMedication.updatePharmaInvoice(currentPrescription.getMedicine_name(), itemLeft);

                    PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
                    int billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.M, totalPrice,"");
                } else {
                    System.out.println("Not Enough Doses Available" + " Patient needed " + totalDoseNeeded + " Inventory has " + itemQuantityAvailable);
                }
            }
            return null;
        }
}



