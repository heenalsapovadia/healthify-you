package presentation.patient;

import persistence.patient.daoImpl.DoctorAppointmentBookingByNameDAOImpl;
import presentation.common.PrintToConsole;

import java.sql.SQLException;
import java.util.*;

public class DoctorAppointmentByNameOutput {

  public void displayOutput() throws SQLException {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader("Visit a specific doctor");

    Scanner sc = new Scanner(System.in);
    consoleObj.printScreenFields("Enter doctor's firstname or lastname (case-insensitive)");
    String name = sc.nextLine();

    List<String> selectionOptions = new ArrayList<>();
    selectionOptions.add("1. Search doctor");
    selectionOptions.add("2. Exit");
    int choice = consoleObj.printSelection(selectionOptions);

    if(choice == 1) {
      consoleObj.printHeader("Search doctor");
      consoleObj.printSubHeading("Doctors and Availability");

      DoctorAppointmentBookingByNameDAOImpl doctorAppointmentBookingByNameDAOImpl = new DoctorAppointmentBookingByNameDAOImpl();
      Map<Integer, String> doctorIdentifierList = doctorAppointmentBookingByNameDAOImpl.fetchDoctorIdentifier(name);
      int doctorID = 0;
      if(doctorIdentifierList == null) {
        System.err.println("Not available!");
        return;
      } else {
          System.out.println("Enter doctor identifier from the list given below");
          for(Integer i : doctorIdentifierList.keySet()) {
            System.out.println(i + ": " + doctorIdentifierList.get(i));
          }
          doctorID = sc.nextInt();
          if(!doctorIdentifierList.containsKey(doctorID)) {
            System.err.println("Entered identifier not in the list!");
            return;
          } else {
              doctorAppointmentBookingByNameDAOImpl.fetchDoctorAvailability(doctorID);
          }
      }
    }
  }
}

