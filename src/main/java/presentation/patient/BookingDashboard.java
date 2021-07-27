package presentation.patient;

import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * <pre>
 * This  class is responsible for representing booking module and further process related to it.
 * </pre>
 */

public class BookingDashboard {
    private static class BookingDashboardHelper {
        private static final BookingDashboard instance = new BookingDashboard();
    }

    public static BookingDashboard getInstance() {
        return BookingDashboard.BookingDashboardHelper.instance;
    }

    public void displayOutput() throws SQLException {
        PrintToConsole consoleObj = PrintToConsole.getInstance();

        loadScreenOptions(consoleObj);
    }

    private List<String> getSelectionOptions() {
        List<String> selectionOptions = new ArrayList<>();
        selectionOptions.add(ScreenFields.APPOINTMENT_WITH_DOCTOR);
        selectionOptions.add(ScreenFields.BOOK_LAB_TEST);
        selectionOptions.add(ScreenFields.BOOK_IMMUNIZATION);
        selectionOptions.add(ScreenFields.bookBloodBankService);
        selectionOptions.add(ScreenFields.GO_BACK);
        return selectionOptions;
    }

    private int loadScreenOptions(PrintToConsole consoleObj) throws SQLException {
        consoleObj.printHeader(ScreenTitles.BOOKING_DASHBOARD);
        List<String> selectionOptions = getSelectionOptions();
        int sel = consoleObj.printSelection(selectionOptions);
        if(sel == 1) {
            //Book an appointment with doctor
            DoctorAppointmentBookingOutput doctorAppointmentBookingOutput = new DoctorAppointmentBookingOutput();
            doctorAppointmentBookingOutput.dashboard();
            sel = loadScreenOptions(consoleObj);
        }
        else if(sel == 2) {
            LabTestBookingOutput labTestBookingOutput = new LabTestBookingOutput();
            labTestBookingOutput.dashboard();
            sel = loadScreenOptions(consoleObj);
        }
        else if(sel == 3) {
          ImmunizationBookingOutput obj = new ImmunizationBookingOutput();
          obj.immunizationBooking();
          sel = loadScreenOptions(consoleObj);
        }
        else if(sel == 4) {
            //Book a blood bank service - blood donation
            BloodBankServiceOutput bloodBankServiceOutput = new BloodBankServiceOutput();
            bloodBankServiceOutput.bloodBankService();
            sel = loadScreenOptions(consoleObj);

        }
        else if(sel == 5) {
              return sel;
        }
        else {
            consoleObj.printError(CommonErrors.INVALID_SELECTION);
            sel = loadScreenOptions(consoleObj);
        }
        return sel;
    }
}
