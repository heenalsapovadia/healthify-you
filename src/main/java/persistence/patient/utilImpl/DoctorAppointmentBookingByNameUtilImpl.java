package persistence.patient.utilImpl;

import persistence.patient.util.DoctorAppointmentBookingByNameUtil;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DoctorAppointmentBookingByNameUtilImpl implements DoctorAppointmentBookingByNameUtil {

  public boolean validateID(int doctorID) throws SQLException {

    if (doctorID == 0) {
      return false;
    } else {
        String sql = "select distinct doctor_id from doctors;";

        Connection conn = DatabaseConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rS = null;

        try {
          /* retrieves doctor_id(s) list from the database */
          rS = statement.executeQuery(sql);

          Set<Integer> doctorIDSet = new HashSet<>();
          if (!rS.next()) {
            return false;
          } else {
              doctorIDSet.add(rS.getInt("doctor_id"));
              return true;
          }
        } catch (SQLException se) {
            return false;
        }
    }
  }


  public boolean validateName(String name) {
    if (name != null) {
      if (name.isEmpty()) {
        return false;
      } else {
          return true;
      }
    } else {
        return false;
    }
  }

}
