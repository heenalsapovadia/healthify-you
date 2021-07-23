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
  public boolean addDoctorAppointment(int patientID, int doctorID, String bookedOnDate, String appointmentDate) throws SQLException;

}
