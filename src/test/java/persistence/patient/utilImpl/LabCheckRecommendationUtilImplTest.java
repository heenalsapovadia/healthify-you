package persistence.patient.utilImpl;

import org.junit.Test;
import persistence.patient.model.LabCheck;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckRecommendationUtil;
import presentation.startup.DatabaseConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class LabCheckRecommendationUtilImplTest {

    @Test
    public void genderBasedRecommendationForFemale() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("female_patient@gmail.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.genderBasedRecommendation();
        assertEquals(1, labCheckList.size());
        assertEquals(4, labCheckList.get(0).getCheckupId());
        Patient.resetPatient();
    }

    @Test
    public void genderBasedRecommendationForNonFemale() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("Nonfemale_patient@gmail.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.genderBasedRecommendation();
        assertEquals(0, labCheckList.size());
        Patient.resetPatient();
    }

    @Test
    public void ageBasedRecommendationForChildren() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("child@gmail.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.ageBasedRecommendation();
        assertEquals(1, labCheckList.size());
        assertEquals(3, labCheckList.get(0).getCheckupId());
        Patient.resetPatient();
    }

    @Test
    public void ageBasedRecommendationForAdult() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("adult@gmail.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.ageBasedRecommendation();
        assertEquals(1, labCheckList.size());
        assertEquals(1, labCheckList.get(0).getCheckupId());
        Patient.resetPatient();
    }

    @Test
    public void ageBasedRecommendationForSeniorCitizen() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("seniorcitizen@gmail.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.ageBasedRecommendation();

        assertEquals(2, labCheckList.size());

        HashMap<Integer, LabCheck> labCheckHashMap = new HashMap();
        for(LabCheck labCheck : labCheckList)
            labCheckHashMap.put(labCheck.getCheckupId(), labCheck);

        assertTrue(labCheckHashMap.containsKey(2));
        assertTrue(labCheckHashMap.containsKey(5));
        Patient.resetPatient();
    }

    @Test
    public void historyBasedRecommendation() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("ronnie@gma.com");

        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();
        List<LabCheck> labCheckList = labCheckRecommendationUtil.historyBasedRecommendation();

        Map<Integer, LabCheck> labCheckHashMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckHashMap.put(labCheck.getCheckupId(), labCheck);

        assertTrue(labCheckHashMap.containsKey(7));
        assertTrue(labCheckHashMap.containsKey(8));
        assertTrue(labCheckHashMap.containsKey(9));
        assertTrue(labCheckHashMap.containsKey(10));
        Patient.resetPatient();
    }
}