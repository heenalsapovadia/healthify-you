package presentation.startup;

import presentation.doctor.DoctorMenuOutput;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws SQLException {
		/* Used for Testing. Refer this while making connections in all features. */
		System.gc();
		DatabaseConnection.loadDatabaseConnection();
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			LOGGER.log(Level.SEVERE, "Could not establish connection with database.");
			System.exit(0);
		}
		ApplicationOutput applicationOutput = ApplicationOutput.getInstance();
//		applicationOutput.displayOutput();
		DoctorMenuOutput.getInstance().displayOutput();
	}
}