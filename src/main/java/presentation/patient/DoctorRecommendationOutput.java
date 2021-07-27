package presentation.patient;

import persistence.patient.utilImpl.DoctorRecommendationUtilImpl;
import presentation.common.PrintToConsole;

import static presentation.common.CommonErrors.INVALID_SELECTION;
import static presentation.common.ScreenFields.*;
import static presentation.common.ScreenTitles.DOCTOR_RECOMMENDATION;

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

        consoleObj.printHeader(DOCTOR_RECOMMENDATION);

        int choice = 0;
        consoleObj.printSingleNewLine();

        while(choice != 2) {
            consoleObj.printSingleNewLine();
            consoleObj.printScreenFields(SELECTION_FIELD);

            consoleObj.printScreenFields(SELECTION_OPTION_1);
            consoleObj.printScreenFields(SELECTION_OPTION_2);

            consoleObj.printScreenFields(SELECTION);
            choice = sc.nextInt();

            if (choice == 1) {
                int symptomChoice = 0;
                String symptom = "";
                Scanner sc1 = new Scanner(System.in);

                consoleObj.printScreenFields(SYMPTOM_SELECTION_FIELD);
                consoleObj.printScreenFields(SYMPTOM_OPTION_1);
                consoleObj.printScreenFields(SYMPTOM_OPTION_2);
                consoleObj.printScreenFields(SYMPTOM_OPTION_3);
                consoleObj.printScreenFields(SYMPTOM_OPTION_4);
                consoleObj.printScreenFields(SYMPTOM_OPTION_5);

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
                consoleObj.printScreenFields(SUPPORT_INPUT);
                support = sc1.nextInt();

                int numRec = 0;
                consoleObj.printScreenFields(RECOMMENDATION_INPUT);
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
                DoctorAppointmentBookingDashboard doctorAppointmentBookingDashboard = new DoctorAppointmentBookingDashboard();
                try {
                    doctorAppointmentBookingDashboard.display();
                } catch (SQLException se) {
                    return;
                }
            } else {
                System.out.println(INVALID_SELECTION);
            }
        }
    }

}
