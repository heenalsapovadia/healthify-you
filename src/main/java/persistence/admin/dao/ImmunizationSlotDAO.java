package persistence.admin.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface ImmunizationSlotDAO {

	/**
	 * @param
	 * @return Queue<Integer>
	 */
	public Queue<Integer> getDoctorsAvailable();

	/**
	 * @param
	 * @return List<String>
	 */
	public List<String> getSlotTiming();

	/**
	 * @param updatedChoice
	 * @return Map<String, ArrayList<Integer>>
	 */
	public Map<String, ArrayList<Integer>> getAssignedDoctors(int updatedChoice);

	/**
	 * @param updatedRecords
	 * @return void
	 */
	public void updateSlotsInDatabase(LinkedHashMap<String, ArrayList<Integer>> updatedRecords);

}
