package presentation.admin;

import java.util.Scanner;

import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

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
		loadScreenOptions(new Scanner(System.in));
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
	
	private int loadScreenOptions(Scanner sc) {
		int sel = -1;
		System.out.println("1. "+ScreenFields.getInvoices);
		System.out.println("2. "+ScreenFields.getRecommendations);
		System.out.println("3. "+ScreenFields.registerDoctor);
		System.out.println("4. "+ScreenFields.logout);
		System.out.println(ScreenFields.selection);
		if(sc.hasNextInt())
			sel = sc.nextInt();
		else {
			System.err.println(CommonErrors.invalidSelection);
			sel = loadScreenOptions(new Scanner(System.in));
		}
		if(sel == 1) {
			InvoiceOutput invoiceOutput = new InvoiceOutput();
			invoiceOutput.displayInvoice();
			sel = loadScreenOptions(new Scanner(System.in));
		}
		else if(sel == 2) {
			//add code for blood bank output here
		}
		else if(sel == 3) {
			DoctorRegistrationOutput doctorRegistrationOutput = new DoctorRegistrationOutput();
			doctorRegistrationOutput.registerDoctor();
			sel = loadScreenOptions(new Scanner(System.in));
		}
		else if(sel == 4) {
			System.out.println(ScreenFields.logoutMessage);
			System.out.println(ScreenFields.applicationTerminationMessage);
			sc.close();
			System.exit(0);
		}
		else {
			System.out.println(CommonErrors.invalidSelection);
			sel = loadScreenOptions(new Scanner(System.in));
		}
		return sel;
	}
}
