package persistence.patient.dao;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;

import java.util.List;

public interface BloodBankServiceDAO {
    void insertBloodBankServiceDetails(BloodBankService bloodBankService);

    List<BloodBankService> getAllBloodDonationsForPatient(Patient patient);

}