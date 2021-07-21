package persistence.admin.utilImpl;

import org.junit.Test;
import persistence.admin.util.VaccineDemandStatsUtil;

import java.util.Map;

import static org.junit.Assert.*;

public class VaccineDemandStatsUtilImplTest {
    VaccineDemandStatsUtil vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();

    @Test
    public void mostVaccinatedByVaccine() {
        assertEquals("covaxin", vaccineDemandStatsUtil.mostVaccinatedBy("vaccineName"));
    }

    @Test
    public void mostVaccinatedByAge() {
        assertEquals("Adult", vaccineDemandStatsUtil.mostVaccinatedBy("ageGroup"));
    }

    @Test
    public void mostVaccinatedByGender() {
        assertEquals("Male", vaccineDemandStatsUtil.mostVaccinatedBy("gender"));
    }

    @Test
    public void mostVaccinatedByArea() {
        assertEquals("Halifax", vaccineDemandStatsUtil.mostVaccinatedBy("area"));
    }

    @Test
    public void dosesAdministered() {
        assertEquals(10, vaccineDemandStatsUtil.dosesAdministered(1));
    }

    @Test
    public void covidVaccineDistribution() {
        Map<String, Integer> covidAnalysis = vaccineDemandStatsUtil.covidVaccineDistribution();
        assertTrue(covidAnalysis.get("covaxin")>0);
    }
}