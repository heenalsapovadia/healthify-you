package persistence.patient.utilImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import persistence.patient.daoImpl.ImmunizationBookingDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.ImmunizationBookingUtil;
import presentation.common.PrintToConsole;

/**
 * @author Deeksha Sareen: This class contains methods responsible for checking
 *         the eligibility of patient to receive the vaccine shot
 */
public class ImmunizationBookingUtilImpl implements ImmunizationBookingUtil {

	PrintToConsole print = PrintToConsole.getInstance();

	/**
	 * This method is responsible for checking if the patient is eligible to recieve
	 * the vaccine
	 */
	@Override
	public boolean vaccineEligibilityCheck(int vaccineId, int doses, String ageGroup, int vaccineGap) {
		Patient patient = Patient.instance();
		String dob = patient.getPatientDob();
		int patientId = patient.getPatientId();
		int patientAge = getAge(dob);
		if (checkAge(patientAge, ageGroup) != false) {
			if (checkLastDate(vaccineId, patientId, vaccineGap) != false) {
				if (checkDoses(doses, vaccineId, patientId) != false) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method checks the vaccine dose eligibility
	 */
	private boolean checkDoses(int doses, int vaccineId, int patientId) {
		ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
		ArrayList<String> appointmentsdates = new ArrayList<>(dao.getAppointments(vaccineId, patientId));
		if (appointmentsdates.size() > doses) {
			print.printScreenFields("You have exceeded the dose limit of " + doses);
			return false;
		}
		return true;

	}

	/**
	 * This method gets the age from the date of birth
	 */
	public int getAge(String dob) {
		if (dob == null || dob.isEmpty()) {
			return -1;
		}
		String dateofbirth = dob;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		int age = 0;
		try {
			date = sdf.parse(dateofbirth);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			int year = calender.get(Calendar.YEAR);
			int month = calender.get(Calendar.MONTH) + 1;
			int day = calender.get(Calendar.DATE);
			LocalDate localdate = LocalDate.of(year, month, day);
			LocalDate currentdate = LocalDate.now();
			Period period = Period.between(localdate, currentdate);
			age = period.getYears();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}

	/**
	 * This method is responsible for checking if the patient is eligible to recieve
	 * the vaccine based on his age
	 */
	private boolean checkAge(int patientage, String agegroup) {
		String[] agerange = agegroup.split(" to ");
		double leftrange = Double.parseDouble(agerange[0]);
		double rightrange = Double.parseDouble(agerange[1]);
		if (patientage >= leftrange && patientage <= rightrange) {
			return true;
		} else {
			print.printScreenFields("Your age ( " + patientage + " years ) is not eligible for this vaccine");
		}
		return false;

	}

	/**
	 * This method is responsible for checking if the patient is eligibile to
	 * recieve the vaccine based on his last dose for the same vaccine.
	 */
	private boolean checkLastDate(int vaccineId, int patientId, int vaccineGap) {

		ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
		ArrayList<String> appointmentsdates = new ArrayList<>(dao.getAppointments(vaccineId, patientId));
		if (appointmentsdates.isEmpty()) {
			return true;
		}
		String recentDate = Collections.max(appointmentsdates);
		if (getAge(recentDate) >= vaccineGap) {
			return true;
		} else {
			int days = vaccineGap - getAge(recentDate);
			print.printScreenFields("Come back after " + days + " days");
		}
		return false;

	}

}
