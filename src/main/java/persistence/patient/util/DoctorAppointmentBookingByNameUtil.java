package persistence.patient.util;

import java.sql.SQLException;

public interface DoctorAppointmentBookingByNameUtil {
  public boolean validateID(int doctorID) throws SQLException;
  public boolean validateName(String name);
}
