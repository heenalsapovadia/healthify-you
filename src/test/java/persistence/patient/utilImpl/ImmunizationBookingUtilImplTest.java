package persistence.patient.utilImpl;
import static org.junit.Assert.*;

import org.junit.Test;

import persistence.patient.model.Patient;

/**
 * @author Deeksha Sareen
 *
 */
public class ImmunizationBookingUtilImplTest {

  /**
   * Test method for getting age from DOB.
   */
  @Test
  public void testGetAge() {
    String dob = "2004-09-23";
    ImmunizationBookingUtilImpl util = new ImmunizationBookingUtilImpl();
    assertEquals(16, util.getAge(dob));
  }
  @Test
  public void testGetAgeBlank() {
    String dob = "";
    ImmunizationBookingUtilImpl util = new ImmunizationBookingUtilImpl();
    assertEquals(-1, util.getAge(dob));
  }
  @Test
  public void testGetAgeNull() {
    String dob = null;
    ImmunizationBookingUtilImpl util = new ImmunizationBookingUtilImpl();
    assertEquals(-1, util.getAge(dob));
  }
  
}
