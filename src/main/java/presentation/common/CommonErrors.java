package presentation.common;

/**
 * <pre>
 * Contains all common errors to be displayed 
 * throughout the application.
 * </pre>
 * 
 * @author G12
 *
 */
public class CommonErrors {
	private CommonErrors() {}
	
	public static final String invalidDateFormat = "Invalid Date Format! Date should be formatted as yyyy-mm-dd!";
	public static final String greaterDate = "Date greater than today!";
	public static final String invalidAppointmentId = "INVALID Appointment Id entered, Please try again...";
	public static final String emailError = "Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)";
	public static final String invalidSelection = "Invalid Selection! Enter a valid value!";
	public static final String errorMessage= "Error Occured";
	public static final String invalidPassword = "Password is invalid! Enter a valid password";
	public static final String invalidName = "Invalid name. Contains characters other than alphabets";
	public static final String invalidCity = "Invalid city. Contains characters other than alphabets";
	public static final String invalidDisplay = "No option set to display";
	public static final String userIDInvalid = "User ID not found! Please Sign up first";
	public static final String userPasswordUnmatch = "Incorrect Password!";
	public static final String invalidRegistration = "You are not allowed to Register !";
	public static final String invalidWeekday = "Slots can only be assigned on the weekend. Come back again on the weekend !";
	public static final String invalidCheckUpId = "Invalid Lab CheckUp Id, Enter Again";
	public static final String smallerDate = "Date smaller than today!";
	public static final String NO_REPORTS = "No Reports Found";
	public static final String NO_RECEIPTS = "No Receipts Found";

}
