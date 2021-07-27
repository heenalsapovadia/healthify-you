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
	
	public static final String INVALID_DATE_FORMAT = "Invalid Date Format! Date should be formatted as yyyy-mm-dd!";
	public static final String GREATER_DATE = "Date greater than today!";
	public static final String INVALID_APPOINTMENT_ID = "INVALID Appointment Id entered, Please try again...";
	public static final String EMAIL_ERROR = "Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)";
	public static final String INVALID_SELECTION = "Invalid Selection! Enter a valid value!";
	public static final String ERROR_MESSAGE= "Error Occured";
	public static final String INVALID_PASSWORD = "Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
			                                     "digit, a lowercase and uppercase alphabet, one special character except white-spaces" ;
	public static final String INVALID_NAME= "Invalid name. Contains characters other than alphabets";
	public static final String INVALID_CITY = "Invalid city. Contains characters other than alphabets";
	public static final String INVALID_DISPLAY = "No option set to display";
	public static final String INVALID_USERID = "User ID not found! Please Sign up first";
	public static final String PASSWORD_MISMATCH = "Incorrect Password!";
	public static final String INVALID_REGISTRATION = "You are not allowed to Register !";
	public static final String INVALID_WEEKDAY = "Slots can only be assigned on the weekend. Come back again on the weekend !";
	public static final String INVALID_CHECK_UP_ID = "Invalid Lab CheckUp Id, Enter Again";
	public static final String SMALLER_DATE = "Date smaller than today!";
	public static final String NO_REPORTS = "No Reports Found";
	public static final String NO_RECEIPTS = "No Receipts Found";
	public static final String NO_DATA_FOR_COVID = "No data available for Covid Shot analysis";
	public static final String MEDICINE_NAME_NOT_FOUND = "Medicine Name Not Found! Try again";
	public static final String INVALID_FIRST_NAME = "Invalid First Name! Enter valid First Name (only alphabets and spaces allowed)!";
	public static final String INVALID_LAST_NAME = "Invalid Last Name! Enter valid Last Name (only alphabets and spaces allowed)!";
	public static final String INVALID_CITY_NAME = "Invalid City Name! Enter valid City Name (only alphabets and spaces allowed)!";
	public static final String NOT_AVAILABLE = "Not available!";

}
