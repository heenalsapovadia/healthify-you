package persistence.patient.utilImpl;

import org.json.simple.JSONArray;
import persistence.common.jsonUtil.utilImpl.JsonPatientReportParserImpl;
import persistence.common.reports.model.Blood;
import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.util.BloodBankServiceUtil;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <pre>
 * This class is responsible for implementing functionalities related to Blood Bank Serive feature.
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
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

    public String registerPatientForBloodDonation(BloodBankServiceDAO bloodBankDatabase, Patient patient, String bloodGroupInput) {
        BloodBankService bbservice = new BloodBankService();
        BloodBankServiceUtilImpl serviceUtil = new BloodBankServiceUtilImpl();
        String donationId = serviceUtil.getRandomStringForDonationId();
        bbservice.setBloodGrp(bloodGroupInput);
        bbservice.setPatientId(patient.getPatientId());
        Date d1 = new Date();
        bbservice.setDate(d1);
        bbservice.setDonationId(donationId);
        bloodBankDatabase.insertBloodBankServiceDetails(bbservice);
        return donationId;
    }

    public boolean checkIfReportsAreNormalForDonation() {
        Boolean reportsAreNormalForBloodDonations = true;
        JsonPatientReportParserImpl reportParser = new JsonPatientReportParserImpl();
        Map report = reportParser.getPatientReport(Patient.instance().getPatientId());
        if(report == null){
            return true;
        }
        JSONArray allTestsArray = (JSONArray) report.get("tests");
        Map allTestsMap = (Map) allTestsArray.get(0);

        List<Blood> bloodReports = reportParser.parseBloodReports(allTestsMap);
        for ( Blood blood : bloodReports ) {
            if (blood.getCbcPanel().getHaemoglobin() < 14) {
                reportsAreNormalForBloodDonations = false;
            }
        }
        return reportsAreNormalForBloodDonations;
    }

    public boolean validateIfPreviousDonationMoreThanSixMonth(BloodBankService donation) {
        Boolean donatedInLastSixMonths = false;
        int m1 = donation.getDate().getYear() * 12 + donation.getDate().getMonth();
        Date currentDate = new Date();
        int m2 = currentDate.getYear() * 12 + currentDate.getMonth();

        // if greater than 6 months register for blood donation
        if (m2 - m1 + 1 <= 6) {
            donatedInLastSixMonths = true;
        }
        return donatedInLastSixMonths;
    }
}
