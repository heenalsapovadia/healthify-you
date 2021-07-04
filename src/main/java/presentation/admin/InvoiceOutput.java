package presentation.admin;

import java.sql.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.CommonConstants;
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
		System.out.println("1. Search pharmaceutical supplies by date");
		System.out.println("2. Exit");
		System.out.println("Please enter your selection below:");
		Scanner sc = new Scanner(System.in);
		int sel = sc.nextInt();
		switch(sel) {
			case 1:
				try {
					System.out.println("Please enter a date (yyyy/mm/dd):");
					Date date = Date.valueOf(sc.next());
					if(date.compareTo(new Date(System.currentTimeMillis())) > 0) {
						System.out.println("Date greater than today!");
					}
					else {
						System.out.println("Loading all receipts for month "+(date.getYear()+1900)+"/"+(date.getMonth()+1)+"/"+date.getDate());
						Thread.sleep(500);
						PharmaInvoiceOutput output = new PharmaInvoiceOutput();
						output.displayInvoice(date);
					}
				}
				catch (IllegalArgumentException | InterruptedException e) {
					LOGGER.log(Level.SEVERE, "Invalid date format!");
				}
				break;
			case 2:
				System.exit(0);
		}
		sc.close();
	}
}
