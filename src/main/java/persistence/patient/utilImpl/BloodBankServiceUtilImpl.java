package persistence.patient.utilImpl;

import persistence.patient.daoImpl.BloodBankServiceDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.BloodTestReport;
import persistence.patient.model.Patient;
import persistence.patient.util.BloodBankServiceUtil;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

public class BloodBankServiceUtilImpl implements BloodBankServiceUtil {

    static final String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final int randomStringLength = 10;
    static SecureRandom rnd = new SecureRandom();

    public String getRandomStringForDonationId() {
        StringBuilder sb = new StringBuilder(randomStringLength);
        for ( int i = 0; i < randomStringLength; i++ )
            sb.append(randomString.charAt(rnd.nextInt(randomString.length())));
        return sb.toString();
    }

    public String getTokenIdForDonation() {
        String tokenString = "0123456789";
        final int tokenStringLength = 5;
        StringBuilder sb = new StringBuilder(randomStringLength);
        for ( int i = 0; i < tokenStringLength; i++ )
            sb.append(tokenString.charAt(rnd.nextInt(tokenString.length())));
        return sb.toString();
    }

    @Override
    public String validatePatientReport(String s) {
        return null;
    }

    public String registerPatientForBloodDonation(BloodBankServiceDAOImpl bloodBankDatabase, Patient patient, String bloodGroupInput) {
        BloodBankService bbservice = new BloodBankService();
        BloodBankServiceUtilImpl serviceUtil = new BloodBankServiceUtilImpl();
        BloodTestReport bloodTestReport = new BloodTestReport();
        String donationId = serviceUtil.getRandomStringForDonationId();
        bbservice.setBloodGrp(bloodGroupInput);
        // since no patient id yet in Patient model validating through patient email
        bbservice.setPatientId(patient.getPatientId());
        Date d1 = new Date();
        bbservice.setDate(d1);
        bbservice.setDonationId(donationId);
//        List<BloodTestReport> tests = new ArrayList<BloodTestReport>();
//        BloodTestReport bloodTest = new BloodTestReport();
//        bloodTest.setHemoglobinValue(4);
        bloodBankDatabase.insertBloodBankServiceDetails(bbservice);
        return donationId;
    }
}



