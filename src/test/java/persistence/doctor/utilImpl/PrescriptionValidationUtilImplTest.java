package persistence.doctor.utilImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.model.Doctor;
import persistence.doctor.util.PrescriptionValidationUtil;
import presentation.startup.DatabaseConnection;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void validateMedicineName() {
        PrescriptionValidationUtil prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Set<String> medicineList = new HashSet<>();
        medicineList.add("crocin");
        medicineList.add("ibuprofen");

        String medicineName = "Crocin";
        Assert.assertTrue(prescriptionValidationUtil.validateMedicineName(medicineName, medicineList));
    }
}