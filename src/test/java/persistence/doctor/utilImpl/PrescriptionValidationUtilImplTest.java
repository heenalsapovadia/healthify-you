package persistence.doctor.utilImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;

public class PrescriptionValidationUtilImplTest {

    @Test
    public void testValidateAppointmentId_Valid() {
        DatabaseConnection.loadDatabaseConnection();

        // set current doctor
        Doctor.setDoctor("biswa.roy@healthifyyou.com");

        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Assert.assertNotNull(prescriptionValidationUtil.validateAppointmentId(17));
    }

    @Test
    public void testValidateAppointmentId_Invalid() {
        DatabaseConnection.loadDatabaseConnection();

        // set current doctor
        Doctor.setDoctor("biswa.roy@healthifyyou.com");

        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Assert.assertNull(prescriptionValidationUtil.validateAppointmentId(13));
    }
}