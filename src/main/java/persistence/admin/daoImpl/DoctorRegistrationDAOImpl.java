package persistence.admin.daoImpl;
import persistence.admin.dao.DoctorRegistrationDAO;
import persistence.admin.model.DoctorRegistration;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;


public class DoctorRegistrationDAOImpl implements DoctorRegistrationDAO {

    @Override
    public int updateDoctorDetails(DoctorRegistration doc) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.loadDatabaseConnection();
        Statement statement = conn.createStatement();

        try {
            /* insert ignore use to avoid violation of primary key constraint if the data for that key already exists in the database */
            /* inserts registration details into the doctors table of the database */
            statement.executeUpdate("insert ignore into doctors(first_name, last_name, joining_date, degree, specialization, birth_date, contact_number, city, email, password) values (\"" + doc.getFirstName() + "\"" + ", \"" + doc.getLastName() + "\"" + ", \"" + doc.getJoiningDate() + "\"" + ", \"" + doc.getDegree() + "\"" + ", \"" + doc.getSpecialization() + "\"" + ", \"" + doc.getBirthDate() + "\"" + ", \"" + doc.getContactNumber() + "\"" + ", \"" + doc.getCity() + "\"" + ", \"" + doc.getEmail() + "\"" + ", \"" + doc.getPassword() + "\");");
            statement.executeUpdate("insert ignore into UserCredentials values (\"" + doc.getEmail() + "\"" + ", \"" + doc.getPassword() + "\"" + ", 'D');");

            return 0;

        } catch (SQLException se) {
            return -1;
        }

    }

    @Override
    public boolean checkDoctorExists(String email) throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.loadDatabaseConnection();
        Statement statement = conn.createStatement();
        ResultSet rS = null;

        try {
            /* retrieves details into the UserCredentials table of the database */
            rS = statement.executeQuery("select * from UserCredentials where User_Id = " + "\"" + email + "\"" + ";");

            ArrayList<String> check = new ArrayList<>();
            while (rS.next()) {
                check.add(rS.getString("User_Id"));
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException se) {
            return false;
        }

    }

}

