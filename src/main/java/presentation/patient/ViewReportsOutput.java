/**
 * 
 */
package presentation.patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import persistence.patient.util.ViewReportsUtil;
import persistence.patient.utilImpl.ViewReportsUtilImpl;
import presentation.admin.PharmaInvoiceOutput;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Gurleen Saluja
 *
 */
public class ViewReportsOutput {
	
	private Date startDate = null;
	private Date endDate = null;
	
	public void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.VIEW_REPORTS);
		consoleObj.printSingleNewLine();
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		ViewReportsUtil reportsUtil = new ViewReportsUtilImpl();
		do {
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: ViewReportsByTestOutput viewReportsByTest = new ViewReportsByTestOutput();
						viewReportsByTest.displayOutput();
						break;
				case 2: displayReportForParticularDate(reportsUtil, consoleObj);						
						break;
				case 3: displayReportForDateRange(reportsUtil, consoleObj);
						break;
				case 4: return;
				default: consoleObj.printError(CommonErrors.invalidSelection);
			}
		}
		while(sel != 4);
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.VIEW_REPORTS_BY_TEST);
		selectionOptions.add(ScreenFields.VIEW_REPORTS_BY_PARTICULAR_DATE);
		selectionOptions.add(ScreenFields.VIEW_REPORTS_BY_DATE_RANGE);
		selectionOptions.add(ScreenFields.exit);
		return selectionOptions;
	}
	
	private Date parseDateInput(PrintToConsole consoleObj, Scanner sc) {
		System.out.println(ScreenFields.dateInput);
		Date date = null;
		try {
			date = Date.valueOf(sc.next());
			if(date.compareTo(new Date(System.currentTimeMillis())) > 0) {
				System.err.println(CommonErrors.greaterDate);
			}
			else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				System.out.println("Loading all reports for the date "+((cal.get(Calendar.YEAR))+"-"+(cal.get(Calendar.MONTH)+1))+"-"+(cal.get(Calendar.DAY_OF_MONTH)));
			}
		}
		catch(IllegalArgumentException e) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			parseDateInput(consoleObj, new Scanner(System.in));
		}
		if(date == null) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			date = parseDateInput(consoleObj, new Scanner(System.in));
		}
		return date;
	}
	
	private void displayReportForParticularDate(ViewReportsUtil reportsUtil, PrintToConsole consoleObj) {
		Map<String, List<String>> reportsMap = reportsUtil.fetchReportByDate(parseDateInput(consoleObj, new Scanner(System.in)).toString());
		if(!reportsMap.isEmpty()) {
			for(Map.Entry<String, List<String>> entry: reportsMap.entrySet()) {
				consoleObj.printHeader(entry.getKey());
				for(String report: entry.getValue()) {
					System.out.println(report);
				}
			}
		}
		else {
			System.err.println(CommonErrors.NO_REPORTS);
		}
	}
	
	private void displayReportForDateRange(ViewReportsUtil reportsUtil, PrintToConsole consoleObj) {
		parseDateRange(consoleObj, new Scanner(System.in));
		Map<String, List<String>> reportsMap = reportsUtil.fetchReportByDateRange(startDate.toString(), endDate.toString());
		if(!reportsMap.isEmpty()) {
			for(Map.Entry<String, List<String>> entry: reportsMap.entrySet()) {
				consoleObj.printHeader(entry.getKey());
				for(String report: entry.getValue()) {
					System.out.println(report);
					consoleObj.printLineSeparator();
				}
			}
		}
		else {
			System.err.println(CommonErrors.NO_REPORTS);
		}
	}
	
	private void parseDateRange(PrintToConsole consoleObj, Scanner sc) {
		try {
			System.out.println(ScreenFields.START_DATE);
			startDate = Date.valueOf(sc.next());
			if(startDate.compareTo(new Date(System.currentTimeMillis())) > 0) {
				System.err.println(CommonErrors.greaterDate);
				displayOutput();
			}
			System.out.println(ScreenFields.END_DATE);
			endDate = Date.valueOf(sc.next());
			if(endDate.compareTo(new Date(System.currentTimeMillis())) > 0) {
				System.err.println(CommonErrors.greaterDate);
				displayOutput();
			}
			else {
				Calendar startDateCal = Calendar.getInstance();
				startDateCal.setTime(startDate);
				Calendar endDateCal = Calendar.getInstance();
				endDateCal.setTime(endDate);
				System.out.println(
						"Loading all reports from "
								+ ((startDateCal.get(Calendar.YEAR))+"-"+(startDateCal.get(Calendar.MONTH)+1))+"-"+(startDateCal.get(Calendar.DAY_OF_MONTH))
								+ " to "+ ((endDateCal.get(Calendar.YEAR))+"-"+(endDateCal.get(Calendar.MONTH)+1))+"-"+(endDateCal.get(Calendar.DAY_OF_MONTH)));
			}
		}
		catch(IllegalArgumentException e) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			parseDateRange(consoleObj, new Scanner(System.in));
		}
		if(startDate == null || endDate == null) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			parseDateRange(consoleObj, new Scanner(System.in));
		}
	}
}