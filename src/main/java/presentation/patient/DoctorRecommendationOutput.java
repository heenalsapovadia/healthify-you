package presentation.patient;

import persistence.admin.daoImpl.DoctorRegistrationDAOImpl;
import persistence.admin.model.DoctorRegistration;
import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;
import persistence.patient.util.DoctorRecommendationUtil;
import persistence.patient.utilImpl.DoctorRecommendationUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;

import static presentation.common.CommonErrors.emailError;
import static presentation.common.ScreenFields.*;
import static presentation.common.ScreenTitles.doctorRecommendation;
import static presentation.common.ScreenTitles.doctorRegistration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* <pre>
* Output class for doctor recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorRecommendationOutput {

    public void recommendDoctor() {
        Scanner sc = new Scanner(System.in);
        PrintToConsole consoleObj = PrintToConsole.getInstance();

        consoleObj.printHeader(doctorRecommendation);

        int choice = 0;
        consoleObj.printSingleNewLine();

        while(choice != 2) {
            consoleObj.printSingleNewLine();
            consoleObj.printScreenFields(selectionField);

            consoleObj.printScreenFields(selectionOption1);
            consoleObj.printScreenFields(selectionOption2);

            consoleObj.printScreenFields(SELECTION);
            choice = sc.nextInt();

            if (choice == 1) {
                int symptomChoice = 0;
                String symptom = "";
                Scanner sc1 = new Scanner(System.in);

                consoleObj.printScreenFields(symptomSelectionField);
                consoleObj.printScreenFields(symptomOption1);
                consoleObj.printScreenFields(symptomOption2);
                consoleObj.printScreenFields(symptomOption3);
                consoleObj.printScreenFields(symptomOption4);
                consoleObj.printScreenFields(symptomOption5);

                symptomChoice = sc1.nextInt();

                if (symptomChoice == 1) {
                    symptom = "Cough";
                } else if (symptomChoice == 2) {
                    symptom = "Cold";
                } else if (symptomChoice == 3) {
                    symptom = "Fever";
                } else if (symptomChoice == 4) {
                    symptom = "Nausea";
                } else if (symptomChoice == 5) {
                    symptom = "Digestive issues";
                } else {
                    System.out.println("Invalid choice");
                    return;
                }

                int support = 0;
                consoleObj.printScreenFields(supportInput);
                support = sc1.nextInt();

                int numRec = 0;
                consoleObj.printScreenFields(recommendationInput);
                numRec = sc1.nextInt();

                ArrayList<String> doctorList = new ArrayList<>();
                DoctorRecommendationUtilImpl doctorRecommendationUtilImpl = new DoctorRecommendationUtilImpl();
                doctorList = doctorRecommendationUtilImpl.getDoctorRecommendations(symptom, support, numRec);

                if(doctorList != null){

                    consoleObj.printSingleNewLine();
                    System.out.println("You may visit the following doctor(s) for :" +symptom);

                    for(String doctorName : doctorList) {
                        System.out.println(doctorName);
                    }

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
