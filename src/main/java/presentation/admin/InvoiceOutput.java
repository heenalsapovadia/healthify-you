package presentation.admin;

import java.sql.Date;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.CommonConstants;
import presentation.CommonErrors;
import presentation.ScreenFields;
import presentation.ScreenTitles;

public class InvoiceOutput {
	
	private static final Logger LOGGER = Logger.getLogger(InvoiceOutput.class.getName());
	
	public void displayInvoice(){
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(CommonConstants.titleSpace+ScreenTitles.invoice+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		int sel;
		do {
			sel = loadScreenOptions(new Scanner(System.in));
			switch(sel) {
				case 1: parseDateInput(new Scanner(System.in));
						break;
				case 2: return;
			}
		}
		while(sel != 2);
	}
	
	private int loadScreenOptions(Scanner sc) {
		int sel = -1;
		System.out.println("1. "+ScreenFields.pharmSuppliesByDate);
		System.out.println("2. "+ScreenFields.exit);
		System.out.println(ScreenFields.selection);
		if(sc.hasNextInt())
			sel = sc.nextInt();
		else {
			System.err.println(CommonErrors.invalidSelection);
			sc = new Scanner(System.in);
			sel = loadScreenOptions(new Scanner(System.in));
		}
		return sel;
	}
	
	private void parseDateInput(Scanner sc) {
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
			System.err.println(CommonErrors.invalidDateFormat);
		}
	}
}
