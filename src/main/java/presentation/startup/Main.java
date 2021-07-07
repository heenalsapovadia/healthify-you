package presentation.startup;

import persistence.admin.model.DoctorRegistration;
import presentation.admin.DoctorRegistrationOutput;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		/* Used for Testing. Refer this while making connections in all features. */

		DatabaseConnection dbConnObject = new DatabaseConnection();
		Connection conn = dbConnObject.loadDatabaseConnection();
		try {
			if(conn.isValid(500)) {
				LOGGER.log(Level.INFO, "Connection Successful!");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
}
