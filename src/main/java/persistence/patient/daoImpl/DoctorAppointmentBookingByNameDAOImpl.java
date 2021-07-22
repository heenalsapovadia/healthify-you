package persistence.patient.daoImpl;

import persistence.patient.dao.DoctorAppointmentBookingByNameDAO;
import persistence.patient.util.DoctorAppointmentBookingByNameUtil;
import persistence.patient.utilImpl.DoctorAppointmentBookingByNameUtilImpl;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DoctorAppointmentBookingByNameDAOImpl implements DoctorAppointmentBookingByNameDAO {

  @Override
  public Map<Integer, String> fetchDoctorIdentifier(String name) throws SQLException {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtilImpl = new DoctorAppointmentBookingByNameUtilImpl();

    if (!doctorAppointmentBookingByNameUtilImpl.validateName(name)) {
      return null;
    } else {
      String sql = "select * from doctors where ";

      Connection conn = DatabaseConnection.getConnection();
      Statement statement = conn.createStatement();
      ResultSet rS = null;

      try {
        /* retrieves doctor list for the symptoms */
        rS = statement.executeQuery(sql+"first_name=\""+name.toUpperCase(Locale.ROOT)+"\""+"or last_name=\""+name.toUpperCase(Locale.ROOT)+"\"");

        Map<Integer, String> doctorIdentifierList = new HashMap<>();
        if(!rS.next()) {
          return null;
        } else {
            int doctorID = rS.getInt("doctor_id");
            String doctorName = "";
            doctorName = doctorName + rS.getString("first_name") + " " + rS.getString("last_name");
            doctorName = doctorName.trim();
            doctorIdentifierList.put(doctorID, doctorName);
        }
        return doctorIdentifierList;
      } catch (SQLException se) {
          return null;
      }
    }
  }

  @Override
  public Map<Integer, List<String>> fetchDoctorAvailability(int doctorID) throws SQLException {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtilImpl = new DoctorAppointmentBookingByNameUtilImpl();

    if (!doctorAppointmentBookingByNameUtilImpl.validateID(doctorID)) {
      return null;
    } else {
      List<String> datesAvailable = new ArrayList<>();


      Map<Integer, List<String>> doctorAvailability = new HashMap<>();
      doctorAvailability.put(doctorID, datesAvailable);
      return doctorAvailability;
    }
  }

}
