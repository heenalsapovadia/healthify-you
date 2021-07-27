package persistence.patient.util;

import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
/**
 * <pre>
 * Interface for Blood Bank Service functionality .
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface BloodBankServiceUtil {

    // Random string generated for donation id when patient registers
    String getRandomStringForDonationId();

    // random token id generated for patient when registration is successful for blood donation
    String getTokenIdForDonation();

    // register logged in patient for blood donation.
    String registerPatientForBloodDonation(BloodBankServiceDAO bloodBankDatabase, Patient patient,String bloodGroupInput);

    // check if reports are normal for patients if exists any... else patient wont be able to register for donation.
    boolean checkIfReportsAreNormalForDonation();

    // validation if patient's previous donation record is more than 6 months before they would be able to register.
    boolean validateIfPreviousDonationMoreThanSixMonth(BloodBankService donations);
}

