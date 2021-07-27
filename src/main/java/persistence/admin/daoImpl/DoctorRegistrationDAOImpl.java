package persistence.admin.daoImpl;
import persistence.admin.dao.DoctorRegistrationDAO;
import persistence.admin.model.DoctorRegistration;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
* <pre>
* Perform operations for registering doctor in the system in the database
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRegistrationDAOImpl implements DoctorRegistrationDAO {

  @Override
  public int updateDoctorDetails(DoctorRegistration doc) {
    Connection connection = DatabaseConnection.instance();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
        System.err.println("Error occurred in establishing database connection!");
        return -1;
    }

    try {
      /* insert ignore use to avoid violation of primary key constraint if the data for that key already exists in the database */
      /* inserts registration details into the doctors table of the database */
      statement.executeUpdate("insert ignore into doctors(first_name, last_name, joining_date, degree, specialization, birth_date, contact_number, city, email) values (\"" + doc.getFirstName() + "\"" + ", \"" + doc.getLastName() + "\"" + ", \"" + doc.getJoiningDate() + "\"" + ", \"" + doc.getDegree() + "\"" + ", \"" + doc.getSpecialization() + "\"" + ", \"" + doc.getBirthDate() + "\"" + ", \"" + doc.getContactNumber() + "\"" + ", \"" + doc.getCity() + "\"" + ", \"" + doc.getEmail() + "\");");
      statement.executeUpdate("insert ignore into UserCredentials values (\"" + doc.getEmail() + "\"" + ", \"" + doc.getPassword() + "\"" + ", 'D');");

      return 0;

    } catch (SQLException | NullPointerException se) {
        return -1;
    }
  }

  @Override
  public boolean checkDoctorExists(String email) {

    Connection connection = DatabaseConnection.instance();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
        System.err.println("Error occurred in establishing database connection!");
        return false;
    }
    ResultSet resultSet = null;

    try {
      /* retrieves details into the UserCredentials table of the database */
      resultSet = statement.executeQuery("select * from UserCredentials where User_Id = " + "\"" + email + "\"" + ";");
      ArrayList<String> check = new ArrayList<>();
      while (resultSet.next()) {
        check.add(resultSet.getString(DatabaseConstants.USER_ID));
      }

      if (check.size() > 0) {
        return true;
      } else {
          return false;
      }
    } catch (SQLException | NullPointerException se) {
        return false;
    }
  }
}

