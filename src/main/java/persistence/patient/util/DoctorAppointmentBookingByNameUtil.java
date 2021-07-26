package persistence.patient.util;

import java.sql.SQLException;
import java.util.List;

/**
* <pre>
* Interface for handling utility methods of doctor appointment booking
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public interface DoctorAppointmentBookingByNameUtil {
  public boolean validateID(int doctorID) throws SQLException;
  public boolean validateName(String name);
  public boolean validateDate(String date, List<String> datesAvailable);
  public int validateEmail(String email) throws SQLException;
}
