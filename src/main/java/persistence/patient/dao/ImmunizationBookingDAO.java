package persistence.patient.dao;

import java.util.List;
import java.util.Map;
import persistence.patient.model.ImmunizationBooking;

/**
 * @author Deeksha Sareen interface DAO class for booking of immunization for
 *         patients
 */

public interface ImmunizationBookingDAO {

	/**
	 * @param
	 * @return List<String>
	 */
	public List<String> getVaccineStock();

	/**
	 * @param vaccineName
	 * @return List<String>
	 */
	public List<String> getVaccineDetail(String vaccineName);

	/**
	 * @param vaccineId, patientId
	 * @return List<String>
	 */
	public List<String> getAppointments(int vaccineId, int patientId);

	/**
	 * @param
	 * @return List<String>
	 */
	public List<String> getSlots();

	/**
	 * @param slotsChosen, vaccineId
	 * @return boolean
	 */
	public boolean assignPatientinDatabase(String slotChosen, int vaccineId);

	/**
	 * @param List of vaccineId
	 * @return Map<Integer, String>
	 */
	public Map<Integer, String> getVaccineDetailById(List<Integer> vaccineId);

	/**
	 * @param
	 * @return List<ImmunizationBooking>
	 */
	public List<ImmunizationBooking> getVaccineIdByPatientId();

}
