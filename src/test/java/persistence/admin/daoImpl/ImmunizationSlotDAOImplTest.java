package persistence.admin.daoImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import presentation.startup.DatabaseConnection;

/**
 * @author Deeksha Sareen
 *
 */
public class ImmunizationSlotDAOImplTest {

  /**
   * Test method for
   * {@link persistence.admin.daoImpl.ImmunizationSlotDAOImpl#getSlotTiming()}.
   */
  @Test
  public void testGetSlotTiming() {
    DatabaseConnection.loadDatabaseConnection();
    ArrayList<String> slots = new ArrayList<>();
    slots.add("11:00:00 am");
    slots.add("12:00:00 pm");
    slots.add("01:00:00 pm");
    ImmunizationSlotDAOImpl dao = new ImmunizationSlotDAOImpl();
    assertEquals(slots, dao.getSlotTiming());

  }

}
