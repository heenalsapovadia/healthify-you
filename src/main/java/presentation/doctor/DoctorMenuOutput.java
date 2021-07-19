package presentation.doctor;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Prescription;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import presentation.common.*;
import presentation.admin.DoctorRegistrationOutput;
import presentation.admin.InvoiceOutput;
import presentation.startup.ApplicationOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
        selectionOptions.add(ScreenFields.logout);
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
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            option = loadScreenOptions();
        }
        return option;
    }

}
