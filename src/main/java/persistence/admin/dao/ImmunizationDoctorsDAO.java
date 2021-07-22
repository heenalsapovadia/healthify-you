/**
 * 
 */
package persistence.admin.dao;

/**
 * @author Deeksha Sareen
 *
 */
public interface ImmunizationDoctorsDAO {

  public int getDoctorAssigned(String weekday , String slotTime);
}
