package presentation.admin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Deeksha Sareen: Dashboard for immunization management
 *
 */
public class ImmunizationDashboard {

	private static final Logger LOGGER = Logger.getLogger

	(ImmunizationDashboard.class.getName());
	PrintToConsole consoleObj = PrintToConsole.getInstance();

	private ImmunizationDashboard() {
	}

	private static ImmunizationDashboard immunizationdashboard;

	/**
	 * This sets the instance for the immunization dashboard
	 */
	public static ImmunizationDashboard getInstance() {
		if (immunizationdashboard == null)
			immunizationdashboard = new ImmunizationDashboard();
		return immunizationdashboard;
	}

	/**
	 * This method displays the immunization dashboard
	 */
	public void displayOutput() {
		consoleObj.printHeader(ScreenTitles.IMMUNIZATION_DASHBOARD);
		loadScreenOptions();
	}

	/**
	 * This loads the screen options for the immunization dashboard
	 */
	private int loadScreenOptions() {

		List<String> selectionOptions = Arrays.asList(ScreenFields.SLOT_MANAGEMENT, ScreenFields.VACCINATION_STATS);
		int sel = consoleObj.printSelection(selectionOptions);

		if (sel == 1) {
			ImmunizationSlotOutput slotoutput = new ImmunizationSlotOutput();
			slotoutput.immunizationSlotAssign();
		} else if (sel == 2) {
			ImmunizationStatsOutput immunizationStatsOutput = new ImmunizationStatsOutput();
			immunizationStatsOutput.dashboard();
		} else {
			consoleObj.printError(CommonErrors.INVALID_SELECTION);
			sel = loadScreenOptions();
		}
		return sel;
	}

}
