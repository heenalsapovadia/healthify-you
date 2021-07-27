package persistence.admin.dao;

/**
 * @author Deeksha Sareen:This interface has 1 method that is responsible for
 *         getting the doctor assigned for a particular weekday and slottime
 *
 */
public interface ImmunizationDoctorsDAO {

	/**
	 * @param weekday
	 * @param slotTime
	 * @return integer
	 */
	public int getDoctorAssigned(String weekday, String slotTime);
}
