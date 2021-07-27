package persistence.doctor.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;

public class AppointmentDAOImplTest {

    /*
    Check the Appointment Id validity
     */

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