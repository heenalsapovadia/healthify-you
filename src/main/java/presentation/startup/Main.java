package presentation.startup;

import persistence.patient.daoImpl.BloodBankServiceDAOimpl;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import presentation.patient.BloodBankServiceOutput;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws SQLException {
		/* Used for Testing. Refer this while making connections in all features. */
		DatabaseConnection.loadDatabaseConnection();
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			LOGGER.log(Level.SEVERE, "Could not establish connection with database.");
			System.exit(0);
		}
		try {
			if(conn.isValid(2000)) {
				LOGGER.log(Level.INFO, "Connection Successful!");

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

				// Dummy code end
				////
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
}