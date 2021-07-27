/**
 * This class consists of implementations of user login validations
 * @author Deeksha Sareen
 *
 */
package persistence.startup.utilImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistence.startup.util.UserLoginUtil;
import presentation.common.CommonErrors;

public class UserLoginUtilImpl implements UserLoginUtil {

	/**
	 * This method validates the email format
	 */
	@Override
	public String validateEmail(String email) {
		String emailregex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() == false) {
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
		if (matcher.matches() == false) {
			return CommonErrors.INVALID_PASSWORD;
		}

		return null;
	}

}