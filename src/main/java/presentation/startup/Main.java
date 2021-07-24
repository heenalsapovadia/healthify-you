package presentation.startup;


import persistence.patient.model.Patient;
import presentation.patient.RequestMedicationOutput;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <pre>
 * This is class is responsible for bootstrapping the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws SQLException {
		DatabaseConnection.loadDatabaseConnection();
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			LOGGER.log(Level.SEVERE, "Could not establish connection with database.");
			System.exit(0);
		}
//		ApplicationOutput applicationOutput = ApplicationOutput.getInstance();
//		applicationOutput.displayOutput();
		Patient.setPatient("samtran@gmail.com");
		RequestMedicationOutput output = new RequestMedicationOutput();
		output.requestMedicationDetails();
	}
}