package persistence.admin.dao;

import persistence.admin.model.DoctorRegistration;
import java.sql.SQLException;


public interface DoctorRegistrationDAO {

    public int updateDoctorDetails(DoctorRegistration doc) throws SQLException;
    public boolean checkDoctorExists(String email) throws SQLException;

}
