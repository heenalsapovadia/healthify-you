package persistence.doctor.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;

public class AppointmentDAOImplTest {

    /*
    Check the Appointment object returned... null for no valid record, not null for valid record
     */
    @Test
    public void testValidateAppointmentId_Invalid() {
        DatabaseConnection.loadDatabaseConnection();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(17);

        //Set current doctor's id
        Doctor.setDoctor("biswa.roy@healthifyyou.com");
        int doctor_id = Doctor.getDoctor().getDoctorId();


        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        appointment = appointmentDAO.validateAppointmentId(appointment);

        Assert.assertNotNull(appointment);
        Assert.assertEquals(doctor_id, appointment.getDoctorId());
    }

    @Test
    public void testValidateAppointmentId_Valid() {
        DatabaseConnection.loadDatabaseConnection();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(12);

        //Set current doctor's id
        Doctor.setDoctor("biswa.roy@healthifyyou.com");

        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        Assert.assertNull(appointmentDAO.validateAppointmentId(appointment));
    }
}