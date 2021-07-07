package persistence.startup.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import persistence.startup.dao.UserLoginDAO;
import presentation.startup.DatabaseConnection;
import presentation.startup.SHA_Hash;

public class UserLoginDAOImpl implements UserLoginDAO {

	@Override
	public String GetuserDetails(String userId,String password) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		Connection conn = databaseConnection.loadDatabaseConnection();
		SHA_Hash sha= new SHA_Hash();
		ResultSet resultSet = null;
		String query = "SELECT * FROM UserCredentials WHERE User_Id = ?";
		
            PreparedStatement preparedStatement;

            
                try {
					preparedStatement = conn.prepareStatement(query);
				    preparedStatement.setString(1, userId);
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.first()) {
                    	String pwd = resultSet.getObject(2).toString();
                        String userType = resultSet.getObject(3).toString();
                        String hashedpassword=sha.getSHA(password);
                        if(!hashedpassword.equals(pwd))
                        	return "Incorrect Password!";
                        else
                        	return "Successfully logged in " ;
                    	
                    }
                    else{
                    	return "User Id NOT found! Please Register first";
                    }
                } catch (SQLException e) {
					
					e.printStackTrace();
				}
      		return "Error";
           
	}
	
}
