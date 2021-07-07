package persistence.admin.daoImpl;
import persistence.admin.dao.DoctorRegistrationDAO;
import persistence.admin.model.DoctorRegistration;
import presentation.startup.DatabaseConnection;
import java.sql.*;


public class DoctorRegistrationDAOImpl implements DoctorRegistrationDAO {

    @Override
    public int updateDoctorDetails(DoctorRegistration doc) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.loadDatabaseConnection();
        Statement statement = conn.createStatement();

        try {
            /* insert ignore use to avoid violation of primary key constraint if the data for that key already exists in the database */
            /* inserts registration details into the doctors table of the database */
            statement.executeUpdate("insert ignore into doctors values (\"" + doc.getDoctorID() + "\"," + "\"" + doc.getFirstName() + "\"" + ", \"" + doc.getLastName() + "\"" + ", \"" + doc.getJoiningDate() + "\"" + ", \"" + doc.getDegree() + "\"" + ", \"" + doc.getSpecialization() + "\"" + ", \"" + doc.getBirthDate() + "\"" + ", \"" + doc.getContactNumber() + "\"" + ", \"" + doc.getCity() + "\"" + ", \"" + doc.getEmail() + "\"" + ", \"" + doc.getPassword() + "\");");
            statement.executeUpdate("insert ignore into UserCredentials values (\"" + doc.getEmail() + "\"" + ", \"" + doc.getPassword() + "\"" + ", 'D');");
//            statement.executeUpdate("update UserCredentials set User_Type='D' where User_Id =" + "\"" + doc.getEmail() + "\"");

            return 0;

        } catch (SQLException se) {
            return -1;
        }


    }

}
