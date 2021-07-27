package persistence.patient.utilImpl;

import persistence.admin.model.PharmaInvoice;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.RequestMedicationDetails;
import presentation.common.PaymentInterfaceOutput;
import persistence.patient.model.MedicationsToUpdate;
import java.util.ArrayList;

public class RequestMedicationUtilImpl {

    public void makePaymentForPrescriptionsWithAmount(double amount,
                                                      ArrayList<MedicationsToUpdate> medicationsToUpdate,
                                                      RequestMedicationDAOImpl requestMedication,
                                                      int current_PrescriptionId) {
        PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
        System.out.println("Total medicine checkout amount - " + amount);
        int billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.M, amount, " ");
        if (billingId != 0) {
            for (MedicationsToUpdate medicationToUpdate: medicationsToUpdate) {
                requestMedication.updatePharmaInvoice(medicationToUpdate.medicationName, medicationToUpdate.medicationLeft);
            }
            requestMedication.updatePrescription(current_PrescriptionId, billingId);
        }
    }

    public RequestMedicationDetails processPrescription(Prescription currentPrescription,
                                                        RequestMedicationDAOImpl requestMedication) {
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
                return new RequestMedicationDetails(totalPrice, itemLeft);
            }
        } else {
            System.out.println("Not enough dose available in inventory. Unable to add to checkout amount.");
        }
        return new RequestMedicationDetails(0.0, 0);
    }

}