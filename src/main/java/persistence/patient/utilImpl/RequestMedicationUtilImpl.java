package persistence.patient.utilImpl;

import persistence.admin.model.PharmaInvoice;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.doctor.model.Prescription;
import persistence.patient.dao.RequestMedicationDAO;
import persistence.patient.model.RequestMedicationModel.RequestMedicationDetails;
import persistence.patient.util.RequestMedicationUtil;
import presentation.common.PaymentInterfaceOutput;
import persistence.patient.model.RequestMedicationModel.MedicationsToUpdate;
import presentation.common.ScreenFields;
import java.util.ArrayList;
/**
 * <pre>
 * Request Medication class for implementing functionality for request medication..
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class RequestMedicationUtilImpl implements RequestMedicationUtil {

    public RequestMedicationDetails processPrescription(Prescription currentPrescription,
                                                        RequestMedicationDAO requestMedication) {
        System.out.println("\n" );
        System.out.println(ScreenFields.PRESCRIBED_LIST_OF_MEDICINE);
        System.out.println(ScreenFields.NAME_OF_MEDICINE+ currentPrescription.getMedicineName());
        int totalDoseNeeded = currentPrescription.getMorning() + currentPrescription.getAfternoon() + currentPrescription.getEvening();
        System.out.println(ScreenFields.DOSE_OF_MEDICINE+ totalDoseNeeded);
        int medicinePrescirbedDays = currentPrescription.getDosageDays();
        System.out.println(ScreenFields.MEDICINE_DOSAGE_DAYS  + medicinePrescirbedDays + ScreenFields.DAYS);
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
            System.out.println(ScreenFields.PAYMENT_NEEDED_FOR_PRESCRIPTION+ totalPrice);
            System.out.println(ScreenFields.TOTAL_DOSE + finalDoseAmount);
            System.out.println(ScreenFields.ENOUGH_DOSES_OF_MEDICINE_AVAILABLE);
            requestMedication.updatePharmaInvoice(currentPrescription.getMedicineName(), itemLeft);

            // if price of the medication somehow total to zerp than amount wont be eligible for payment..
            if (totalPrice == 0.0) {
                System.out.println(ScreenFields.CHECKOUT_AMOUNT_NOT_ELIGIBLE);
                System.out.println("\n");
            } else {
                return new RequestMedicationDetails(totalPrice, itemLeft);
            }
        } else {
            System.out.println(ScreenFields.NOT_ENOUGH_DOSES);
        }
        return new RequestMedicationDetails(0.0, 0);
    }

    public void makePaymentForPrescriptionsWithAmount(double amount,
                                                      ArrayList<MedicationsToUpdate> medicationsToUpdate,
                                                      RequestMedicationDAO requestMedication,
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
}
