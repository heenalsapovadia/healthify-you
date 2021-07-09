package persistence.doctor.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.model.Appointment;
import presentation.startup.DatabaseConnection;

public class AppointmentDAOImplTest {

    /*
    Check the Appointment object returned... null for no valid record, not null for valid record
     */
    @Test
    public void testValidateAppointmentId_Invalid() {
        DatabaseConnection.loadDatabaseConnection();

        Appointment appointment = new Appointment();
        appointment.setAppointment_id(12);

        //Set current doctor's id

        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        appointment = appointmentDAO.validateAppointmentId(appointment);

        //Set current doctor's id
        int doctor_id = 12; // dummy, NEEDS to be FIXED

        Assert.assertEquals(doctor_id, appointment.getDoctor_id());
    }

    @Test
    public void testValidateAppointmentId_Valid() {
        DatabaseConnection.loadDatabaseConnection();

        Appointment appointment = new Appointment();
        appointment.setAppointment_id(12);

        //Set current doctor's id

        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        Assert.assertNull(appointmentDAO.validateAppointmentId(appointment));
    }
}