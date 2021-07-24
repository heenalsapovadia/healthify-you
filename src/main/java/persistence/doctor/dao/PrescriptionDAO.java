package persistence.doctor.dao;

import persistence.doctor.model.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    void insertPrescription(List<Prescription> prescriptionList);

    int findMaxPrescriptionId();

    List<Prescription> getPrescriptionById(int prescriptionId);
    
    /**
     * <pre>
     * Fetches prescription by patient id.
     * 
     * Returns a list of prescriptions.
     * </pre>
     * 
     * @return list
     */
    List<Prescription> getPrescriptionByPatientId();
}
