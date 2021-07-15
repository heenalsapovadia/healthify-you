package persistence.patient.utilImpl;

import org.junit.Test;
import persistence.patient.model.LabCheck;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckRecommendationUtil;

import java.util.List;

import static org.junit.Assert.*;

public class LabCheckRecommendationUtilImplTest {

    @Test
    public void genderBasedRecommendationForFemale() {
        Patient.setPatient("female_patient@gmail.com");
        Patient.getPatient().setPatientGender("F");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.genderBasedRecommendation();
        assertEquals(1, labCheckList.size());
        assertEquals(4, labCheckList.get(0).getCheckup_id());
    }

    @Test
    public void genderBasedRecommendationForNonFemale() {
        Patient.setPatient("Nonfemale_patient@gmail.com");
        Patient.getPatient().setPatientGender("M");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.genderBasedRecommendation();
        assertEquals(0, labCheckList.size());
    }

    @Test
    public void ageBasedRecommendation() {
    }
}