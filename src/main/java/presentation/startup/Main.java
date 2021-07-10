package presentation.startup;

import persistence.patient.daoImpl.BloodBankServiceDAOimpl;
import persistence.patient.model.BloodBankService;

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
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
}