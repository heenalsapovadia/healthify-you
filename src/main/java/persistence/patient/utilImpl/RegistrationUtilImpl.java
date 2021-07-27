package persistence.patient.utilImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistence.patient.util.RegistrationUtil;
import presentation.common.CommonErrors;
import presentation.common.ScreenFields;

/**
 * This class contains the implementation of the Registration interface.
 * 
 * @author Deeksha Sareen
 *
 */
public class RegistrationUtilImpl implements RegistrationUtil {

	/**
	 * This method validates the date format
	 */
	@Override
	public String validateDate(String Date) {
		String dateregex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
		Pattern pattern = Pattern.compile(dateregex);
		Matcher matcher = pattern.matcher(Date);
		if (matcher.matches() == false || Date == null || Date.equals("")) {

			return CommonErrors.INVALID_DATE_FORMAT;
		}

		return null;
	}

	/**
	 * This method validates the contact format
	 */
	@Override
	public String validateContact(Long Contact) {
		int length = (int) (Math.log10(Contact) + 1);
		if (length == 10) {
			String temp = Contact + "";
			if (temp.startsWith("902") && !temp.matches("^[ a-zA-Z]*$")) {
				return null;
			} else {
				return ScreenFields.CONTACT_BEGIN_WITH;
			}
		} else {
			return ScreenFields.CONTACT_LENGTH;

		}

	}

	/**
	 * This method validates the Email format
	 */
	@Override
	public String validateEmail(String email) {
		String emailregex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches() == false || email == null || email.equals("")) {

			return CommonErrors.EMAIL_ERROR;
		}

		return null;
	}

	/**
	 * This method validates the password format
	 */
	@Override
	public String validatePassword(String password) {
		String passwordregex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(passwordregex);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches() == false || password == null || password.equals("")) {

			return CommonErrors.INVALID_PASSWORD;
		}
		return null;

	}

	/**
	 * This method validates the name format
	 */
	@Override
	public String validateNames(String name) {
		String nameregex = "^[a-zA-Z]*$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches() == false || name == null || name.equals("")) {

			return CommonErrors.INVALID_NAME;
		}
		return null;
	}

	/**
	 * This method validates the city name format
	 */
	@Override
	public String validateCity(String city) {
		String cityregex = "^[ a-zA-Z]*$";
		Pattern pattern = Pattern.compile(cityregex);
		Matcher matcher = pattern.matcher(city);
		if (city == null || city.equals("") || matcher.matches() == false) {
			return CommonErrors.INVALID_CITY;
		}
		return null;
	}

}
