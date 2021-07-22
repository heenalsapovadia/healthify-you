package presentation.patient;

import persistence.patient.model.Patient;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.util.ArrayList;
import java.util.List;

public class BookingDashboard {
    private static class BookingDashboardHelper {
        private static final BookingDashboard instance = new BookingDashboard();
    }

    public static BookingDashboard getInstance() {
        return BookingDashboard.BookingDashboardHelper.instance;
    }

    public void displayOutput() {
        PrintToConsole consoleObj = PrintToConsole.getInstance();
        consoleObj.printHeader(ScreenTitles.bookingDashboard);
        loadScreenOptions(consoleObj);
    }

    private List<String> getSelectionOptions() {
        List<String> selectionOptions = new ArrayList<>();
        selectionOptions.add(ScreenFields.appointmentWithDoctor);
        selectionOptions.add(ScreenFields.bookLabTest);
        selectionOptions.add(ScreenFields.bookImmunization);
        selectionOptions.add(ScreenFields.bookBloodBankService);
        return selectionOptions;
    }

    private int loadScreenOptions(PrintToConsole consoleObj) {
        List<String> selectionOptions = getSelectionOptions();
        int sel = consoleObj.printSelection(selectionOptions);
        if(sel == 1) {
            //Book an appointment with doctor
            DoctorAppointmentBookingOutput doctorAppointmentBookingOutput = new DoctorAppointmentBookingOutput();
            doctorAppointmentBookingOutput.dashboard();
        }
        else if(sel == 2) {
            // Book a Lab Test/Health Check up
        }
        else if(sel == 3) {
            //Book an Immunization
        }
        else if(sel == 4) {
            //Book a blood bank service - blood donation
            Patient patient = Patient.getPatient();
            patient.getPatientEmail();
            BloodBankServiceOutput bloodBankServiceOutput = new BloodBankServiceOutput();
            bloodBankServiceOutput.bloodBankService(patient);

        }
        else if(sel == 5) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj);
        }
        return sel;
    }
}
