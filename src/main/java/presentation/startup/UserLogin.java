package presentation.startup;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.daoImpl.UserLoginDAOImpl;
import persistence.startup.model.Login;
import persistence.startup.util.UserLoginUtil;
import persistence.startup.utilImpl.UserLoginUtilImpl;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import presentation.patient.RegisterPatientOutput;

/**
 * This class controls the flow of user login module.
 * 
 * @author Deeksha Sareen
 *
 */
public class UserLogin {

	public boolean loginUser() {

		PrintToConsole print = PrintToConsole.getInstance();
		print.printHeader(ScreenTitles.LOGIN);
		print.printScreenFields(ScreenFields.USER_EMAIL_INPUT);
		Scanner sc = new Scanner(System.in);
		String userId = sc.next();
		print.printScreenFields(ScreenFields.USER_PASSWORD_INPUT);
		String password = sc.next();
		int length = password.length();
		String hidden = "";
		for (int i = 0; i < length; i++) {
			hidden += "*";
		}
		Login l = new Login();
		l.setUserPassword(password);
		l.setUserEmail(userId);
		print.printSingleNewLine();
		print.printScreenFields(ScreenFields.EMAILID_OUTPUT + "=" + userId);
		print.printScreenFields(ScreenFields.PASSWORD_OUTPUT + "=" + hidden);
		print.printDoubleNewlines();
		int sel;
		while (true) {
			List<String> selectionOptions = Arrays.asList(ScreenFields.PROCEED, "Patient Sign Up", ScreenFields.EXIT);
			sel = print.printSelection(selectionOptions);
			switch (sel) {
			case 1:
				UserLoginUtil util = new UserLoginUtilImpl();
				if (util.validateEmail(userId) == null && util.validatePassword(password) == null) {
					UserLoginDAO dao = new UserLoginDAOImpl();
					print.printMethodReturns(dao.getuserDetails(l));
					if (dao.getuserDetails(l).equals(CommonErrors.PASSWORD_MISMATCH)
							|| dao.getuserDetails(l).equals(CommonErrors.INVALID_USERID)) {

						List<String> selectionOptions2 = Arrays.asList("Retry", "Sign-Up", "Exit");
						int sel2 = print.printSelection(selectionOptions2);
						if (sel2 == 1) {
							loginUser();
							return false;
						} else if (sel2 == 2) {
							RegisterPatientOutput obj = new RegisterPatientOutput();
							obj.registerPatient();
							return false;
						} else if (sel2 == 3) {
							System.exit(0);
							return false;
						}
					} else if (dao.getuserDetails(l).equals(ScreenFields.SUCCESS_LOGIN)) {
						return false;
					}
				} else {
					if (util.validateEmail(userId) != null) {
						print.printMethodReturns(util.validateEmail(userId));
					}
					if (util.validatePassword(password) != null) {
						print.printMethodReturns(util.validatePassword(password));
					}
					List<String> selectionOptions2 = Arrays.asList("Retry", "Sign-Up", "Exit");
					int sel2 = print.printSelection(selectionOptions2);
					if (sel2 == 1) {
						loginUser();
						return false;
					} else if (sel2 == 2) {
						RegisterPatientOutput obj = new RegisterPatientOutput();
						obj.registerPatient();
						return false;
					} else if (sel2 == 3) {
						System.exit(0);
						return false;
					}
				}
				break;

			case 2:
				RegisterPatientOutput obj = new RegisterPatientOutput();
				obj.registerPatient();
				break;

			case 3:
				print.printScreenFields(ScreenFields.EXIT);
				return false;

			}

		}

	}

}