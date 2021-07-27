package persistence.patient.dao;

import persistence.patient.model.Patient;

/**
 * @author Deeksha Sareen: RegistrationDAO interface
 *
 */
public interface RegistrationDAO {

	/**
	 * @param Patient object
	 * @return String
	 */
	public String addPatientDetails(Patient p);
}
