package persistence.doctor.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.model.Prescription;
import presentation.startup.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOImplTest {

    /*
    Check if prescriptions were inserted or not
     */
    @Test
    public void testInsertPrescription() {
        int appointmentId = 12;
        int doctor_id = 123; // fetch the current doctor's id
        String doctor_name = "Test"; // fetch the current doctor's name
        int prescription_id = 15;

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription1 = new Prescription();
        prescription1.setAppointment_id(appointmentId);
        prescription1.setPatient_id(12);
        prescription1.setDoctor_id(doctor_id);
        prescription1.setDoctor_name(doctor_name);
        prescription1.setPrescription_id(prescription_id);
        prescription1.setMedicine_name("Med1");
        prescription1.setMorning(0);
        prescription1.setAfternoon(1);
        prescription1.setEvening(0);

        Prescription prescription2 = new Prescription();
        prescription2.setAppointment_id(appointmentId);
        prescription2.setPatient_id(12);
        prescription2.setDoctor_id(doctor_id);
        prescription2.setDoctor_name(doctor_name);
        prescription2.setPrescription_id(prescription_id);
        prescription2.setMedicine_name("Med2");
        prescription2.setMorning(0);
        prescription2.setAfternoon(1);
        prescription2.setEvening(0);

        prescriptionList.add(prescription1);
        prescriptionList.add(prescription2);

        // call getPrescription and verify
    }

    /*
    fetch the max prescription id value from database table
     */
    @Test
    public void testFindMaxPrescriptionId() {
        DatabaseConnection.loadDatabaseConnection();
        int maxId = 11;
        Assert.assertEquals(maxId, new PrescriptionDAOImpl().findMaxPrescriptionId());
    }
}