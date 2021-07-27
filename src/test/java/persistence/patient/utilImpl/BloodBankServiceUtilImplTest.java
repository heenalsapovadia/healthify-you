package persistence.patient.utilImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.Test;
import persistence.patient.model.BloodBankService;
/**
 * <pre>
 * Blood Bank Service Util Test method
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class BloodBankServiceUtilImplTest {

    @Test
    public void testGetRandomStringForDonationId() {
        assertFalse(Boolean.parseBoolean((new BloodBankServiceUtilImpl()).getRandomStringForDonationId()));
    }

    @Test
    public void testValidateIfPreviousDonationMoreThanSixMonth() {
        BloodBankServiceUtilImpl bloodBankServiceUtilImpl = new BloodBankServiceUtilImpl();
        BloodBankService bloodBankService = new BloodBankService();
        LocalDateTime atStartOfDayResult = LocalDate.of(2019, 1, 1).atStartOfDay();
        bloodBankService.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        bloodBankService.setBloodGrp("AB-");
        bloodBankService.setDonationId("42");
        bloodBankService.setPatientId(38);
        assertFalse(bloodBankServiceUtilImpl.validateIfPreviousDonationMoreThanSixMonth(bloodBankService));
    }

    @Test
    public void testGetTokenIdForDonation() {
        (new BloodBankServiceUtilImpl()).getTokenIdForDonation();
    }
}
