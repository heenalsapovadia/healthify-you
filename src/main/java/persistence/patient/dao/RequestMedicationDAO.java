package persistence.patient.dao;

import persistence.doctor.model.Prescription;

import java.util.List;

public interface RequestMedicationDAO {
    // get prescription details from prescription table
    public List<Prescription> getPrescriptionDetails(int prescriptionId);

    //get medicine stock, charges details from pharma_charges or create new table
}
