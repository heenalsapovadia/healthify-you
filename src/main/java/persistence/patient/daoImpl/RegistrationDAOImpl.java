package persistence.patient.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistence.patient.dao.RegistrationDAO;
import persistence.patient.util.RegistrationUtil;
import persistence.patient.utilImpl.RegistrationUtilImpl;
import presentation.startup.DatabaseConnection;
import presentation.startup.SHA_Hash;

public class RegistrationDAOImpl implements RegistrationDAO{

	@Override
	public String addPatientDetails(String name, String DOB, double contact, String gender, String city, String email, String password, String type) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement;
		SHA_Hash sha= new SHA_Hash();
		String HashedPassword= sha.getSHA(password);
		ResultSet resultSet = null;
		String query = "SELECT * FROM UserCredentials WHERE User_Id = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				String sql = "insert into UserCredentials(User_Id, User_Password, User_Type) values (?,?,?)"; 
				String sql2= "insert into patients (patient_name, patient_gender,patient_dob,patient_email,patient_address,patient_contact) values (?,?,?,?,?,?)";
				RegistrationUtil util= new RegistrationUtilImpl();
				RegistrationDAO dao = new RegistrationDAOImpl();
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, HashedPassword);
				preparedStatement.setString(3, type);
				preparedStatement.execute();
				preparedStatement=conn.prepareStatement(sql2);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, gender);
				preparedStatement.setString(3, DOB);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, city);
				preparedStatement.setDouble(6, contact);
				preparedStatement.execute();
				return "Successfully Registered";
				
			}
			else{
				return "User details already exist";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return "Error occured";
	}

}
