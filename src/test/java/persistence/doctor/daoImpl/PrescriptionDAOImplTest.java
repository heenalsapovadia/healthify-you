package persistence.doctor.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.model.Doctor;
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
        DatabaseConnection.loadDatabaseConnection();
        Doctor.setDoctor("biswa.roy@healthifyyou.com");

        PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();

        int appointmentId = 12;
        int doctorId = Doctor.getDoctor().getDoctor_id(); // fetch the current doctor's id
        String doctorName = Doctor.getDoctor().getFirst_name() + Doctor.getDoctor().getLast_name(); // fetch the current doctor's name
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription1 = new Prescription();
        prescription1.setAppointment_id(appointmentId);
        prescription1.setPatient_id(12);
        prescription1.setDoctor_id(doctorId);
        prescription1.setDoctor_name(doctorName);
        prescription1.setPrescription_id(prescriptionId);
        prescription1.setMedicine_name("Med1");
        prescription1.setMorning(0);
        prescription1.setAfternoon(1);
        prescription1.setEvening(0);

        Prescription prescription2 = new Prescription();
        prescription2.setAppointment_id(appointmentId);
        prescription2.setPatient_id(12);
        prescription2.setDoctor_id(doctorId);
        prescription2.setDoctor_name(doctorName);
        prescription2.setPrescription_id(prescriptionId);
        prescription2.setMedicine_name("Med2");
        prescription2.setMorning(0);
        prescription2.setAfternoon(1);
        prescription2.setEvening(0);

        prescriptionList.add(prescription1);
        prescriptionList.add(prescription2);

        prescriptionDAO.insertPrescription(prescriptionList);

        Assert.assertEquals(2, prescriptionDAO.getPrescriptionById(prescriptionId).size());
    }

    /*
    fetch the max prescription id value from database table
     */
    @Test
    public void testFindMaxPrescriptionId() {
        DatabaseConnection.loadDatabaseConnection();
        int maxId = 15;
        Assert.assertEquals(maxId, new PrescriptionDAOImpl().findMaxPrescriptionId());
    }

    @Test
    public void testGetPrescriptionById() {
        DatabaseConnection.loadDatabaseConnection();
        int prescriptionId = 15;
        Assert.assertEquals(2, new PrescriptionDAOImpl().getPrescriptionById(prescriptionId).size());
    }
}