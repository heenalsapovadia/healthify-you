package persistence.patient.utilImpl;

import org.junit.Test;
import presentation.startup.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorAppointmentBookingByNameTest {

  @Test
  /* Test Case 1: invalid doctorID */
  public void validateID_1() throws SQLException {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertFalse(doctorAppointmentBookingByNameUtil.validateID(0));
  }

  @Test
  /* Test Case 2: valid doctorID */
  public void validateID_2() throws SQLException {
    DatabaseConnection.loadDatabaseConnection();
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertTrue(doctorAppointmentBookingByNameUtil.validateID(2));
  }

  @Test
  /* Test Case 3: invalid name */
  public void validateName_1() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertFalse(doctorAppointmentBookingByNameUtil.validateName(null));
  }

  @Test
  /* Test Case 4: invalid name */
  public void validateName_2() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertFalse(doctorAppointmentBookingByNameUtil.validateName(""));
  }

  @Test
  /* Test Case 5: valid name */
  public void validateName_3() {
    DatabaseConnection.loadDatabaseConnection();
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertTrue(doctorAppointmentBookingByNameUtil.validateName("thomas"));
  }

  @Test
  /* Test Case 6: valid name */
  public void validateName_4() {
    DatabaseConnection.loadDatabaseConnection();
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    assertTrue(doctorAppointmentBookingByNameUtil.validateName("heenal"));
  }

  @Test
  /* Test Case 7: invalid date - empty string */
  public void validateDate_1() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = new ArrayList<>();
    assertFalse(doctorAppointmentBookingByNameUtil.validateDate("", datesAvailable));
  }

  @Test
  /* Test Case 8: invalid date - null string */
  public void validateDate_2() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = new ArrayList<>();
    assertFalse(doctorAppointmentBookingByNameUtil.validateDate(null, datesAvailable));
  }

  @Test
  /* Test Case 9: valid date but not in the list */
  public void validateDate_3() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = new ArrayList<>();
    datesAvailable.add("2021-07-24");
    datesAvailable.add("2021-07-26");
    assertFalse(doctorAppointmentBookingByNameUtil.validateDate("2021-07-20", datesAvailable));
  }

  @Test
  /* Test Case 10: valid date and in the list */
  public void validateDate_4() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = new ArrayList<>();
    datesAvailable.add("2021-07-24");
    datesAvailable.add("2021-07-26");
    assertTrue(doctorAppointmentBookingByNameUtil.validateDate("2021-07-24", datesAvailable));
  }

  @Test
  /* Test Case 11: datesAvailable list null */
  public void validateDate_5() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = null;
    assertFalse(doctorAppointmentBookingByNameUtil.validateDate("2021-07-24", datesAvailable));
  }

  @Test
  /* Test Case 11: datesAvailable list null */
  public void validateDate_6() {
    DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
    List<String> datesAvailable = new ArrayList<>();
    assertFalse(doctorAppointmentBookingByNameUtil.validateDate("2021-07-24", datesAvailable));
  }

}
