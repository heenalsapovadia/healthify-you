/**
 * 
 */
/**
 * @author Deeksha Sareen
 *
 */
package persistence.startup.utilImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistence.startup.util.UserLoginUtil;

public class UserLoginUtilImpl implements UserLoginUtil{

	@Override
	public String ValidateEmail(String email) {
		 String emailregex = "^[A-Za-z0-9+_.-]+@(.+)$";  
		 Pattern pattern = Pattern.compile(emailregex);  
		 Matcher matcher = pattern.matcher(email);  
		 if(matcher.matches()==false){
		
			 return "The email ID is invalid.Please enter a valid email address"; 
		 }

		return null;
	}

	@Override
	public String ValidatePassword(String password) {
	     String passwordregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#&()-[{}];;',?/*~$^+-=<>]).{8,20}$";
	     Pattern pattern = Pattern.compile(passwordregex);  
	     Matcher matcher = pattern.matcher(password);  
		 if(matcher.matches()==false){
		
			 return "The password is invalid. Please enter a valid password"; 
		 }
		return null;
	}
	
}