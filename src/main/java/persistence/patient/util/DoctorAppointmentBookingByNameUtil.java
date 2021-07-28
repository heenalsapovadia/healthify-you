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

  /**
  * @param doctorID
  * @return boolean
  */
  public boolean validateID(int doctorID) throws SQLException;

  /**
  * @param name
  * @return boolean
  */
  public boolean validateName(String name);

  /**
  * @param date,datesAvailable
  * @return boolean
  */
  public boolean validateDate(String date, List<String> datesAvailable);

  /**
  * @param email
  * @return int
  */
  public int validateEmail(String email) throws SQLException;

}
