package persistence.startup.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.model.Login;
import persistence.startup.util.UserUtil;
import persistence.startup.utilImpl.UserUtilImpl;
import presentation.common.CommonErrors;
import presentation.common.ScreenFields;
import presentation.startup.DatabaseConnection;
import presentation.startup.SHA_Hash;

/**
 * This class is reponsible for getting a specific user details from the
 * database
 * 
 * @author Deeksha Sareen
 */
public class UserLoginDAOImpl implements UserLoginDAO {

	/* This method gets the user credentials of a user from the database */
	@Override
	public String getuserDetails(Login l) {
		Connection conn = DatabaseConnection.instance();
		SHA_Hash sha = new SHA_Hash();
		ResultSet resultSet = null;
		String query = "SELECT * FROM UserCredentials WHERE User_Id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, l.getUserEmail());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.first()) {
				String pwd = resultSet.getObject(2).toString();
				String userType = resultSet.getObject(3).toString();
				String hashedpassword = sha.getSHA(l.getUserPassword()); // matches the crypted password from the
																			// database
				if (!hashedpassword.equals(pwd))
					return CommonErrors.PASSWORD_MISMATCH;
				else {
					UserUtil userUtil = new UserUtilImpl();
					userUtil.loadUser(l.getUserEmail(), userType);
					return ScreenFields.SUCCESS_LOGIN;
				}

			} else {
				return CommonErrors.INVALID_USERID;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return CommonErrors.ERROR_MESSAGE;

	}

}
