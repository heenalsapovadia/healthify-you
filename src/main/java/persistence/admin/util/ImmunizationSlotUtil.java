package persistence.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface ImmunizationSlotUtil {
  
  /**
   * @param Map(assign), Queue(doctors)
   * @return void
   */
  public void assignDoctors(Map<String, ArrayList<Integer>> assign, Queue<Integer> doctors);

  /**
   * @param 
   * @return boolean
   */
  public boolean validateWeekend();
}
