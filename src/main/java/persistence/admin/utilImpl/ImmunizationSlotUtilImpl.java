package persistence.admin.utilImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import persistence.admin.daoImpl.ImmunizationSlotDAOImpl;
import persistence.admin.util.ImmunizationSlotUtil;

/**
 * @author Deeksha Sareen: This class is responsbile for assigning slots to
 *         doctors
 *
 */
public class ImmunizationSlotUtilImpl implements ImmunizationSlotUtil {

	/**
	 * This method checks if the day of the week is a weekend
	 *
	 */
	@Override
	public boolean validateWeekend() {
		Calendar now = Calendar.getInstance();
		if (now.get(Calendar.DAY_OF_WEEK) == 7) { // Saturday
			return true;
		}
		if (now.get(Calendar.DAY_OF_WEEK) == 1) { // Sunday
			return true;
		}
		return false;

	}

	private Queue<Integer> rearrange(int index, Queue<Integer> localcopy) {
		int id = 0;
		Queue<Integer> rearrangedQueue = new LinkedList<>(localcopy);
		while (id != index) {
			id = rearrangedQueue.remove();
			rearrangedQueue.add(id);
		}
		return rearrangedQueue;
	}

	/**
	 * This method is responsible for assigning doctors to the slots.
	 */
	@Override
	public void assignDoctors(Map<String, ArrayList<Integer>> assign, Queue<Integer> doctors) {

		LinkedHashMap<String, ArrayList<Integer>> updated = new LinkedHashMap<>();
		Queue<Integer> localcopy = new LinkedList<>(doctors);
		ImmunizationSlotDAOImpl dao = new ImmunizationSlotDAOImpl();

		int index = dao.getDoctorAssigned("Friday", "01:00:00 pm");
		localcopy = rearrange(index, localcopy);

		for (Map.Entry<String, ArrayList<Integer>> entry : assign.entrySet()) {
			String weekday = entry.getKey();
			ArrayList<Integer> weekslots = entry.getValue();
			ArrayList<Integer> slots = new ArrayList<>();
			int id;
			for (int slot : weekslots) {
				id = localcopy.remove();
				slots.add(id);
				localcopy.add(id);
			}
			updated.put(weekday, slots);
		}
		dao.updateSlotsInDatabase(updated);

	}

}
