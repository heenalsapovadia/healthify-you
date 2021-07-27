package persistence.patient.daoImpl;

import persistence.common.DatabaseConstants;
import persistence.patient.dao.DoctorAppointmentBookingBySpecializationDAO;
import persistence.patient.utilImpl.DoctorAppointmentBookingBySpecializationUtilImpl;
import presentation.patient.DoctorAppointmentBookingOutput;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
* <pre>
* DAO class for doctor appointment booking by specialization
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorAppointmentBookingBySpecializationDAOImpl implements DoctorAppointmentBookingBySpecializationDAO {

  @Override
  public Map<Integer, String> fetchDoctorIdentifier(String specialization) {

    DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();

    if (!doctorAppointmentBookingBySpecializationUtil.validateSpecialization(specialization)) {
      return null;
    } else {
        String sql = "select * from doctors where ";

        Connection connection = DatabaseConnection.instance();
        Statement statement = null;
        try {
          statement = connection.createStatement();
        } catch (SQLException sqlException) {
            System.err.println("Error occurred in establishing database connection!");
            return null;
        }
        ResultSet resultSet = null;

        try {
          /* retrieves doctor list for the specialization */
          resultSet = statement.executeQuery(sql + "specialization=\"" + specialization.toUpperCase(Locale.ROOT) + "\";");

          Map<Integer, String> doctorIdentifierList = new HashMap<>();
          if (!resultSet.next()) {
            return null;
          } else {
              do {
                int doctorID = resultSet.getInt(DatabaseConstants.DOCTOR_ID);
                String doctorName = "";
                doctorName = doctorName + resultSet.getString(DatabaseConstants.FIRST_NAME) + " " + resultSet.getString(DatabaseConstants.LAST_NAME);
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

    DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
    DoctorAppointmentBookingOutput doctorAppointmentBookingOutput = new DoctorAppointmentBookingOutput();

      try {
          if (!doctorAppointmentBookingBySpecializationUtil.validateID(doctorID)) {
            return null;
          } else {
              Connection connection = DatabaseConnection.instance();
              Statement statement = connection.createStatement();
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
                      daysAvailable.add(resultSet.getString(DatabaseConstants.WEEKDAY));
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
  public int checkDoctorExists(int doctorID) {
      String sql = "select distinct doctor_id from doctors;";

      Connection connection = DatabaseConnection.instance();
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
                doctorIDSet.add(resultSet.getInt(DatabaseConstants.DOCTOR_ID));
            } while (resultSet.next());
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

    Connection connection = DatabaseConnection.instance();
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
            identifier = resultSet.getInt(DatabaseConstants.PATIENT_ID);
          } while (resultSet.next());
      }
    } catch (SQLException se) {
        return -1;
    }
    return identifier;
  }

  @Override
  public double fetchDoctorCharges(int doctorID) {

    DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();

    try {
      if (!doctorAppointmentBookingBySpecializationUtil.validateID(doctorID)) {
        return -1;
      } else {
          String sql = "select charges from doctor_specific_charges where doctor_id = ";
          double charges;

          Connection connection = DatabaseConnection.instance();
          Statement statement = connection.createStatement();
          ResultSet resultSet = null;

          try {
            /* retrieves doctor_id(s) list from the database */
            resultSet = statement.executeQuery(sql + doctorID + ";");

            if (!resultSet.next()) {
              return -1;
            } else {
                do {
                  charges = resultSet.getDouble(DatabaseConstants.CHARGES);
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

    if (billingID == 0) {
      return -1;
    } else {
        String sql = "update doctor_appointment set billing_id = ";

        Connection connection = DatabaseConnection.instance();
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

  @Override
  public int addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate, int billingID) throws SQLException {

    DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();

    if (!doctorAppointmentBookingBySpecializationUtil.validateID(doctorID)) {
      return -1;
    }

    if (bookedOnDate == null) {
      return -1;
    }

    if (bookedOnDate != null && bookedOnDate.isEmpty()) {
      return -1;
    }

    if (appointmentDate == null) {
      return -1;
    }

    if (appointmentDate != null && appointmentDate.isEmpty()) {
      return -1;
    }

    Connection connection = DatabaseConnection.instance();
    Statement statement = connection.createStatement();
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;

    int appointmentID;

    try {
      /* retrieves doctor list for the symptoms */
      resultSet = statement.executeQuery("insert ignore into doctor_appointment(patient_id, doctor_id, booked_on_date, booked_for_date, billing_id) values(" + patientID + "," + doctorID + ", \"" + bookedOnDate + "\"" + ", \"" + appointmentDate + "\"," + billingID + ");");
      resultSet1 = statement.executeQuery("select * from doctor_appointment where patient_id = " + patientID + " and doctor_id=" + doctorID + " and booked_for_date=\"" + appointmentDate + "\" and booked_on_date=\"" + bookedOnDate + "\";");

      if(!resultSet1.next()) {
        return -1;
      } else {
          do {
            appointmentID = resultSet1.getInt(DatabaseConstants.APPOINTMENT_ID);
          } while(resultSet1.next());
          return appointmentID;
      }
    } catch (SQLException sqlException) {
        return -1;
    }
  }

}
