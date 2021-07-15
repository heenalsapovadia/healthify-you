package presentation.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

public class InvoiceOutput {
	
	public void displayInvoice(){
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.invoice);
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		do {
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: parseDateInput(consoleObj, new Scanner(System.in));
						break;
				case 2: return;
				default: consoleObj.printError(CommonErrors.invalidSelection);
			}
		}
		while(sel != 2);
	}
	
	private void parseDateInput(PrintToConsole consoleObj, Scanner sc) {
		System.out.println(ScreenFields.dateInput);
		try {
			Date date = Date.valueOf(sc.next());
			if(date.compareTo(new Date(System.currentTimeMillis())) > 0) {
				System.err.println(CommonErrors.greaterDate);
			}
			else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				System.out.println("Loading all receipts for the date "+((cal.get(Calendar.YEAR))+"-"+(cal.get(Calendar.MONTH)+1))+"-"+(cal.get(Calendar.DAY_OF_MONTH)));
				PharmaInvoiceOutput output = new PharmaInvoiceOutput();
				output.displayInvoice(date);
			}
		}
		catch(IllegalArgumentException e) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			parseDateInput(consoleObj, new Scanner(System.in));
		}
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.pharmSuppliesByDate);
		selectionOptions.add(ScreenFields.exit);
		return selectionOptions;
	}
}
