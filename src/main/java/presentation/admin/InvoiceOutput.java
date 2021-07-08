package presentation.admin;

import java.sql.Date;
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
		System.out.println("1. "+ScreenFields.pharmSuppliesByDate);
		System.out.println("2. "+ScreenFields.exit);
		System.out.println(ScreenFields.selection);
		Scanner sc = new Scanner(System.in);
		int sel = sc.nextInt();
		switch(sel) {
			case 1:
				try {
					System.out.println(ScreenFields.dateInput);
					Date date = Date.valueOf(sc.next());
					if(date.compareTo(new Date(System.currentTimeMillis())) > 0) {
						System.err.println(CommonErrors.greaterDate);
					}
					else {
						System.out.println("Loading all receipts for month "+(date.getYear()+1900)+"/"+(date.getMonth()+1)+"/"+date.getDate());
						Thread.sleep(500);
						PharmaInvoiceOutput output = new PharmaInvoiceOutput();
						output.displayInvoice(date);
					}
				}
				catch (IllegalArgumentException | InterruptedException e) {
					System.err.println(CommonErrors.invalidDateFormat);
				}
				break;
			case 2: {
				sc.close();
				return;
			}
		}
		sc.close();
	}
}
