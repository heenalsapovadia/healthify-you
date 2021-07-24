package presentation.doctor;

import presentation.common.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DoctorMenuOutput {

    private static final Logger LOGGER = Logger.getLogger(DoctorMenuOutput.class.getName());
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    private DoctorMenuOutput(){}

    private static DoctorMenuOutput doctorMenuOutput;

    public static DoctorMenuOutput getInstance() {
        if(doctorMenuOutput == null)
            doctorMenuOutput = new DoctorMenuOutput();
        return doctorMenuOutput;
    }

    public void displayOutput() {
        consoleObj.printHeader(ScreenTitles.doctorDashboard);
        loadScreenOptions();
    }

    private List<String> getSelectionOptions() {
        List<String> selectionOptions = new ArrayList<>();
        selectionOptions.add(ScreenFields.prescribeMedicine);
        selectionOptions.add(ScreenFields.viewAppointment);
        selectionOptions.add(ScreenFields.LOGOUT);
        return selectionOptions;
    }

    private int loadScreenOptions() {
        List<String> selectionOptions = getSelectionOptions();
        int option = consoleObj.printSelection(selectionOptions);

        if(option == 1) {
            PrescribeMedicineOutput prescribeMedicineOutput = new PrescribeMedicineOutput();
            prescribeMedicineOutput.prescribeMedication();
        }
        else if(option == 2) {
            //add code for View Appointment for Doctor here
        }
        else if(option == 3) {
            System.out.println(ScreenFields.LOGOUT_MESSAGE);
            System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            option = loadScreenOptions();
        }
        return option;
    }

}
