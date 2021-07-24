package persistence.patient.utilImpl;

import org.junit.Test;
import presentation.startup.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorAppointmentBookingBySpecializationTest {

    @Test
    /* Test Case 1: invalid doctorID */
    public void validateID_1() throws SQLException {
        DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
        assertFalse(doctorAppointmentBookingBySpecializationUtil.validateID(0));
    }

    @Test
    /* Test Case 2: valid doctorID */
    public void validateID_2() throws SQLException {
      DatabaseConnection.loadDatabaseConnection();
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      assertTrue(doctorAppointmentBookingBySpecializationUtil.validateID(2));
    }

    @Test
    /* Test Case 3: valid name */
    public void validateName_1() {
      DatabaseConnection.loadDatabaseConnection();
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      assertTrue(doctorAppointmentBookingBySpecializationUtil.validateSpecialization("ENT SPECIALIST"));
    }

    @Test
    /* Test Case 4: valid name */
    public void validateName_2() {
      DatabaseConnection.loadDatabaseConnection();
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateSpecialization("GYNAEC"));
    }

    @Test
    /* Test Case 5: invalid date - empty string */
    public void validateDate_1() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = new ArrayList<>();
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateDate("", datesAvailable));
    }

    @Test
    /* Test Case 6: invalid date - null string */
    public void validateDate_2() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = new ArrayList<>();
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateDate(null, datesAvailable));
    }

    @Test
    /* Test Case 7: valid date but not in the list */
    public void validateDate_3() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = new ArrayList<>();
      datesAvailable.add("2021-07-24");
      datesAvailable.add("2021-07-26");
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateDate("2021-07-20", datesAvailable));
    }

    @Test
    /* Test Case 8: valid date and in the list */
    public void validateDate_4() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = new ArrayList<>();
      datesAvailable.add("2021-07-24");
      datesAvailable.add("2021-07-26");
      assertTrue(doctorAppointmentBookingBySpecializationUtil.validateDate("2021-07-24", datesAvailable));
    }

    @Test
    /* Test Case 9: datesAvailable list null */
    public void validateDate_5() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = null;
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateDate("2021-07-24", datesAvailable));
    }

    @Test
    /* Test Case 10: datesAvailable list null */
    public void validateDate_6() {
      DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
      List<String> datesAvailable = new ArrayList<>();
      assertFalse(doctorAppointmentBookingBySpecializationUtil.validateDate("2021-07-24", datesAvailable));
    }

}
