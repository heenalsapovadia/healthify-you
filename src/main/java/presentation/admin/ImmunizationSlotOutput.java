package presentation.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import persistence.admin.daoImpl.ImmunizationSlotDAOImpl;
import persistence.admin.util.CurrentWeekdays;
import persistence.admin.utilImpl.ImmunizationSlotUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.doctor.DoctorMenuOutput;

public class ImmunizationSlotOutput {

	PrintToConsole print = PrintToConsole.getInstance();

	/**
	 * This method used to print the stucture for immunization slot headers
	 */
	private void printSlotHeaders() {
		print.printHeader(presentation.common.ScreenTitles.IMMUNIZATION_DASHBOARD);
		print.printSubHeading("Slots Assigned");
		Date date = new Date();
		String str = String.format("Current " + ScreenFields.DATETIME + ": %tc", date);
		print.printScreenFields(str);
		print.printSingleNewLine();
		print.printScreenFields(CommonConstants.MEDIUM_SPACE + CommonConstants.VERTICAL_BAR + CommonConstants.SINGLE_TAB
				+ "Monday" + CommonConstants.MEDIUM_SPACE + "Tuesday" + CommonConstants.MEDIUM_SPACE + "Wedneday"
				+ CommonConstants.SINGLE_TAB + "Thursday" + CommonConstants.SINGLE_TAB + "Friday"
				+ CommonConstants.SINGLE_TAB);

		print.printScreenFieldsSameLine(CommonConstants.MEDIUM_SPACE + CommonConstants.VERTICAL_BAR);
		CurrentWeekdays week = new CurrentWeekdays();
		for (String Slotdate : week.getDates()) {
			print.printScreenFieldsSameLine(CommonConstants.SINGLE_TAB + Slotdate);
		}
		print.printSingleNewLine();
	}

	/**
	 * This method is used to print the slot content for immunization
	 */
	private void printSlotContent(ImmunizationSlotDAOImpl dao, int updateChoice) {
		printSlotHeaders();

		int i = 0;
		for (String time : dao.getSlotTiming()) {
			print.printScreenFieldsSameLine(
					time + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + CommonConstants.SINGLE_TAB);
			for (Map.Entry<String, ArrayList<Integer>> entry : dao.getAssignedDoctors(updateChoice).entrySet()) {
				int docID = entry.getValue().get(i);
				if (docID == 0) {
					print.printScreenFieldsSameLine("over" + CommonConstants.MEDIUM_SPACE);
				} else {
					print.printScreenFieldsSameLine(entry.getValue().get(i) + CommonConstants.MEDIUM_SPACE);
				}
			}
			print.printSingleNewLine();
			i++;
		}
	}

	/**
	 * This method prints the slots assigned
	 */
	public void immunizationSlotAssign() {

		ImmunizationSlotDAOImpl dao = new ImmunizationSlotDAOImpl();
		ImmunizationSlotUtilImpl util = new ImmunizationSlotUtilImpl();
		int updatedChoice = 0;
		LinkedHashMap<String, ArrayList<Integer>> assign = new LinkedHashMap<>(dao.getAssignedDoctors(updatedChoice));

		printSlotContent(dao, 0);
		print.printHorizontalLine();
		print.printScreenFields("Staff ID in queue :" + dao.getDoctorsAvailable());
		print.printScreenFields("Assign slots a new Staff ID? Yes / No");
		print.printScreenFields(ScreenFields.SELECTION);
		List<String> selection = Arrays.asList("Yes", "No");
		int choice = print.printSelection(selection);
		if (choice == 1) {
			if (util.validateWeekend()) {
				util.assignDoctors(assign, dao.getDoctorsAvailable());
				print.printScreenFields(ScreenFields.SLOTS_ASSIGNED);
				printSlotContent(dao, 1);
			} else {
				print.printScreenFields(CommonErrors.INVALID_WEEKDAY);
				print.printSingleNewLine();
			}
		}
		if (choice == 2) {
			ImmunizationDashboard immunizationdashboard = ImmunizationDashboard.getInstance();
			immunizationdashboard.displayOutput();

		}

	}

}
