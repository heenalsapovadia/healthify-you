package presentation.admin;

import persistence.admin.daoImpl.DoctorRegistrationDAOImpl;
import persistence.admin.model.DoctorRegistration;
import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;
import presentation.CommonConstants;

import java.sql.SQLException;
import java.util.Scanner;


public class DoctorRegistrationOutput {

    public void registerDoctor() {
        Scanner sc = new Scanner(System.in);
        DoctorRegistration doc = new DoctorRegistration();
        DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
        DoctorRegistrationUtilImpl doctorRegistrationUtilImpl = new DoctorRegistrationUtilImpl();

        System.out.println(CommonConstants.titleSpace+CommonConstants.titleSpace+CommonConstants.titleSpace+CommonConstants.titleSpace+":: Registration for doctors of the hospital ::");

        System.out.println();
        System.out.println("Please enter the details below:\n");


        System.out.println("Enter your email address");
        String email = sc.nextLine().trim();
        boolean checkEmail = doctorRegistrationUtilImpl.validateEmail(email);

        if(!checkEmail) {
            do {
                System.err.println("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)");
                System.out.println("Enter your email address");
                email = sc.nextLine().trim();
                checkEmail = doctorRegistrationUtilImpl.validateEmail(email);
            } while (!checkEmail);
        }

        if(checkEmail) {
            doc.setEmail(email);
        }



        System.out.println("Enter your first name");
        String fname = sc.nextLine().trim();
        boolean checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);

        if(!checkFName) {
            do {
                System.err.println("Invalid First Name! Enter valid First Name (only alphabets))!");
                System.out.println("Enter your first name");
                fname = sc.nextLine().trim();
                checkFName = doctorRegistrationUtilImpl.validateFirstName(fname);
            } while (!checkFName);
        }

        if(checkFName) {
                doc.setFirstName(fname);
        }


        System.out.println("Enter your last name");
        String lname = sc.nextLine().trim();
        boolean checkLName = doctorRegistrationUtilImpl.validateLastName(lname);

        if(!checkLName) {
            do {
                System.err.println("Invalid Last Name! Enter valid Last Name (only alphabets))!");
                System.out.println("Enter your last name");
                lname = sc.nextLine().trim();
                checkLName = doctorRegistrationUtilImpl.validateLastName(lname);
            } while (!checkLName);
        }

        if(checkLName) {
            doc.setLastName(lname);
        }


        System.out.println("Enter your password! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)");
        String password = sc.nextLine().trim();
        boolean checkPassword = doctorRegistrationUtilImpl.validatePassword(password);

        if(!checkPassword) {
            do {
                System.err.println("Invalid Password! Enter valid password! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)");
                System.out.println("Enter your password");
                password = sc.nextLine().trim();
                checkPassword = doctorRegistrationUtilImpl.validatePassword(password);
            } while (!checkPassword);
        }

        if(checkPassword) {
            doc.setPassword(password);
        }


        System.out.println("Enter your joining date (YYYY-MM-DD)");
        String jDate = sc.nextLine().trim();
        boolean checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);

        if(!checkJDate) {
            do {
                System.err.println("Invalid Date! Enter date in valid format! (should be YYYY-MM-DD)");
                System.out.println("Enter your joining date (YYYY-MM-DD)");
                jDate = sc.nextLine().trim();
                checkJDate = doctorRegistrationUtilImpl.validateDate(jDate);
            } while (!checkJDate);
        }

        if(checkJDate) {
            doc.setJoiningDate(jDate);
        }


        System.out.println("Enter your graduation degree");
        String degree = sc.nextLine().trim();
        boolean checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);

        if(!checkDegree) {
            do {
                System.err.println("Invalid Degree! Enter valid Graduation Degree (only alphabets and spaces))!");
                System.out.println("Enter your graduation degree");
                degree = sc.nextLine().trim();
                checkDegree = doctorRegistrationUtilImpl.validateDegree(degree);
            } while (!checkDegree);
        }

        if(checkDegree) {
            doc.setDegree(degree);
        }


        System.out.println("Enter your specialization (if any, NA if not applicable)");
        String specialization = sc.nextLine().trim();
        boolean checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);

        if(!checkSpecialization) {
            do {
                System.err.println("Invalid specialization! Enter valid specialization (only alphabets and spaces))!");
                System.out.println("Enter your specialization (if any, NA if not applicable)");
                ;
                specialization = sc.nextLine().trim();
                checkSpecialization = doctorRegistrationUtilImpl.validateSpecialization(specialization);
            } while (!checkSpecialization);
        }

        if(checkSpecialization) {
            doc.setSpecialization(specialization);
        }


        System.out.println("Enter your birth date (YYYY-MM-DD)");
        String bdate = sc.nextLine().trim();
        boolean checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);

        if(!checkBDate) {
            do {
                System.err.println("Invalid Date! Enter date in valid format! (should be YYYY-MM-DD");
                System.out.println("Enter your birthdate (YYYY-MM-DD)");
                bdate = sc.nextLine().trim();
                checkBDate = doctorRegistrationUtilImpl.validateDate(bdate);
            } while (!checkBDate);
        }

        if(checkBDate) {
            doc.setBirthDate(bdate);
        }


        System.out.println("Enter your contact number (902######)");
        Long contactNumber = sc.nextLong();
        boolean checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);

        if(!checkContact) {
            do {
                System.out.println("Enter your contact number (902######)");
                contactNumber = sc.nextLong();
                checkContact = doctorRegistrationUtilImpl.validateContact(contactNumber);
            } while (!checkContact);
        }

        if(checkContact) {
            doc.setContactNumber(contactNumber);
        }


        System.out.println("Enter your city of residence");
        String city = sc.nextLine().trim();
        boolean checkCity = doctorRegistrationUtilImpl.validateCity(city);

        if(!checkCity) {
            do {
                System.err.println("Invalid city! Enter valid city (only alphabets and spaces))!");
                System.out.println("Enter your city of residence");
                city = sc.nextLine().trim();
                checkCity = doctorRegistrationUtilImpl.validateCity(city);
            } while (!checkCity);
        }

        if(checkCity) {
                doc.setCity(city);
        }


        System.out.println();
        int choice = 0;

        while(choice != 2) {
            System.out.println("Select one of the below options:");
            System.out.println("1. Confirm to proceed to register");
            System.out.println("2. Exit");

            System.out.println("Please enter your selection below:");
            choice = sc.nextInt();

            if(choice == 1) {
                try {
                    doctorRegistrationDAOImpl.updateDoctorDetails(doc);
                    System.out.println("Registered successfully!");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (choice == 2) {
                System.out.println("Thank you for using our service!");
                return;
            } else {
                System.out.println("Invalid choice!");
            }

        }

    }

}
