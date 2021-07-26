package persistence.patient.daoImpl;

import persistence.patient.dao.DoctorRecommendationDAO;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
* <pre>
* Perform operations for fetching data from database for doctor recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRecommendationDAOImpl implements DoctorRecommendationDAO{

  @Override
  public ArrayList<Integer> fetchDoctorList(String symptom) {
    Connection connection = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
        System.err.println("Error occurred in establishing database connection!");
        return null;
    }
    ResultSet resultSet = null;

    String sql = "select doctor_id from doctor_symptoms where symptom = \"" +symptom+ "\"" + ";";

    try {
      /* retrieves doctor list for the symptoms */
      resultSet = statement.executeQuery(sql);

      ArrayList<Integer> doctorIDList = new ArrayList<>();
      while (resultSet.next()) {
        doctorIDList.add(resultSet.getInt("doctor_id"));
      }
      return doctorIDList;
    } catch (SQLException se) {
        return null;
    }
  }

  @Override
  public ArrayList<String> getDoctorName(ArrayList<Integer> recf) {

    if(recf == null) {
      return null;
    }

    if(recf != null && recf.size() == 0) {
      return null;
    }

    String inClause = "";
    int size = recf.size();
    for(int i =0; i < size; i++) {
      if(i <= size-2) {
        inClause = inClause + recf.get(i) + ",";
      } else {
          inClause = inClause + recf.get(i);
      }
    }

    Connection connection = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException throwables) {
        System.err.println("Database connection failed!");
        return null;
    }
    ResultSet resultSet = null;

    String sql = "select first_name, last_name from doctors where doctor_id in (" +inClause+ ");";

    try {
      /* retrieves doctor list for the symptoms */
      resultSet = statement.executeQuery(sql);
      ArrayList<String> doctorList = new ArrayList<>();
      while (resultSet.next()) {
        String name = "";
        name = resultSet.getString("first_name") + " " +resultSet.getString("last_name");
        doctorList.add(name);
      }
      return doctorList;
    } catch (SQLException se) {
        return null;
    }
  }

}