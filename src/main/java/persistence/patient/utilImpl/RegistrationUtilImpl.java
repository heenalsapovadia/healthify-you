package persistence.patient.utilImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistence.patient.util.RegistrationUtil;

public class RegistrationUtilImpl implements RegistrationUtil {

	@Override
	public String ValidateDate(String Date) {
		 String dateregex = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$";  
		 Pattern pattern = Pattern.compile(dateregex);  
		 Matcher matcher = pattern.matcher(Date);  
		 if(matcher.matches()==false){
		
			 return "The DOB is invalid.Please enter a valid DOB"; 
		 }

		return null;
	}

	@Override
	public String ValidateContact(long Contact) {
		 int length = (int) (Math.log10(Contact) + 1);
		 if(length == 10) {
	            String temp = Contact+"";
	            System.out.println(temp);
	            if(temp.startsWith("902")) {
	                return null;
	            } else {
	                return "The contact number should begin with 902";
	            }
	        } else {
	            return "Contact number should be 10 digits long!";
	        }

	}
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

	@Override
	public String ValidateNames(String name) {
		  String nameregex = "^[a-zA-Z]*$";
		  Pattern pattern = Pattern.compile(nameregex);  
		  Matcher matcher = pattern.matcher(name);  
		  if(matcher.matches()==false){
			
			return "Contains characters other than alphabets. Please retry"; 
		  }
		return null;
	}


	
}
