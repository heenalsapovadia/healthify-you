package persistence.patient.dao;

import java.util.List;
import java.util.Map;
import persistence.patient.model.ImmunizationBooking;

/**
 * @author Deeksha Sareen
 *
 */

public interface ImmunizationBookingDAO {

	public List<String> getVaccineStock();

	public List<String> getVaccineDetail(String vaccineName);

	public List<String> getAppointments(int vaccineId, int patientId);

	public List<String> getSlots();

	public boolean assignPatientinDatabase(String slotChosen, int vaccineId);

	/**
	 * <pre>
	 * Returns map of vaccine name with their corresponding id.
	 * </pre>
	 * 
	 * @param vaccineId
	 * @return map
	 */
	public Map<Integer, String> getVaccineDetailById(List<Integer> vaccineId);

	/**
	 * <pre>
	 * Returns list of immunization booking objects.
	 * </pre>
	 * 
	 * @return
	 */
	public List<ImmunizationBooking> getVaccineIdByPatientId();

}
