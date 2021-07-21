/**
 * 
 */
package persistence.patient.dao;

import java.sql.Timestamp;
import persistence.patient.model.Patient;

/**
 * <pre>
 * Performs operation on patient's data.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public interface PatientDAO {
	/**
	 * <pre>
	 * Fetches patient's data from database.
	 * </pre>
	 * 
	 * @param patient
	 * @return
	 */
	public Patient getPatient(Patient patient);
	
	public void updateVouchersForPatients(String voucherId, Timestamp datetime, int patientId);
}
