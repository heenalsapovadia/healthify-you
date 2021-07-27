package persistence.patient.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistence.patient.dao.RegistrationDAO;
import persistence.patient.model.Patient;
import presentation.common.CommonErrors;
import presentation.startup.DatabaseConnection;
import presentation.startup.SHA_Hash;

/**
 * @author Deeksha Sareen: This class implements the RegistrationDAO interface
 *         and is responsible for adding registered patient detials into the
 *         database.
 *
 */
public class RegistrationDAOImpl implements RegistrationDAO {

	@Override
	public String addPatientDetails(Patient p) {

		Connection conn = DatabaseConnection.instance();
		PreparedStatement preparedStatement;
		SHA_Hash sha = new SHA_Hash();
		String hashedPassword = sha.getSHA(p.getPassword());
		ResultSet resultSet = null;
		String query = "SELECT * FROM UserCredentials WHERE User_Id = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			if (p.getPatientEmail() == null) {
				return CommonErrors.ERROR_MESSAGE;
			}
			preparedStatement.setString(1, p.getPatientEmail());
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {

				String sql = "insert into UserCredentials(User_Id, User_Password, User_Type) values (?,?,?)";
				String sql2 = "insert into patients (patient_name, patient_gender,patient_dob,patient_email,patient_address,patient_contact) values (?,?,?,?,?,?)";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, p.getPatientEmail());
				preparedStatement.setString(2, hashedPassword);
				preparedStatement.setString(3, p.getPatientType());
				preparedStatement.execute();
				preparedStatement = conn.prepareStatement(sql2);
				preparedStatement.setString(1, p.getPatientName());
				preparedStatement.setString(2, p.getPatientGender());
				preparedStatement.setString(3, p.getPatientDob());
				preparedStatement.setString(4, p.getPatientEmail());
				preparedStatement.setString(5, p.getPatientAddress());
				preparedStatement.setDouble(6, p.getPatientContact());
				preparedStatement.execute();
				return "Successfully Registered";
			} else {
				return "User details already exist";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return CommonErrors.ERROR_MESSAGE;
	}

}
