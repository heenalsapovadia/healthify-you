package persistence.patient.utilImpl;

import persistence.patient.util.BloodBankServiceUtil;

import java.security.SecureRandom;

public class BloodBankServiceUtilImpl implements BloodBankServiceUtil {

    static final String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final int randomStringLength = 10;
    static SecureRandom rnd = new SecureRandom();

    public String getRandomStringForDonationId() {
        StringBuilder sb = new StringBuilder(randomStringLength);
        for ( int i =0 ; i < randomStringLength; i++ )
            sb.append(randomString.charAt(rnd.nextInt(randomString.length())));
        return sb.toString();
    }

    @Override
    public boolean validateDate(boolean b) {
        return false;
    }

    @Override
    public String validatePatientReport(String s) {
        return null;
    }

}
