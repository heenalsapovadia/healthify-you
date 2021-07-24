package persistence.patient.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* <pre>
* DAO interface for doctor appointment booking by name
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public interface DoctorAppointmentBookingByNameDAO {

  public Map<Integer, String> fetchDoctorIdentifier(String name) throws SQLException;
  public Map<Integer, List<String>> fetchDoctorAvailability(int doctorID) throws SQLException;
  public int checkDoctorExists(int doctorID) throws SQLException;
  public int checkPatientExists(String email) throws SQLException;
  public double fetchDoctorCharges(int doctorID) throws SQLException;
  public int updateBillingID(int billingID, String appointmentIDList) throws SQLException;
  public int addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate, int billingID) throws SQLException;

}
