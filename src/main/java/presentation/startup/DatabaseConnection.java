package presentation.startup;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
	private static Connection connection = null;
	
	private DatabaseConnection() {}
	
	public static void loadDatabaseConnection() {
		if(connection == null) {
			try (InputStream resourceStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")){
				Properties prop = new Properties();
				prop.load(resourceStream);
				String databaseType = prop.getProperty("databaseType");
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url")+prop.getProperty(databaseType+"Db");
				String user = prop.getProperty(databaseType+"User");
				String password = prop.getProperty(databaseType+"Password");
				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url, user, password);
			}
			catch(IOException | SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, "Exception in creating database connection!\n{0}", e.toString());
			}
		}
	}
	
	public static Connection instance() {
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception in closing database connection!\n{0}", e.toString());
		}
	}
}
