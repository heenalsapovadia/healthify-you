package persistence.patient.utilImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistence.patient.util.RegistrationUtil;
import presentation.CommonErrors;
import presentation.ScreenFields;

public class RegistrationUtilImpl implements RegistrationUtil {

	@Override
	public String ValidateDate(String Date) {
		 String dateregex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";  
		 Pattern pattern = Pattern.compile(dateregex);  
		 Matcher matcher = pattern.matcher(Date);  
		 if(matcher.matches()==false || Date == null || Date ==""){
		
			 return CommonErrors.invalidDateFormat; 
		 }

		return null;
	}
    
	@Override
	public String ValidateContact(Long Contact) {
		 int length = (int) (Math.log10(Contact) + 1);
		 if(length == 10) {
	            String temp = Contact+"";
	            if(temp.startsWith("902") && !temp.matches("^[ a-zA-Z]*$")) {
	                return null;
	            } else {
	                return ScreenFields.contactBeginWith;
	            }
	        } else {
	            return ScreenFields.contactLength;
	            		
	        }

	}
    @Override
	public String ValidateEmail(String email) {
    	 String emailregex="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		 Pattern pattern = Pattern.compile(emailregex);  
		 Matcher matcher = pattern.matcher(email);  
		 
		 if(matcher.matches()==false || email==null || email==""){
		
			 return CommonErrors.emailError; 
		 }

		return null;
	}
	
	@Override
	public String ValidatePassword(String password) {
		  String passwordregex = "^(?=.*[0-9])"
	                + "(?=.*[a-z])(?=.*[A-Z])"
	                + "(?=.*[@#$%^&+=])"
	                + "(?=\\S+$).{8,20}$";
		  Pattern pattern = Pattern.compile(passwordregex);  
		  Matcher matcher = pattern.matcher(password);  
		  if(matcher.matches()==false || password==null || password==""){
			
			return CommonErrors.invalidPassword; 
		  }
		return null;
		
	}

	@Override
	public String ValidateNames(String name) {
		  String nameregex = "^[a-zA-Z]*$";
		  Pattern pattern = Pattern.compile(nameregex);  
		  Matcher matcher = pattern.matcher(name);  
		  if(matcher.matches()==false || name==null || name==""){
			
			return CommonErrors.invalidName; 
		  }
		return null;
	}
	
	@Override
	public String ValidateCity(String city) {
		String cityregex = "^[ a-zA-Z]*$";
		Pattern pattern = Pattern.compile(cityregex);
		Matcher matcher = pattern.matcher(city); 
		if(city==null || city.equals("") || matcher.matches()==false ) {
          return CommonErrors.invalidCity;
        }
		return null;
	}


	
}
