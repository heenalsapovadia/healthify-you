package persistence.patient.daoImpl;

import org.junit.Test;
import static org.junit.Assert.*;

import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.BloodBankServiceUtilImpl;
import presentation.patient.BloodBankServiceOutput;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BloodBankServiceDAOImplTest {

    @Test
    public void getAllBloodDonationsForPatientTest() throws SQLException {
//        DatabaseConnection.loadDatabaseConnection();
//        Connection conn = DatabaseConnection.getConnection();
//        BloodBankServiceUtilImpl bloodBankServiceUtilImpl = new BloodBankServiceUtilImpl();

        // testing the dummy code in main for BloodBankDonation implementation
        // Dummy code start
        RegistrationDAOImpl registrationImplementation = new RegistrationDAOImpl();
        Patient currentPatient = new Patient();
        currentPatient.setPatientName("Saloni Ray");
        currentPatient.setPatientAddress("India");
        currentPatient.setPatientDob("08201996");
        currentPatient.setPatientGender("f");
        currentPatient.setPassword("parrrot88");
        currentPatient.setPatientEmail("salonimr");
        registrationImplementation.addPatientDetails(currentPatient);
        BloodBankServiceOutput bank = new BloodBankServiceOutput();
        bank.bloodBankService(currentPatient, "B+");
        registrationImplementation.addPatientDetails(currentPatient);
        String userExistsOutput="All donation details received..";
        assertEquals(userExistsOutput,registrationImplementation.addPatientDetails(currentPatient));

        // Dummy code end
        ////
    }

}
