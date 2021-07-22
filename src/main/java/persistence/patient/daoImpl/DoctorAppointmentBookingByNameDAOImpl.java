package persistence.patient.daoImpl;

import persistence.patient.dao.DoctorAppointmentBookingByNameDAO;
import persistence.patient.util.DoctorAppointmentBookingByNameUtil;
import persistence.patient.utilImpl.DoctorAppointmentBookingByNameUtilImpl;
import presentation.patient.DoctorAppointmentBookingOutput;
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
    DoctorAppointmentBookingOutput doctorAppointmentBookingOutput = new DoctorAppointmentBookingOutput();

    if (!doctorAppointmentBookingByNameUtilImpl.validateID(doctorID)) {
      return null;
    } else {

      Connection conn = DatabaseConnection.getConnection();
      Statement statement = conn.createStatement();
      ResultSet rS = null;

      String sql = "select * from doc_availability where doctor_id =";
      List<String> daysAvailable = new ArrayList<>();
      try {
        /* retrieves doctor list for the symptoms */
        rS = statement.executeQuery(sql + doctorID);

        if (!rS.next()) {
          return null;
        } else {
          do {
            daysAvailable.add(rS.getString("weekday"));
          } while (rS.next());
        }

        System.out.println(daysAvailable);

        List<String> datesAvailable = new ArrayList<>();
        datesAvailable = doctorAppointmentBookingOutput.datesGenerator(daysAvailable, 0);
        Map<Integer, List<String>> doctorAvailability = new HashMap<>();
        doctorAvailability.put(doctorID, datesAvailable);
        return doctorAvailability;
      } catch (SQLException se) {
        return null;
      }
    }
  }

}
