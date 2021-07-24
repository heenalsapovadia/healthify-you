package persistence.patient.daoImpl;

import persistence.patient.dao.DoctorAppointmentBookingByNameDAO;
import persistence.patient.utilImpl.DoctorAppointmentBookingByNameUtilImpl;
import presentation.patient.DoctorAppointmentBookingOutput;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
* <pre>
* DAO class for doctor appointment booking by name
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

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
        rS = statement.executeQuery(sql + "first_name=\"" + name.toUpperCase(Locale.ROOT) + "\"" + "or last_name=\"" + name.toUpperCase(Locale.ROOT) + "\"");

        Map<Integer, String> doctorIdentifierList = new HashMap<>();
        if (!rS.next()) {
          return null;
        } else {
          do {
            int doctorID = rS.getInt("doctor_id");
            String doctorName = "";
            doctorName = doctorName + rS.getString("first_name") + " " + rS.getString("last_name");
            doctorName = doctorName.trim();
            doctorIdentifierList.put(doctorID, doctorName);
          } while(rS.next());
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

  @Override
  public List<Integer> addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate) throws SQLException {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();

    if (!doctorAppointmentBookingByNameUtil.validateID(doctorID)) {
      return null;
    }

    if (bookedOnDate == null) {
      return null;
    }

    if (bookedOnDate != null && bookedOnDate.isEmpty()) {
      return null;
    }

    if (appointmentDate == null) {
      return null;
    }

    if (appointmentDate != null && appointmentDate.isEmpty()) {
      return null;
    }

    Connection conn = DatabaseConnection.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rS = null;
    ResultSet rS1 = null;

    List<Integer> appointmentIDList = new ArrayList<>();

    try {
      /* retrieves doctor list for the symptoms */
      rS = statement.executeQuery("insert ignore into doctor_appointment(patient_id, doctor_id, booked_on_date, booked_for_date) values(" + patientID + "," + doctorID + ", \"" + bookedOnDate + "\"" + ", \"" + appointmentDate + "\");");
      rS1 = statement.executeQuery("select * from doctor_appointment where patient_id = " + patientID + " and doctor_id=" + doctorID + " and booked_for_date=\"" + appointmentDate + "\" and booked_on_date=\"" + bookedOnDate + "\";");

      if(!rS1.next()) {
        return null;
      } else {
        do {
          appointmentIDList.add(rS1.getInt("appointment_id"));
        } while(rS1.next());
        return appointmentIDList;
      }
    } catch (SQLException sqlException) {
      return null;
    }
  }

  @Override
  public int checkDoctorExists(int doctorID) throws SQLException {
    String sql = "select distinct doctor_id from doctors;";

    Connection conn = DatabaseConnection.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rS = null;

    try {
      /* retrieves doctor_id(s) list from the database */
      rS = statement.executeQuery(sql);

      Set<Integer> doctorIDSet = new HashSet<>();
      if (!rS.next()) {
        return -1;
      } else {
        doctorIDSet.add(rS.getInt("doctor_id"));
        if (doctorIDSet.contains(doctorID))
          return 0;
        else
          return -1;
      }
    } catch (SQLException se) {
      return -1;
    }
  }

  @Override
  public int checkPatientExists(String email) throws SQLException {
    String sql = "select patient_id from patients where patient_email = ";
    int identifier;

    Connection conn = DatabaseConnection.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rS = null;

    try {
      /* retrieves doctor_id(s) list from the database */
      rS = statement.executeQuery(sql + "\"" + email + "\";");

      if (!rS.next()) {
        return -1;
      } else {
        do {
          identifier = rS.getInt("patient_id");
        } while (rS.next());
      }
    } catch (SQLException se) {
      return -1;
    }
    return identifier;
  }

  @Override
  public double fetchDoctorCharges(int doctorID) throws SQLException{

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();

    if (!doctorAppointmentBookingByNameUtil.validateID(doctorID)) {
      return -1;
    } else {
      String sql = "select charges from doctor_specific_charges where doctor_id = ";
      double charges;

      Connection conn = DatabaseConnection.getConnection();
      Statement statement = conn.createStatement();
      ResultSet rS = null;

      try {
        /* retrieves doctor_id(s) list from the database */
        rS = statement.executeQuery(sql + doctorID + ";");

        if (!rS.next()) {
          return -1;
        } else {
          do {
            charges = rS.getDouble("charges");
          } while (rS.next());
        }
      } catch (SQLException se) {
        return -1;
      }
      return charges;
    }
  }

  @Override
  public int updateBillingID(int billingID, String appointmentIDList) throws SQLException{

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    if (billingID == 0) {
      return -1;
    } else {
      String sql = "update doctor_appointment set billing_id = ";

      Connection conn = DatabaseConnection.getConnection();
      Statement statement = conn.createStatement();
      ResultSet rS = null;

      try {
        /* retrieves doctor_id(s) list from the database */
        statement.executeUpdate(sql + billingID + " where appointment_id in (" + appointmentIDList + ");");

        return 0;

      } catch (SQLException se) {
        return -1;
      }
    }
  }

}
