package persistence.admin.dao;

import persistence.admin.model.DoctorRegistration;
import java.sql.SQLException;

/**
 * <pre>
 * DAO interface for doctor registration
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 **/

public interface DoctorRegistrationDAO {

    public int updateDoctorDetails(DoctorRegistration doc) throws SQLException;
    public boolean checkDoctorExists(String email) throws SQLException;

}
