/**
 * 
 */
package persistence.patient.util;

/**
 * @author Deeksha Sareen
 *
 */
public interface ImmunizationBookingUtil {

	/**
	 * @param vaccineId, doses, ageGroup, vaccineGap
	 * @return boolean
	 */
	public boolean vaccineEligibilityCheck(int vaccineId, int doses, String ageGroup, int vaccineGap);

}
