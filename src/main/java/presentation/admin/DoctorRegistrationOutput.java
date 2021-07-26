package presentation.admin;

import persistence.admin.daoImpl.DoctorRegistrationDAOImpl;
import persistence.admin.model.DoctorRegistration;
import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;

import static presentation.common.CommonErrors.emailError;
import static presentation.common.ScreenFields.*;
import static presentation.common.ScreenTitles.doctorRegistration;

import java.sql.SQLException;
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
        consoleObj.printHeader(doctorRegistration);

        consoleObj.printSingleNewLine();
        consoleObj.printScreenFields("Please enter the details below:\n");

        consoleObj.printScreenFields(emailInput);

        String email = sc.nextLine().trim();
        boolean checkEmail = doctorRegistrationUtilImpl.validateEmail(email);

        if(!checkEmail) {
          do {
            consoleObj.printError(emailError);
            consoleObj.printScreenFields(emailInput);
            email = sc.nextLine().trim();
            checkEmail = doctorRegistrationUtilImpl.validateEmail(email);
          } while (!checkEmail);
        }

        if(checkEmail) {
            doc.setEmail(email);
        }

        consoleObj.printScreenFields(firstNameInput);
        String fname = sc.nextLine().trim();
        boolean checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);

        if(!checkFName) {
            do {
                consoleObj.printError("Invalid First Name! Enter valid First Name (only alphabets))!");
                consoleObj.printScreenFields(firstNameInput);
                fname = sc.nextLine().trim();
                checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);
            } while (!checkFName);
        }

        if(checkFName) {
                doc.setFirstName(fname);
        }

        consoleObj.printScreenFields(lastNameInput);
        String lname = sc.nextLine().trim();
        boolean checkLName = doctorRegistrationUtilImpl.validateLastName(lname);

        if(!checkLName) {
            do {
                consoleObj.printError("Invalid Last Name! Enter valid Last Name (only alphabets))!");
                consoleObj.printScreenFields(lastNameInput);
                lname = sc.nextLine().trim();
                checkLName = doctorRegistrationUtilImpl.validateLastName(lname);
            } while (!checkLName);
        }

        if(checkLName) {
            doc.setLastName(lname);
        }

        consoleObj.printScreenFields(passwordInput);
        String password = sc.nextLine().trim();
        boolean checkPassword = doctorRegistrationUtilImpl.validatePassword(password);

        if(!checkPassword) {
            do {
                consoleObj.printError("Invalid Password! Enter valid password! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)");
                consoleObj.printScreenFields(passwordInput);
                password = sc.nextLine().trim();
                checkPassword = doctorRegistrationUtilImpl.validatePassword(password);
            } while (!checkPassword);
        }

        if(checkPassword) {
            doc.setPassword(password);
        }

        consoleObj.printScreenFields(joiningDateInput);
        String jDate = sc.nextLine().trim();
        boolean checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);

        if(!checkJDate) {
            do {
                consoleObj.printError("Invalid Date! Enter date in valid format! (should be YYYY-MM-DD)");
                consoleObj.printScreenFields(joiningDateInput);
                jDate = sc.nextLine().trim();
                checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);
            } while (!checkJDate);
        }

        if(checkJDate) {
            doc.setJoiningDate(jDate);
        }

        consoleObj.printScreenFields(degreeInput);
        String degree = sc.nextLine().trim();
        boolean checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);

        if(!checkDegree) {
            do {
                consoleObj.printError("Invalid Degree! Enter valid Graduation Degree (only alphabets and spaces))!");
                consoleObj.printScreenFields(degreeInput);
                degree = sc.nextLine().trim();
                checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);
            } while (!checkDegree);
        }

        if(checkDegree) {
            doc.setDegree(degree);
        }

        consoleObj.printScreenFields(specializationInput);
        String specialization = sc.nextLine().trim();
        boolean checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);

        if(!checkSpecialization) {
            do {
                consoleObj.printError("Invalid specialization! Enter valid specialization (only alphabets and spaces))!");
                consoleObj.printScreenFields(specializationInput);

                specialization = sc.nextLine().trim();
                checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);
            } while (!checkSpecialization);
        }

        if(checkSpecialization) {
            doc.setSpecialization(specialization);
        }

        consoleObj.printScreenFields(birthDateInput);
        String bdate = sc.nextLine().trim();
        boolean checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);

        if(!checkBDate) {
            do {
                consoleObj.printError("Invalid Date! Enter date in valid format! (should be YYYY-MM-DD");
                consoleObj.printScreenFields(birthDateInput);
                bdate = sc.nextLine().trim();
                checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);
            } while (!checkBDate);
        }

        if(checkBDate) {
            doc.setBirthDate(bdate);
        }

        consoleObj.printScreenFields(contactInput);
        Long contactNumber = sc.nextLong();
        boolean checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);

        if(!checkContact) {
            do {
                consoleObj.printScreenFields(contactInput);
                contactNumber = sc.nextLong();
                checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);
            } while (!checkContact);
        }

        if(checkContact) {
            doc.setContactNumber(contactNumber);
        }

        consoleObj.printScreenFields(cityInput);
        Scanner sc1 = new Scanner(System.in);
        String city = sc1.nextLine().trim();
        boolean checkCity = doctorRegistrationUtilImpl.validateCity(city);

        if(!checkCity) {
            do {
                consoleObj.printError("Invalid city! Enter valid city (only alphabets and spaces))!");
                consoleObj.printScreenFields(cityInput);
                city = sc.nextLine().trim();
                checkCity = doctorRegistrationUtilImpl.validateCity(city);
            } while (!checkCity);
        }

        if(checkCity) {
                doc.setCity(city);
        }

        consoleObj.printSingleNewLine();
        int choice = 0;

        consoleObj.printScreenFields("Select one of the below options:");
        consoleObj.printScreenFields("1. Confirm to proceed to register");
        consoleObj.printScreenFields("2. Exit");

        consoleObj.printScreenFields("Please enter your selection below:");
        choice = sc.nextInt();

        if(choice == 1) {
          doctorRegistrationDAOImpl.updateDoctorDetails(doc);
          System.out.println("Registered successfully!");
        } else if (choice == 2) {
            System.out.println("Thank you for using our service!");
            return;
        } else {
            System.out.println("Invalid choice!");
            return;
        }
    }

}
