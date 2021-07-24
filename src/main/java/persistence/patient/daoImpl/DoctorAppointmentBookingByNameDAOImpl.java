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
  public Map<Integer, String> fetchDoctorIdentifier(String name) {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtilImpl = new DoctorAppointmentBookingByNameUtilImpl();

    if (!doctorAppointmentBookingByNameUtilImpl.validateName(name)) {
      return null;
    } else {
      String sql = "select * from doctors where ";

      Connection connection = DatabaseConnection.getConnection();
      Statement statement = null;
      try {
        statement = connection.createStatement();
      } catch (SQLException sqlException) {
        System.err.println("Error occurred in establishing database connection!");
        return null;
      }
      ResultSet resultSet = null;

      try {
        /* retrieves doctor list for the symptoms */
        resultSet = statement.executeQuery(sql + "first_name=\"" + name.toUpperCase(Locale.ROOT) + "\"" + "or last_name=\"" + name.toUpperCase(Locale.ROOT) + "\"");

        Map<Integer, String> doctorIdentifierList = new HashMap<>();
        if (!resultSet.next()) {
          return null;
        } else {
            do {
              int doctorID = resultSet.getInt("doctor_id");
              String doctorName = "";
              doctorName = doctorName + resultSet.getString("first_name") + " " + resultSet.getString("last_name");
              doctorName = doctorName.trim();
              doctorIdentifierList.put(doctorID, doctorName);
            } while(resultSet.next());
        }
        return doctorIdentifierList;
      } catch (SQLException se) {
          return null;
      }
    }
  }

  @Override
  public Map<Integer, List<String>> fetchDoctorAvailability(int doctorID) {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtilImpl = new DoctorAppointmentBookingByNameUtilImpl();
    DoctorAppointmentBookingOutput doctorAppointmentBookingOutput = new DoctorAppointmentBookingOutput();

    try {
      if (!doctorAppointmentBookingByNameUtilImpl.validateID(doctorID)) {
        return null;
      } else {
          Connection connection = DatabaseConnection.getConnection();
          Statement statement = null;
          try {
            statement = connection.createStatement();
          } catch (SQLException sqlException) {
            System.err.println("Error occurred in establishing database connection!");
            return null;
          }
          ResultSet resultSet = null;

          String sql = "select * from doc_availability where doctor_id =";
          List<String> daysAvailable = new ArrayList<>();
          try {
            /* retrieves doctor list for the symptoms */
            resultSet = statement.executeQuery(sql + doctorID);

          if (!resultSet.next()) {
            return null;
          } else {
              do {
                daysAvailable.add(resultSet.getString("weekday"));
              } while (resultSet.next());
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
    } catch (SQLException sqlException) {
      System.err.println("Error occurred in establishing database connection!");
      return null;
    }
  }

  @Override
  public List<Integer> addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate) {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();

    try {
      if (!doctorAppointmentBookingByNameUtil.validateID(doctorID)) {
        return null;
      }
    } catch (SQLException sqlException) {
      System.err.println("Error occurred in establishing database connection!");
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

    Connection connection = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
      System.err.println("Error occurred in establishing database connection!");
      return null;
    }
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;

    List<Integer> appointmentIDList = new ArrayList<>();

    try {
      /* retrieves doctor list for the symptoms */
      resultSet = statement.executeQuery("insert ignore into doctor_appointment(patient_id, doctor_id, booked_on_date, booked_for_date) values(" + patientID + "," + doctorID + ", \"" + bookedOnDate + "\"" + ", \"" + appointmentDate + "\");");
      resultSet1 = statement.executeQuery("select * from doctor_appointment where patient_id = " + patientID + " and doctor_id=" + doctorID + " and booked_for_date=\"" + appointmentDate + "\" and booked_on_date=\"" + bookedOnDate + "\";");

      if(!resultSet1.next()) {
        return null;
      } else {
          do {
            appointmentIDList.add(resultSet1.getInt("appointment_id"));
          } while(resultSet1.next());
          return appointmentIDList;
      }
    } catch (SQLException sqlException) {
        return null;
    }
  }

  @Override
  public int checkDoctorExists(int doctorID) {
    String sql = "select distinct doctor_id from doctors;";

    Connection connection = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
      System.err.println("Error occurred in establishing database connection!");
      return -1;
    }
    ResultSet resultSet = null;

    try {
      /* retrieves doctor_id(s) list from the database */
      resultSet = statement.executeQuery(sql);

      Set<Integer> doctorIDSet = new HashSet<>();
      if (!resultSet.next()) {
        return -1;
      } else {
          do {
            doctorIDSet.add(resultSet.getInt("doctor_id"));
          } while(resultSet.next());
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
  public int checkPatientExists(String email) {
    String sql = "select patient_id from patients where patient_email = ";
    int identifier;

    Connection connection = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
      System.err.println("Error occurred in establishing database connection!");
      return -1;
    }
    ResultSet resultSet = null;

    try {
      /* retrieves doctor_id(s) list from the database */
      resultSet = statement.executeQuery(sql + "\"" + email + "\";");

      if (!resultSet.next()) {
        return -1;
      } else {
        do {
          identifier = resultSet.getInt("patient_id");
        } while (resultSet.next());
      }
    } catch (SQLException se) {
      return -1;
    }
    return identifier;
  }

  @Override
  public double fetchDoctorCharges(int doctorID) {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();

    try {
      if (!doctorAppointmentBookingByNameUtil.validateID(doctorID)) {
        return -1;
      } else {
          String sql = "select charges from doctor_specific_charges where doctor_id = ";
          double charges;

          Connection connection = DatabaseConnection.getConnection();
          Statement statement = connection.createStatement();
          ResultSet resultSet = null;

          try {
            /* retrieves doctor_id(s) list from the database */
            resultSet = statement.executeQuery(sql + doctorID + ";");

            if (!resultSet.next()) {
              return -1;
            } else {
                do {
                  charges = resultSet.getDouble("charges");
                } while (resultSet.next());
            }
        } catch (SQLException se) {
          return -1;
        }
        return charges;
      }
    } catch (SQLException sqlException) {
        System.err.println("Error occurred in establishing database connection!");
        return -1;
    }
  }

  @Override
  public int updateBillingID(int billingID, String appointmentIDList) {

    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    if (billingID == 0) {
      return -1;
    } else {
      String sql = "update doctor_appointment set billing_id = ";

      Connection connection = DatabaseConnection.getConnection();
      Statement statement = null;
      try {
        statement = connection.createStatement();
      } catch (SQLException sqlException) {
          System.err.println("Error occurred in establishing database connection!");
          return -1;
      }

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
