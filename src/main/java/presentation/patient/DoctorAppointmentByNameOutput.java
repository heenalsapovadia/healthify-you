package presentation.patient;

import persistence.patient.daoImpl.DoctorAppointmentBookingByNameDAOImpl;
import presentation.common.PrintToConsole;
import presentation.startup.DatabaseConnection;

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
    selectionOptions.add("Search doctor");
    selectionOptions.add("Exit");
    int choice = consoleObj.printSelection(selectionOptions);

    if(choice == 1) {
      consoleObj.printHeader("Search doctor");

      DoctorAppointmentBookingByNameDAOImpl doctorAppointmentBookingByNameDAOImpl = new DoctorAppointmentBookingByNameDAOImpl();
      Map<Integer, String> doctorIdentifierList = doctorAppointmentBookingByNameDAOImpl.fetchDoctorIdentifier(name);
      int doctorID = 0;
      if(doctorIdentifierList == null) {
        System.err.println("Not available!");
        return;
      } else {
          consoleObj.printSubHeading("Doctors and Availability");
          System.out.println("Enter doctor identifier from the list given below");
          for(Integer i : doctorIdentifierList.keySet()) {
            System.out.println(i + ": " + doctorIdentifierList.get(i));
          }
          doctorID = sc.nextInt();
          if(!doctorIdentifierList.containsKey(doctorID)) {
            System.err.println("Entered doctor identifier not in the list!");
            return;
          } else {
              Map<Integer, List<String>> result = new HashMap<>();
              result = doctorAppointmentBookingByNameDAOImpl.fetchDoctorAvailability(doctorID);
              List<String> datesAvailable = result.get(doctorID);

              System.out.println("Next dates available for the requested doctor are as given below:");
              System.out.println(datesAvailable);

              System.out.println("Please enter your choice from the dates mentioned above");
              String appointmentDate = sc.nextLine().trim();
          }
      }
    }
  }

}

