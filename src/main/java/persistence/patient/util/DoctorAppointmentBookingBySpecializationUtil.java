package persistence.patient.util;

import java.sql.SQLException;
import java.util.List;

public interface DoctorAppointmentBookingBySpecializationUtil {

  public boolean validateSpecialization(String specialization);
  public boolean validateID(int doctorID) throws SQLException;
  public boolean validateDate(String date, List<String> datesAvailable);
  public int validateEmail(String email) throws SQLException;
}
