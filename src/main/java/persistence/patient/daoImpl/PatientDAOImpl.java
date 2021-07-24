/**
 * 
 */
package persistence.patient.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.patient.dao.PatientDAO;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;

/**
 * <pre>
 * Perform operations on patient's data
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PatientDAOImpl implements PatientDAO {
	
	private static final Logger LOGGER = Logger.getLogger(PatientDAOImpl.class.getName());

	@Override
	public Patient getPatient(Patient patient) {
		Connection conn = DatabaseConnection.getConnection();
        String sql = "select * from patients where patient_email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, patient.getPatientEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	patient.setPatientId(rs.getInt("patient_id"));
            	patient.setPatientName(rs.getString("patient_name"));
            	patient.setPatientGender(rs.getString("patient_gender"));
            	patient.setPatientDob(rs.getDate("patient_dob").toString());
            	patient.setPatientEmail(rs.getString("patient_email"));
            	patient.setPatientAddress(rs.getString("patient_address"));
            	patient.setPatientContact(rs.getLong("patient_contact"));
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
		return patient;
	}

	@Override
	public void updateVouchersForPatients(String voucherId, Timestamp datetime, int patientId) {
		Connection connection = DatabaseConnection.getConnection();
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("update patients set voucher_id = ?");
		if(datetime != null) {
			sqlStatement.append(", voucher_credit_date = ?");
		}
		sqlStatement.append(" where patient_id = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			preparedStatement.setString(1, voucherId);
			if(datetime != null) {
				preparedStatement.setTimestamp(2, datetime);
				preparedStatement.setInt(3, patientId);
			}
			else {
				preparedStatement.setInt(2, patientId);
			}
			preparedStatement.executeUpdate();
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
	}
}
