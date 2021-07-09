package persistence.doctor.utilImpl;

import org.junit.Assert;
import org.junit.Test;
import presentation.startup.DatabaseConnection;

public class PrescriptionValidationUtilImplTest {

    @Test
    public void testValidateAppointmentId_Valid() {
        DatabaseConnection.loadDatabaseConnection();

        // set current doctor

        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Assert.assertNotNull(prescriptionValidationUtil.validateAppointmentId(12));
    }

    @Test
    public void testValidateAppointmentId_Invalid() {
        DatabaseConnection.loadDatabaseConnection();

        // set current doctor

        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Assert.assertNull(prescriptionValidationUtil.validateAppointmentId(13));
    }
}