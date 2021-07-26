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

import persistence.common.DatabaseConstants;
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
		Connection connection = DatabaseConnection.instance();
        String sqlStatement = "select * from patients where patient_email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
        	preparedStatement.setString(1, patient.getPatientEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	patient.setPatientId(resultSet.getInt(DatabaseConstants.PATIENT_ID));
            	patient.setPatientName(resultSet.getString(DatabaseConstants.PATIENT_NAME));
            	patient.setPatientGender(resultSet.getString(DatabaseConstants.PATIENT_GENDER));
            	patient.setPatientDob(resultSet.getDate(DatabaseConstants.PATIENT_DOB).toString());
            	patient.setPatientEmail(resultSet.getString(DatabaseConstants.PATIENT_EMAIL));
            	patient.setPatientAddress(resultSet.getString(DatabaseConstants.PATIENT_ADDRESS));
            	patient.setPatientContact(resultSet.getLong(DatabaseConstants.PATIENT_CONTACT));
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
		return patient;
	}

	@Override
	public void updateVouchersForPatients(String voucherId, Timestamp datetime, int patientId) {
		Connection connection = DatabaseConnection.instance();
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
