package persistence.patient.dao;

import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import java.util.List;
/**
 * <pre>
 * Blood Bank Service Database Interface - inserts and fetches data from blood_donation table
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface BloodBankServiceDAO {
    // below method insert all the details in the blood donation table
    void insertBloodBankServiceDetails(BloodBankService bloodBankService);

    // below method gets all the details from blood donation table and checks eligibility
    List<BloodBankService> getAllBloodDonationsForPatient(Patient patient);
}
