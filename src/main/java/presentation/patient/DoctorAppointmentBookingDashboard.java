package presentation.patient;

import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* <pre>
* Output class for doctor appointment booking dashboard
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorAppointmentBookingDashboard {

  public void display() throws SQLException {

    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader("Book an appointment with doctor");

    consoleObj.printScreenFields(ScreenFields.SELECTION_FIELD);
    List<String> selectionOptions = new ArrayList<>();
    selectionOptions.add("Get Doctor recommendations based on symptoms");
    selectionOptions.add("Visit a specific doctor");
    selectionOptions.add("Book an appointment with doctor based on specialization");
    selectionOptions.add("Exit");
    consoleObj.printScreenFields(ScreenFields.SELECTION);
    int choice = consoleObj.printSelection(selectionOptions);

    if (choice == 1) {
        DoctorRecommendationOutput doctorRecommendationOutput = new DoctorRecommendationOutput();
        doctorRecommendationOutput.recommendDoctor();
        return;
    } else if (choice == 2) {
        DoctorAppointmentByNameOutput doctorAppointmentByNameOutput = new DoctorAppointmentByNameOutput();
        doctorAppointmentByNameOutput.displayOutput();
        return;
    } else if (choice == 3) {
        DoctorAppointmentBySpecializationOutput doctorAppointmentBySpecializationOutput = new DoctorAppointmentBySpecializationOutput();
        doctorAppointmentBySpecializationOutput.displayOutput();
        return;
    } else if (choice == 4) {
        return;
    }

  }

}
