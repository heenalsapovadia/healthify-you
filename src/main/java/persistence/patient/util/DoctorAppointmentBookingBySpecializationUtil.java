package persistence.patient.util;

import java.sql.SQLException;
import java.util.List;

public interface DoctorAppointmentBookingBySpecializationUtil {

  /**
  * @param specialization
  * @return boolean
  */
  public boolean validateSpecialization(String specialization);

  /**
  * @param doctorID
  * @return boolean
  */
  public boolean validateID(int doctorID) throws SQLException;

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
