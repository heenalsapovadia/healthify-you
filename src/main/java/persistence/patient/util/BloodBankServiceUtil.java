package persistence.patient.util;

import persistence.doctor.model.Appointment;
import persistence.patient.daoImpl.BloodBankServiceDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;

import java.security.SecureRandom;
import java.util.List;

public interface BloodBankServiceUtil {


    // to check if the report of the patient is normal
    public String validatePatientReport(String s);

    // above method for generating token
    public String getRandomStringForDonationId();

    public String registerPatientForBloodDonation(BloodBankServiceDAOImpl bloodBankDatabase, Patient patient,String bloodGroupInput);

    public boolean validateSixMonthCheck();
}
