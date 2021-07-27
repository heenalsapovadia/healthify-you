package persistence.common.reports.utilImpl;

import org.junit.Test;
import persistence.common.reports.util.PatientReportValidationUtil;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import static org.junit.Assert.*;

public class PatientReportValidationUtilImplTest {

    PatientReportValidationUtil patientReportValidationUtil = new PatientReportValidationUtilImpl();

    @Test
    public void validateBloodReports() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("ronnie@gma.com");
        assertFalse(patientReportValidationUtil.validateBloodReports());
    }

    @Test
    public void validateKidneyReports() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("ronnie@gma.com");
        assertTrue(patientReportValidationUtil.validateKidneyReports());
    }

    @Test
    public void validateLiverReports() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("ronnie@gma.com");
        assertTrue(patientReportValidationUtil.validateLiverReports());
    }

    @Test
    public void validateEyeReports() {
        DatabaseConnection.loadDatabaseConnection();
        Patient.setPatient("ronnie@gma.com");
        assertTrue(patientReportValidationUtil.validateEyeReports());
    }
}