/**
 * 
 */
package presentation.patient;

import java.util.ArrayList;
import java.util.List;
import persistence.patient.util.ViewReportsUtil;
import persistence.patient.utilImpl.ViewReportsUtilImpl;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Displays medical reports of patient by a particular test.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class ViewReportsByTestOutput {
	
	public void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printSingleNewLine();
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		ViewReportsUtil reportsUtil = new ViewReportsUtilImpl();
		do {
			consoleObj.printHeader(ScreenTitles.VIEW_REPORTS_BY_TEST);
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: consoleObj.printHeader(ScreenTitles.VIEW_BLOOD_REPORTS);
						System.out.println(reportsUtil.getBloodReportByTest());
						consoleObj.printLineSeparator();
						break;
				case 2: consoleObj.printHeader(ScreenTitles.VIEW_KIDNEY_REPORTS);
						System.out.println(reportsUtil.getKidneyReportByTest());
						consoleObj.printLineSeparator();
						break;
				case 3: consoleObj.printHeader(ScreenTitles.VIEW_LIVER_REPORTS);
						System.out.println(reportsUtil.getLiverReportByTest());
						consoleObj.printLineSeparator();
						break;
				case 4: consoleObj.printHeader(ScreenTitles.VIEW_VISION_REPORTS);
						System.out.println(reportsUtil.getVisionReportByTest());
						consoleObj.printLineSeparator();
						break;
				case 5: consoleObj.printHeader(ScreenTitles.VIEW_COVID_REPORTS);
						System.out.println(reportsUtil.getCovidReportByTest());
						consoleObj.printLineSeparator();
						break;
				case 6: return;
				default: consoleObj.printError(CommonErrors.INVALID_SELECTION);
			}
		}
		while(sel != 6);
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.BLOOD_TEST);
		selectionOptions.add(ScreenFields.KIDNEY_TEST);
		selectionOptions.add(ScreenFields.LIVER_TEST);
		selectionOptions.add(ScreenFields.VISION_TEST);
		selectionOptions.add(ScreenFields.COVID_TEST);
		selectionOptions.add(ScreenFields.EXIT);
		return selectionOptions;
	}
}
