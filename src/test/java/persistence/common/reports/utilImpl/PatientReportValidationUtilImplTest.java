package persistence.common.reports.utilImpl;

import org.junit.Test;
import persistence.common.reports.util.PatientReportValidationUtil;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientReportValidationUtilImplTest {

    PatientReportValidationUtil patientReportValidationUtil = new PatientReportValidationUtilImpl();

    @Test
    public void validateBloodReports() {
        assertTrue(patientReportValidationUtil.validateBloodReports());
    }

    @Test
    public void validateKidneyReports() {
        assertTrue(patientReportValidationUtil.validateKidneyReports());
    }

    @Test
    public void validateLiverReports() {
        assertTrue(patientReportValidationUtil.validateLiverReports());
    }

    @Test
    public void validateEyeReports() {
        assertTrue(patientReportValidationUtil.validateEyeReports());
    }
}