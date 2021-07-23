package presentation.patient;

import persistence.patient.daoImpl.DoctorAppointmentBookingByNameDAOImpl;
import persistence.patient.utilImpl.DoctorAppointmentBookingByNameUtilImpl;
import presentation.common.PrintToConsole;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
* <pre>
* Output class for doctor appointment booking by name
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

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
              DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();

              System.out.println("Next dates available for the requested doctor are as given below:");
              System.out.println(datesAvailable);

              System.out.println("Please enter your choice from the dates mentioned above");
              String appointmentDate = sc.nextLine().trim();

              while(!doctorAppointmentBookingByNameUtil.validateDate(appointmentDate, datesAvailable)) {
                System.out.println(datesAvailable);
                System.out.println("Please enter your choice from the dates mentioned above");
                appointmentDate = sc.nextLine().trim();
              }

              System.out.println("Enter registered patient email address for whom the appointment should be booked!");
              String patientEmail = sc.next();
              int patientID = doctorAppointmentBookingByNameUtil.validateEmail(patientEmail);

              while(patientID == -1) {
                System.out.println("Email address entered is not registered with us! Please enter registered patient email address for whom the appointment should be booked!");
                patientEmail = sc.next();
                patientID = doctorAppointmentBookingByNameUtil.validateEmail(patientEmail);
              }

              LocalDate date = LocalDate.now();
              String bookedDate = date.toString();

              if(doctorAppointmentBookingByNameDAOImpl.addDoctorAppointment(patientID, doctorID, bookedDate, appointmentDate)) {
                  System.out.println("Appointment booked successfully!");
              } else {
                  return;
              }
          }
      }
    }
  }

}

