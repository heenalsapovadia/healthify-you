package presentation.admin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import presentation.startup.ApplicationOutput;

/**
 * @author Deeksha Sareen Dashboard for immunization management
 *
 */
public class ImmunizationDashboard {
  private static final Logger LOGGER = Logger.getLogger

  (ImmunizationDashboard.class.getName());
  PrintToConsole consoleObj = PrintToConsole.getInstance();

  private ImmunizationDashboard() {
  }

  private static ImmunizationDashboard immunizationdashboard;

  public static ImmunizationDashboard getInstance() {
    if (immunizationdashboard == null)
      immunizationdashboard = new ImmunizationDashboard();
    return immunizationdashboard;
  }

  public void displayOutput() {
    consoleObj.printHeader(ScreenTitles.immunizationDashboard);
    loadScreenOptions();
  }

  private int loadScreenOptions() {

    List<String> selectionOptions = Arrays.asList(ScreenFields.slotmanagement, ScreenFields.vaccinationstats);
    int sel = consoleObj.printSelection(selectionOptions);

    if (sel == 1) {
      ImmunizationSlotOutput slotoutput = new ImmunizationSlotOutput();
      slotoutput.immunizationSlotAssign();
    } else if (sel == 2) {
      ImmunizationStatsOutput immunizationStatsOutput = new ImmunizationStatsOutput();
      immunizationStatsOutput.dashboard();

    } else {
      consoleObj.printError(CommonErrors.invalidSelection);
      sel = loadScreenOptions();
    }
    return sel;
  }

}
