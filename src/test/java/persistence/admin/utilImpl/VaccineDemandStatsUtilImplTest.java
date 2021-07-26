package persistence.admin.utilImpl;

import org.junit.Test;
import persistence.admin.util.VaccineDemandStatsUtil;
import presentation.startup.DatabaseConnection;
import java.util.Map;
import static org.junit.Assert.*;

public class VaccineDemandStatsUtilImplTest {
    VaccineDemandStatsUtil vaccineDemandStatsUtil;

    @Test
    public void mostVaccinatedByVaccine() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("Influenza", vaccineDemandStatsUtil.mostVaccinatedBy("vaccineName"));
    }

    @Test
    public void mostVaccinatedByAge() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("27 to 60", vaccineDemandStatsUtil.mostVaccinatedBy("ageGroup"));
    }

    @Test
    public void mostVaccinatedByGender() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("M", vaccineDemandStatsUtil.mostVaccinatedBy("gender"));
    }

    @Test
    public void mostVaccinatedByArea() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("Argentina", vaccineDemandStatsUtil.mostVaccinatedBy("area"));
    }

    @Test
    public void dosesAdministered() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals(23, vaccineDemandStatsUtil.dosesAdministered(12));
    }

    @Test
    public void covidVaccineDistribution() {
        DatabaseConnection.loadDatabaseConnection();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        Map<String, Integer> covidAnalysis = vaccineDemandStatsUtil.covidVaccineDistribution();
        assertTrue(covidAnalysis.get("covaxin")>0);
    }
}