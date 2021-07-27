/**
 * 
 */
package persistence.admin.utilImpl;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Test;

/**
 * @author Deeksha Sareen
 *
 */
public class ImmunizationSlotUtilImplTest {

  /**
   * Test method for
   * validateWeekend()}.
   */
  @Test
  public void testValidateWeekendFalse() {
    Calendar now = Calendar.getInstance();

    if (now.get(Calendar.DAY_OF_WEEK) == 3) { // Tuesday
      ImmunizationSlotUtilImpl util = new ImmunizationSlotUtilImpl();
      assertFalse(util.validateWeekend());
    }

  }

  @Test
  public void testValidateWeekendTrue() {
    Calendar now = Calendar.getInstance();

    if (now.get(Calendar.DAY_OF_WEEK) == 1) { // Sunday
      ImmunizationSlotUtilImpl util = new ImmunizationSlotUtilImpl();
      assertTrue(util.validateWeekend());
    }

  }
}