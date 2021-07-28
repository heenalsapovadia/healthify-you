package persistence.patient.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* <pre>
* DAO interface for doctor appointment booking by specialization
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public interface DoctorAppointmentBookingBySpecializationDAO {

  /**
  * @param name
  * @return Map<Integer, String>
  */
  public Map<Integer, String> fetchDoctorIdentifier(String name) throws SQLException;

  /**
  * @param doctorID
  * @return Map<Integer, List<String>>
  */
  public Map<Integer, List<String>> fetchDoctorAvailability(int doctorID) throws SQLException;

  /**
  * @param doctorID
  * @return int
  */
  public int checkDoctorExists(int doctorID) throws SQLException;

  /**
  * @param email
  * @return int
  */
  public int checkPatientExists(String email) throws SQLException;

  /**
  * @param doctorID
  * @return double
  */
  public double fetchDoctorCharges(int doctorID) throws SQLException;

  /**
  * @param billingID,appointmentIDList
  * @return int
  */
  public int updateBillingID(int billingID, String appointmentIDList) throws SQLException;

  /**
  * @param patientID,doctorID,bookedOnDate,appointmentDate,billingID
  * @return int
  */
  public int addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate, int billingID) throws SQLException;

}
