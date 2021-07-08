package presentation.patient;
import java.util.Scanner;

import org.mariadb.jdbc.internal.com.read.resultset.SelectResultSet;
import persistence.doctor.model.Appointment;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import persistence.patient.dao.RegistrationDAO;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.RegistrationUtil;
import persistence.patient.utilImpl.RegistrationUtilImpl;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.daoImpl.UserLoginDAOImpl;
import persistence.startup.util.UserLoginUtil;
import persistence.startup.utilImpl.UserLoginUtilImpl;
import presentation.CommonConstants;
import presentation.ScreenTitles;
import presentation.startup.UserLogin;

public class BloodBankServiceOutput  {

    public static void bloodBankService() {
        Scanner sc = new Scanner(System.in);
        int userSelection;
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.prescription + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();

        // If user enter 1 then eligibility is checked
        // If user has no records than automatically registered...
        // check whether data is minimum 6 month
        // check if report is normal - will input json format inorder to extract normal reports..

        while (true) {
            System.out.println("1.Registration for Blood Donation");
            System.out.println("2. My Donations");
            System.out.println("3. Exit");
            System.out.println("Please enter your selection below:");
            userSelection = sc.nextInt();
            switch (userSelection) {

                case 1:
//                    // if (user is new)
//                {
//                    // if user is new register them
//                    System.out.println("Checking Eligibility.....");
//                    System.out.println("\n\n");
//                    System.out.println("Eligible");
//                    System.out.println("\n\n");
//                    // System.out.println("Your Token is:" + random generated token);
//                    System.out.println("\n\n");
//                    System.out.println("We operate on Tuesday and Sundays. Visit anytime!");
//                    return;
//                }

//                // else(user is eligible)
//                {
//                    // check user eligibility
//                    //if ((user previous donation date minimum 6 months) |(report = normal))
////                    {
////                        //System.out.println("Checking Eligibility.....");
////
////                    }
//                  }
                    //else{
//                    System.out.println("Checking Eligibility.....");
//                    System.out.println("\n\n");
//                    System.out.println("Eligible");
//                    System.out.println("\n\n");
//                    //System.out.println("Your Token is:" + random generated token);
//                    System.out.println("\n\n");
//                    System.out.println("We operate on Tuesday and Sundays. Visit anytime!");
//                    return;
//                    }

                // case 2:
                // calling database class
                // if(no records found)
//                {
//                    System.out.println("No donation records found:");
//
//                }
            }
            }
        }
    }
