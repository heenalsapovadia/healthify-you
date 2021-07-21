/**
 * 
 */
package persistence.patient.util;

/**
 * @author Deeksha Sareen
 *
 */
public interface ImmunizationBookingUtil {

  public boolean vaccineEligibilityCheck(int vaccineId, int doses, String ageGroup, int vaccineGap);
  
}
