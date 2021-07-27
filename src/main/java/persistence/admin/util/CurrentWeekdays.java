package persistence.admin.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Deeksha Sareen
 * This class is reponsible for returning the current weeks dates.
 * If the current day is a saturday/sunday, then the next weeks dates are returned.
 * return:
 *     List<String> week dates
 */
public class CurrentWeekdays {

  private ArrayList<String> getWeekdays(Calendar now) {
    ArrayList<String> dates = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 1; i < 6; i++) {
      dates.add(formatter.format(now.getTime()));
      now.add(Calendar.DAY_OF_YEAR, 1);
    }
    return dates;
  }

  /**
   * This method gets the weekdays
   */
  public List<String> getDates() {
    Calendar now = Calendar.getInstance();
  
    if (now.get(Calendar.DAY_OF_WEEK) == 2) { // Monday
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 3) { // Tuesday
      now.add(Calendar.DAY_OF_YEAR, -1);
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 4) { // Wednesday
      now.add(Calendar.DAY_OF_YEAR, -2);
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 5) { // Thursday
      now.add(Calendar.DAY_OF_YEAR, -3);
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 6) { // Friday
      now.add(Calendar.DAY_OF_YEAR, -4);
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 7) { // Saturday
      now.add(Calendar.DAY_OF_YEAR, 2);
      return getWeekdays(now);
    }
    if (now.get(Calendar.DAY_OF_WEEK) == 1) { // Sunday
      now.add(Calendar.DAY_OF_YEAR, 1);
      return getWeekdays(now);
    }
    return null;

  }
}
