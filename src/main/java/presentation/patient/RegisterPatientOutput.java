package presentation.patient;

import static presentation.common.ScreenFields.birthDateInput;
import static presentation.common.ScreenFields.cityInput;
import static presentation.common.ScreenFields.contactInput;
import static presentation.common.ScreenFields.firstNameInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import persistence.patient.dao.RegistrationDAO;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.RegistrationUtil;
import persistence.patient.utilImpl.RegistrationUtilImpl;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.daoImpl.UserLoginDAOImpl;
import persistence.startup.util.UserLoginUtil;
import persistence.startup.utilImpl.UserLoginUtilImpl;
import presentation.common.CommonConstants;
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

  public boolean registerPatient() {

    PrintToConsole print = PrintToConsole.getInstance();
    print.printHeader(ScreenTitles.signUp);
    Scanner sc = new Scanner(System.in);
    int sel;
    print.printScreenFields(ScreenFields.userEmailInput);
    String userId = sc.next();
    print.printScreenFields(ScreenFields.userPasswordInput);
    String password = sc.next();
    int length = password.length();
    String hidden = "";
    for (int i = 0; i < length; i++) {
      hidden += "*";
    }
    if (userId.endsWith("healthifyyou.com")) {
      print.printScreenFields(CommonErrors.invalidRegistration);
      return false;
    }
    Patient.setPatient(userId);
    Patient p = Patient.getPatient();
    p.setPassword(password);
    p.setPatientEmail(userId);
    p.setPatientType("P");
    print.printSingleNewLine();
    print.printScreenFields(ScreenFields.emailIdOutput + "= " + userId);
    print.printScreenFields(ScreenFields.passwordOutput + "= " + hidden);
    print.printDoubleNewlines();

    List<String> selection = Arrays.asList(ScreenFields.proceed, ScreenFields.login, ScreenFields.exit);
    sel = print.printSelection(selection);
    switch (sel) {
    case 1:
      RegistrationUtil util = new RegistrationUtilImpl();
      if (util.validateEmail(userId) != null) {
        print.printMethodReturns(util.validateEmail(userId));
        break;
      } else if (util.validatePassword(password) != null) {
        print.printMethodReturns(util.validatePassword(password));
        break;
      } else {
        print.printHeader(ScreenTitles.signUp);
        print.printSubHeading(ScreenTitles.registration);
        print.printScreenFields(ScreenFields.getInput);
        print.printScreenFields(ScreenFields.firstNameInput);
        String fname = sc.next();
        if (util.validateNames(fname) != null) {
          do {
            print.printMethodReturns(util.validateNames(fname));
            print.printScreenFields(ScreenFields.firstNameInput);
            fname = sc.next();
            
          } while (util.validateNames(fname) != null);
        }
        print.printScreenFields(ScreenFields.lastNameInput);
        String lname = sc.next();
        if (util.validateNames(lname) != null) {
          do {
            print.printMethodReturns(util.validateNames(lname));
            print.printScreenFields(ScreenFields.firstNameInput);
            lname = sc.next();
            
          } while (util.validateNames(lname) != null);
        }

        if (util.validateNames(fname) == null && util.validateNames(lname) == null) {
          String name = fname + " " + lname;
          p.setPatientName(name);
        }
        print.printScreenFields(ScreenFields.birthDateInput);
        String DOB = sc.next();

        if (util.validateDate(DOB) != null) {
          do {
            print.printMethodReturns(util.validateDate(DOB));
            print.printScreenFields(ScreenFields.birthDateInput);
            DOB = sc.next();

          } while (util.validateDate(DOB) != null);
        }

        if (util.validateDate(DOB) == null) {
          p.setPatientDob(DOB);
        }
        
        print.printScreenFields(ScreenFields.contactInput);
        Long contact = sc.nextLong();
        if (util.validateContact(contact) != null) {
          do {
            print.printMethodReturns(util.validateContact(contact));
            print.printScreenFields(ScreenFields.contactInput);
            contact = sc.nextLong();

          } while (util.validateContact(contact) != null);
        }

        if (util.validateContact(contact) == null) {
          p.setPatientContact(contact);
        }
        
        print.printScreenFields(ScreenFields.cityInput);
        String city = sc.next();
        if (util.validateCity(city) != null) {
          do {
            print.printMethodReturns(util.validateCity(city));
            print.printScreenFields(ScreenFields.cityInput);
            city = sc.next();
            
          } while (util.validateCity(city) != null);
        }

        if (util.validateCity(city) == null) {
          p.setPatientAddress(city);
        }
        print.printScreenFields(ScreenFields.gender);
        String gender = sc.next();
        p.setPatientGender(gender);
        p.setPatientType("P");
      }
      List<String> selectionOptions = new ArrayList<>();
      selectionOptions.add(ScreenFields.proceed);
      selectionOptions.add(ScreenFields.goBack);
      int sel2 = print.printSelection(selectionOptions);
      switch (sel2) {
      case 1:
        RegistrationDAO dao = new RegistrationDAOImpl();
        print.printMethodReturns(dao.addPatientDetails(p));
        return false;

      case 2:
        print.printScreenFields(ScreenFields.goBack);
        return false;
      }
    case 2:
      UserLogin obj = new UserLogin();
      obj.loginUser();
      break;

    case 3:
      print.printScreenFields(ScreenFields.exit);
      return false;
    }
    return true;

  }

}
