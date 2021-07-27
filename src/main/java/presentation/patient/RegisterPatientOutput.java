package presentation.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import persistence.patient.dao.RegistrationDAO;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.RegistrationUtil;
import persistence.patient.utilImpl.RegistrationUtilImpl;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import presentation.startup.UserLogin;

/**
 *
 * This class controls the flow of patient registration module.
 * 
 * @author Deeksha Sareen
 *
 */
public class RegisterPatientOutput {

	PrintToConsole print = PrintToConsole.getInstance();
	RegistrationUtil util = new RegistrationUtilImpl();
	Scanner sc = new Scanner(System.in);

	public boolean registerPatient() {

		print.printHeader(ScreenTitles.SIGN_UP);

		int sel;
		print.printScreenFields(ScreenFields.USER_EMAIL_INPUT);
		String userId = sc.next();
		print.printScreenFields(ScreenFields.USER_PASSWORD_INPUT);
		String password = sc.next();
		int length = password.length();
		String hidden = "";
		for (int i = 0; i < length; i++) {
			hidden += "*";
		}
		if (userId.endsWith("healthifyyou.com")) {
			print.printScreenFields(CommonErrors.INVALID_REGISTRATION);
			return false;
		}
		Patient.setPatient(userId);
		Patient p = Patient.instance();
		p.setPassword(password);
		p.setPatientEmail(userId);
		p.setPatientType("P");
		print.printSingleNewLine();
		print.printScreenFields(ScreenFields.EMAILID_OUTPUT + "= " + userId);
		print.printScreenFields(ScreenFields.PASSWORD_OUTPUT + "= " + hidden);
		print.printDoubleNewlines();

		List<String> selection = Arrays.asList(ScreenFields.PROCEED, ScreenFields.LOGIN, ScreenFields.EXIT);
		sel = print.printSelection(selection);
		switch (sel) {
		case 1:
			if (util.validateEmail(userId) != null) {
				print.printMethodReturns(util.validateEmail(userId));
				break;
			} else if (util.validatePassword(password) != null) {
				print.printMethodReturns(util.validatePassword(password));
				break;
			} else {
				print.printHeader(ScreenTitles.SIGN_UP);
				print.printSubHeading(ScreenTitles.REGISTRATION);
				print.printScreenFields(ScreenFields.GET_INPUT);
				print.printScreenFields(ScreenFields.FIRST_NAME_INPUT);
				String fname = sc.next();
				if (util.validateNames(fname) != null) {
					do {
						print.printMethodReturns(util.validateNames(fname));
						print.printScreenFields(ScreenFields.FIRST_NAME_INPUT);
						fname = sc.next();

					} while (util.validateNames(fname) != null);
				}
				print.printScreenFields(ScreenFields.LAST_NAME_INPUT);
				String lname = sc.next();
				if (util.validateNames(lname) != null) {
					do {
						print.printMethodReturns(util.validateNames(lname));
						print.printScreenFields(ScreenFields.LAST_NAME_INPUT);
						lname = sc.next();

					} while (util.validateNames(lname) != null);
				}
				if (util.validateNames(fname) == null && util.validateNames(lname) == null) {
					String name = fname + " " + lname;
					p.setPatientName(name);
				}

				print.printScreenFields(ScreenFields.BIRTH_DATE_INPUT);
				String DOB = sc.next();

				if (util.validateDate(DOB) != null) {
					do {
						print.printMethodReturns(util.validateDate(DOB));
						print.printScreenFields(ScreenFields.BIRTH_DATE_INPUT);
						DOB = sc.next();
					} while (util.validateDate(DOB) != null);
				}
				if (util.validateDate(DOB) == null) {
					p.setPatientDob(DOB);
				}

				print.printScreenFields(ScreenFields.CONTACT_INPUT);
				Long contact = sc.nextLong();
				if (util.validateContact(contact) != null) {
					do {
						print.printMethodReturns(util.validateContact(contact));
						print.printScreenFields(ScreenFields.CONTACT_INPUT);
						contact = sc.nextLong();

					} while (util.validateContact(contact) != null);
				}

				if (util.validateContact(contact) == null) {
					p.setPatientContact(contact);
				}

				print.printScreenFields(ScreenFields.CITY_INPUT);
				String city = sc.next();
				if (util.validateCity(city) != null) {
					do {
						print.printMethodReturns(util.validateCity(city));
						print.printScreenFields(ScreenFields.CITY_INPUT);
						city = sc.next();

					} while (util.validateCity(city) != null);
				}

				if (util.validateCity(city) == null) {
					p.setPatientAddress(city);
				}
				print.printScreenFields(ScreenFields.GENDER);
				String gender = sc.next();
				p.setPatientGender(gender);
				p.setPatientType("P");
			}
			List<String> selectionOptions = new ArrayList<>();
			selectionOptions.add(ScreenFields.PROCEED);
			selectionOptions.add(ScreenFields.GO_BACK);
			int sel2 = print.printSelection(selectionOptions);
			switch (sel2) {
			case 1:
				RegistrationDAO dao = new RegistrationDAOImpl();
				print.printMethodReturns(dao.addPatientDetails(p));
				return false;

			case 2:
				print.printScreenFields(ScreenFields.GO_BACK);
				return false;
			}
		case 2:
			UserLogin obj = new UserLogin();
			obj.loginUser();
			break;

		case 3:
			return false;
		}
		return true;

	}

}
