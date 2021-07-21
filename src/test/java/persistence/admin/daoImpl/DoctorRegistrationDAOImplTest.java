package persistence.admin.daoImpl;

import static org.junit.Assert.*;

import org.junit.Test;
import persistence.admin.model.DoctorRegistration;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
* <pre>
* Perform tests for registering doctor in the database of system
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRegistrationDAOImplTest {

    @Test
    public void updateDoctorDetails() throws SQLException {
        DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
        DoctorRegistration doc = new DoctorRegistration();
        DatabaseConnection.loadDatabaseConnection();

        doc.setFirstName("Karolina");
        doc.setLastName("Blix");
        doc.setEmail("karolina123@healthifyyou.com");
        doc.setPassword("Karo1234@");
        doc.setJoiningDate("1998-09-06");
        doc.setBirthDate("1956-07-03");
        doc.setDegree("MBBS");
        doc.setSpecialization("NA");
        doc.setCity("Halifax");

        assertEquals(0, doctorRegistrationDAOImpl.updateDoctorDetails(doc));
    }

    @Test
    public void checkDoctorExists() throws SQLException {
        DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
        DatabaseConnection.loadDatabaseConnection();

        assertTrue(doctorRegistrationDAOImpl.checkDoctorExists("karolina@healthifyyou.com"));
    }

    @Test
    public void checkDoctorNotExists() throws SQLException {
        DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
        DatabaseConnection.loadDatabaseConnection();

        assertFalse(doctorRegistrationDAOImpl.checkDoctorExists("karolina123456@healthifyyou.com"));
    }


}
