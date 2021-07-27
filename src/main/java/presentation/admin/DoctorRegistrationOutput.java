package presentation.admin;

import persistence.admin.daoImpl.DoctorRegistrationDAOImpl;
import persistence.admin.model.DoctorRegistration;
import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;
import presentation.common.PrintToConsole;
import static presentation.common.CommonErrors.*;
import static presentation.common.ScreenFields.*;
import static presentation.common.ScreenTitles.DOCTOR_REGISTRATION;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* <pre>
* Output class for doctor registration
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorRegistrationOutput {

  public void registerDoctor() {
    Scanner sc = new Scanner(System.in);
    DoctorRegistration doc = new DoctorRegistration();
    DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
    DoctorRegistrationUtilImpl doctorRegistrationUtilImpl = new DoctorRegistrationUtilImpl();
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader(DOCTOR_REGISTRATION);

    consoleObj.printSingleNewLine();
    consoleObj.printScreenFields(GET_INPUT);

    consoleObj.printScreenFields(EMAIL_INPUT);

    String email = sc.nextLine().trim();
    boolean checkEmail = doctorRegistrationUtilImpl.validateEmail(email);

    if(!checkEmail) {
      do {
        consoleObj.printError(EMAIL_ERROR);
        consoleObj.printScreenFields(EMAIL_INPUT);
        email = sc.nextLine().trim();
        checkEmail = doctorRegistrationUtilImpl.validateEmail(email);
      } while (!checkEmail);
    }

    if(checkEmail) {
      doc.setEmail(email);
    }

    consoleObj.printScreenFields(FIRST_NAME_INPUT);
    String fname = sc.nextLine().trim();
    boolean checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);

    if(!checkFName) {
      do {
        consoleObj.printError(INVALID_FIRST_NAME);
        consoleObj.printScreenFields(FIRST_NAME_INPUT);
        fname = sc.nextLine().trim();
        checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);
      } while (!checkFName);
    }

    if(checkFName) {
      doc.setFirstName(fname);
    }

    consoleObj.printScreenFields(LAST_NAME_INPUT);
    String lname = sc.nextLine().trim();
    boolean checkLName = doctorRegistrationUtilImpl.validateLastName(lname);

    if(!checkLName) {
      do {
        consoleObj.printError(INVALID_LAST_NAME);
        consoleObj.printScreenFields(LAST_NAME_INPUT);
        lname = sc.nextLine().trim();
        checkLName = doctorRegistrationUtilImpl.validateLastName(lname);
      } while (!checkLName);
    }

    if(checkLName) {
      doc.setLastName(lname);
    }

    consoleObj.printScreenFields(PASSWORD_INPUT);
    String password = sc.nextLine().trim();
    boolean checkPassword = doctorRegistrationUtilImpl.validatePassword(password);

    if(!checkPassword) {
      do {
        consoleObj.printError(INVALID_PASSWORD);
        consoleObj.printScreenFields(PASSWORD_INPUT);
        password = sc.nextLine().trim();
        checkPassword = doctorRegistrationUtilImpl.validatePassword(password);
      } while (!checkPassword);
    }

    if(checkPassword) {
      doc.setPassword(password);
    }

    consoleObj.printScreenFields(JOINING_DATE_INPUT);
    String jDate = sc.nextLine().trim();
    boolean checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);

    if(!checkJDate) {
      do {
        consoleObj.printError(INVALID_DATE_FORMAT);
        consoleObj.printScreenFields(JOINING_DATE_INPUT);
        jDate = sc.nextLine().trim();
        checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);
      } while (!checkJDate);
    }

    if(checkJDate) {
      doc.setJoiningDate(jDate);
    }

    consoleObj.printScreenFields(DEGREE_INPUT);
    String degree = sc.nextLine().trim();
    boolean checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);

    if(!checkDegree) {
      do {
        consoleObj.printError("Invalid Degree! Enter valid Graduation Degree (only alphabets and spaces))!");
        consoleObj.printScreenFields(DEGREE_INPUT);
        degree = sc.nextLine().trim();
        checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);
      } while (!checkDegree);
    }

    if(checkDegree) {
      doc.setDegree(degree);
    }

    consoleObj.printScreenFields(SPECIALIZATION_INPUT);
    String specialization = sc.nextLine().trim();
    boolean checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);

    if(!checkSpecialization) {
      do {
        consoleObj.printError("Invalid specialization! Enter valid specialization (only alphabets and spaces))!");
        consoleObj.printScreenFields(SPECIALIZATION_INPUT);

        specialization = sc.nextLine().trim();
        checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);
      } while (!checkSpecialization);
    }

    if(checkSpecialization) {
      doc.setSpecialization(specialization);
    }

    consoleObj.printScreenFields(BIRTH_DATE_INPUT);
    String bdate = sc.nextLine().trim();
    boolean checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);

    if(!checkBDate) {
      do {
        consoleObj.printError(INVALID_DATE_FORMAT);
        consoleObj.printScreenFields(BIRTH_DATE_INPUT);
        bdate = sc.nextLine().trim();
        checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);
      } while (!checkBDate);
    }

    if(checkBDate) {
      doc.setBirthDate(bdate);
    }

    consoleObj.printScreenFields(CONTACT_INPUT);
    Long contactNumber = sc.nextLong();
    boolean checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);

    if(!checkContact) {
      do {
        consoleObj.printScreenFields(CONTACT_INPUT);
        contactNumber = sc.nextLong();
        checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);
      } while (!checkContact);
    }

    if(checkContact) {
      doc.setContactNumber(contactNumber);
    }

    consoleObj.printScreenFields(CITY_INPUT);
    Scanner sc1 = new Scanner(System.in);
    String city = sc1.nextLine().trim();
    boolean checkCity = doctorRegistrationUtilImpl.validateCity(city);

    if(!checkCity) {
      do {
        consoleObj.printError(INVALID_CITY_NAME);
        consoleObj.printScreenFields(CITY_INPUT);
        city = sc.nextLine().trim();
        checkCity = doctorRegistrationUtilImpl.validateCity(city);
      } while (!checkCity);
    }

    if(checkCity) {
      doc.setCity(city);
    }

    consoleObj.printSingleNewLine();
    int choice = 0;

    consoleObj.printScreenFields(SELECTION_FIELD);
    List<String> registrationOptionList = new ArrayList<>();
    registrationOptionList.add("Confirm to proceed to register");
    registrationOptionList.add("Exit");
    consoleObj.printSelection(registrationOptionList);

    choice = sc.nextInt();

    if(choice == 1) {

      doctorRegistrationDAOImpl.updateDoctorDetails(doc);
      System.out.println("Registered successfully!");
    } else if (choice == 2) {
        System.out.println("Thank you for using our service!");
        return;
    } else {
        consoleObj.printError(INVALID_SELECTION);
        return;
    }
  }

}
