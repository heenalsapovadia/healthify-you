package presentation.admin;

import java.util.ArrayList;
import java.util.List;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Loads admin dashboard in the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class AdminMenuOutput {

  private AdminMenuOutput() {
  }

  private static class AdminMenuOutputHelper {
    private static final AdminMenuOutput instance = new AdminMenuOutput();
  }

  public static AdminMenuOutput getInstance() {
    return AdminMenuOutputHelper.instance;
  }

  public void displayOutput() {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader(ScreenTitles.ADMIN_DASHBOARD);
    loadScreenOptions(consoleObj);
  }

  private List<String> getSelectionOptions() {
    List<String> selectionOptions = new ArrayList<>();
    selectionOptions.add(ScreenFields.GET_INVOICES);
    selectionOptions.add(ScreenFields.GET_RECOMMENDATIONS);
    selectionOptions.add(ScreenFields.REGISTER_DOCTOR);
    selectionOptions.add(ScreenFields.immunizationmanagement);
    selectionOptions.add(ScreenFields.LOGOUT);
    return selectionOptions;
  }

  private int loadScreenOptions(PrintToConsole consoleObj) {
    List<String> selectionOptions = getSelectionOptions();
    int sel = consoleObj.printSelection(selectionOptions);
    if (sel == 1) {
      InvoiceOutput invoiceOutput = new InvoiceOutput();
      invoiceOutput.displayInvoice();
      sel = loadScreenOptions(consoleObj);
    } else if (sel == 2) {
      BloodBankRecommendationOutput bloodBankRecommendationOutput = new BloodBankRecommendationOutput();
      bloodBankRecommendationOutput.getBloodBankRecommendations();
      sel = loadScreenOptions(consoleObj);

    }

    else if (sel == 3) {
      DoctorRegistrationOutput doctorRegistrationOutput = new DoctorRegistrationOutput();
      doctorRegistrationOutput.registerDoctor();
      sel = loadScreenOptions(consoleObj);
      
    } else if (sel == 4) {
      ImmunizationDashboard immunizationdashboard = ImmunizationDashboard.getInstance();
      immunizationdashboard.displayOutput();
      sel = loadScreenOptions(consoleObj);
      
    } else if (sel == 5) {
      System.out.println(ScreenFields.LOGOUT_MESSAGE);
      System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
      System.exit(0);
    } else {
      consoleObj.printError(CommonErrors.invalidSelection);
      sel = loadScreenOptions(consoleObj);
    }
    return sel;
  }
}
