package presentation;

/**
 * <pre>
 * Contains all common errors to be displayed 
 * throughout the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class CommonErrors {
	private CommonErrors() {}
	
	public static final String invalidDateFormat = "Invalid Date Format! Date should be formatted as yyyy-mm-dd!";
	public static final String greaterDate = "Date greater than today!";

	public static final String invalidAppointmentId = "INVALID Appointment Id entered, Please try again...";

	public static final String emailError = "Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)";
    public static final String errorMessage= "Error Occured";
}
