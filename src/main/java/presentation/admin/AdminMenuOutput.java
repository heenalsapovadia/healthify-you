package presentation.admin;

import java.util.InputMismatchException;
import java.util.Scanner;
import presentation.CommonConstants;
import presentation.CommonErrors;
import presentation.ScreenFields;
import presentation.ScreenTitles;

public class AdminMenuOutput {
	
private AdminMenuOutput() {}
	
	private static class AdminMenuOutputHelper {
		private static final AdminMenuOutput instance = new AdminMenuOutput();
	}
	
	public static AdminMenuOutput getInstance() {
		return AdminMenuOutputHelper.instance;
	}
	
	public void displayOutput() {
		loadHeader();
		int sel = -1;
		Scanner sc = null;
		try {
			System.out.println("1. "+ScreenFields.getInvoices);
			System.out.println("2. "+ScreenFields.getRecommendations);
			System.out.println("3. "+ScreenFields.registerDoctor);
			System.out.println("4. "+ScreenFields.exit);
			System.out.println(ScreenFields.selection);
			sc = new Scanner(System.in);
			sel = sc.nextInt();
			if(sel == 1) {
				InvoiceOutput invoiceOutput = new InvoiceOutput();
				invoiceOutput.displayInvoice();
				displayOutput();
			}
			else if(sel == 2) {
				//add code for blood bank output here
			}
			else if(sel == 3) {
				DoctorRegistrationOutput doctorRegistrationOutput = new DoctorRegistrationOutput();
				doctorRegistrationOutput.registerDoctor();
				displayOutput();
			}
			else if(sel == 4) {
				sc.close();
				System.exit(0);
			}
			else {
				displayOutput();
			}
		}
		catch(InputMismatchException e) {
			System.err.println(CommonErrors.invalidSelection);
			displayOutput();
		}
	}
	
	private void loadHeader() {
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(CommonConstants.titleSpace+ScreenTitles.adminDashboard+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
	}
}
