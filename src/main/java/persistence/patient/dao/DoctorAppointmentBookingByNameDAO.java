package persistence.patient.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DoctorAppointmentBookingByNameDAO {

  public Map<Integer, String> fetchDoctorIdentifier(String name) throws SQLException;
  public Map<Integer, List<String>> fetchDoctorAvailability(int doctorID) throws SQLException;

}
