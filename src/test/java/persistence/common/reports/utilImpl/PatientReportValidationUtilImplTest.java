package persistence.common.reports.utilImpl;

import org.junit.Test;
import persistence.common.reports.util.PatientReportValidationUtil;
import persistence.patient.model.Patient;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientReportValidationUtilImplTest {

    PatientReportValidationUtil patientReportValidationUtil = new PatientReportValidationUtilImpl();

    @Test
    public void validateBloodReports() {
        Patient.setPatient("ronnie@gma.com");
        assertFalse(patientReportValidationUtil.validateBloodReports());
    }

    @Test
    public void validateKidneyReports() {
        Patient.setPatient("ronnie@gma.com");
        assertFalse(patientReportValidationUtil.validateKidneyReports());
    }

    @Test
    public void validateLiverReports() {
        Patient.setPatient("ronnie@gma.com");
        assertFalse(patientReportValidationUtil.validateLiverReports());
    }

    @Test
    public void validateEyeReports() {
        Patient.setPatient("ronnie@gma.com");
        assertFalse(patientReportValidationUtil.validateEyeReports());
    }
}