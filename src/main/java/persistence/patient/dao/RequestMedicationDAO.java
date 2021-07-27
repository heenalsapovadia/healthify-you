package persistence.patient.dao;

import persistence.admin.model.PharmaInvoice;
import persistence.doctor.model.Prescription;
import java.util.List;
/**
 * <pre>
 * Request Medication DAO Interface - fetches data from prescription, pharma_invoice and processes
 * </pre>
 * @author Saloni Raythatha
 *
 */
public interface RequestMedicationDAO {

    // get prescription details(prescription id, prescription name, morning, evening, afternoon dose, dosage days) from prescription table
    public List<Prescription> getPrescriptionDetails(int prescriptionId);

    //get medicine stock, charges details from pharma_charges
    public PharmaInvoice getPharmaInvoice(String medicationName);

    // updating the stock in pharmacy supplies table based on successful billing from the user
    public void updatePharmaInvoice(String medication, int remainingQuantity);

    // updating prescription based on successful purchase
    public void updatePrescription(int prescription_id, int billing_id);
}
