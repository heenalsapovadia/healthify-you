package persistence.admin.dao;

import persistence.admin.model.DoctorRegistration;
import java.sql.SQLException;

/**
* <pre>
* Perform operations for registering doctor in the system in the database
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface DoctorRegistrationDAO {

  /**
   * @param doc
   * @return int
   */
  public int updateDoctorDetails(DoctorRegistration doc) throws SQLException;

  /**
   * @param email
   * @return boolean
   */
  public boolean checkDoctorExists(String email) throws SQLException;

}
