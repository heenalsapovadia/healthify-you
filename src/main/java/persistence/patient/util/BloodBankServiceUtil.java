package persistence.patient.util;

import persistence.doctor.model.Appointment;

import java.security.SecureRandom;

public interface BloodBankServiceUtil {

    // to check if current donation date and previous donation date has minimum 6 months
    public boolean validateDate(boolean b);

    // to check if the report of the patient is normal
    public String validatePatientReport(String s);

    //public String getRandomStringForDonationId();
    // above method for generating token
}
