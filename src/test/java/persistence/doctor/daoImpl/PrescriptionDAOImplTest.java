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
        int doctorId = Doctor.instance().getDoctorId(); // fetch the current doctor's id
        String doctorName = Doctor.instance().getFirstName() + Doctor.instance().getLastName(); // fetch the current doctor's name
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription1 = new Prescription();
        prescription1.setAppointmentId(appointmentId);
        prescription1.setPatientId(12);
        prescription1.setDoctorId(doctorId);
        prescription1.setDoctorName(doctorName);
        prescription1.setPrescriptionId(prescriptionId);
        prescription1.setMedicineName("Med1");
        prescription1.setMorning(0);
        prescription1.setAfternoon(1);
        prescription1.setEvening(0);

        Prescription prescription2 = new Prescription();
        prescription2.setAppointmentId(appointmentId);
        prescription2.setPatientId(12);
        prescription2.setDoctorId(doctorId);
        prescription2.setDoctorName(doctorName);
        prescription2.setPrescriptionId(prescriptionId);
        prescription2.setMedicineName("Med2");
        prescription2.setMorning(0);
        prescription2.setAfternoon(1);
        prescription2.setEvening(0);

        prescriptionList.add(prescription1);
        prescriptionList.add(prescription2);

        prescriptionDAO.insertPrescription(prescriptionList);

        Assert.assertEquals(2, prescriptionDAO.getPrescriptionById(prescriptionId).size());
    }

    @Test
    public void testGetPrescriptionById() {
        DatabaseConnection.loadDatabaseConnection();
        int prescriptionId = 15;
        Assert.assertEquals(2, new PrescriptionDAOImpl().getPrescriptionById(prescriptionId).size());
    }
}