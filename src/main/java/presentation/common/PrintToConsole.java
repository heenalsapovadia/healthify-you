/**
 * 
 */
package presentation.common;

import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Prints to the console the following: 
 * 1. Header used across all screens 
 * 2. Selection options for all screens
 * 3. Errors
 * 4. Line separators
 * 
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PrintToConsole {
	
	private static PrintToConsole printInstance;
	
	private PrintToConsole() {}
	
	public static PrintToConsole getInstance() {
		if(printInstance == null) {
			printInstance = new PrintToConsole();
		}
		return printInstance;
	}
	
	public void printHeader(String title) {
		for(int i=0; i<100; i++) {
			System.out.print(CommonConstants.headingChar);
		}
		System.out.println();
		System.out.println(CommonConstants.titleSpace+title+CommonConstants.titleSpace);
		for(int i=0; i<100; i++) {
			System.out.print(CommonConstants.headingChar);
		}
		System.out.println();
	}
	
	public int printSelection(List<String> selectionOptions) {
		int selected = -1;
		if(selectionOptions != null && !selectionOptions.isEmpty()) {
			for(int option=0; option<selectionOptions.size(); option++) {
				System.out.println((option+1)+CommonConstants.period+CommonConstants.singleSpace+selectionOptions.get(option));
			}
			System.out.println(ScreenFields.selection);
			Scanner sc = new Scanner(System.in);
			if(sc.hasNextInt()) {
				selected = sc.nextInt();
			}
			else {
				printError(CommonErrors.invalidSelection);
				selected = printSelection(selectionOptions);
			}
		}
		else {
			printError(CommonErrors.invalidDisplay);
		}
		return selected;
	}
	
	public void printError(String errorMessage) {
		System.err.println(errorMessage);
	}
	
	public void printLineSeparator() {
		for(int i=0; i<100; i++) {
			System.out.print(CommonConstants.headingChar);
		}
		System.out.println();
	}
}
