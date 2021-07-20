package persistence.admin.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface ImmunizationSlotDAO {

  public Queue<Integer> getDoctorsAvailable();

  public List<String> getSlotTiming();

  public Map<String, ArrayList<Integer>> getAssignedDoctors(int updatedChoice);
  
  public void updateSlotsInDatabase(LinkedHashMap<String, ArrayList<Integer>> updatedRecords);
  
  public int getLastDoctorAssigned();
}
