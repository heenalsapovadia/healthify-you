package presentation.patient;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.patient.dao.DoctorAppointmentBookingByNameDAO;
import persistence.patient.daoImpl.DoctorAppointmentBookingByNameDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckBookingUtil;
import persistence.patient.utilImpl.DoctorAppointmentBookingByNameUtilImpl;
import persistence.patient.utilImpl.LabCheckBookingUtilImpl;
import presentation.common.PaymentInterfaceOutput;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.startup.DatabaseConnection;

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
    selectionOptions.add("Logout");
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

              if(result != null) {
                  List<String> datesAvailable = result.get(doctorID);
                  DoctorAppointmentBookingByNameUtilImpl doctorAppointmentBookingByNameUtil = new DoctorAppointmentBookingByNameUtilImpl();
                  Scanner sc1 = new Scanner(System.in);

                  System.out.println("Next dates available for the requested doctor are as given below:");
                  System.out.println(datesAvailable);

                  System.out.println("Please enter your choice from the dates mentioned above");
                  String appointmentDate = sc1.nextLine().trim();

                  while(!doctorAppointmentBookingByNameUtil.validateDate(appointmentDate, datesAvailable)) {
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println(datesAvailable);
                    System.out.println("Please enter your choice from the dates mentioned above");
                    appointmentDate = sc2.nextLine().trim();
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

                  List<Integer> appointmentID = doctorAppointmentBookingByNameDAOImpl.addDoctorAppointment(patientID, doctorID, bookedDate, appointmentDate);
                  if(appointmentID != null && !appointmentID.isEmpty()) {
                      System.out.println("Appointment booked successfully!");

                      List<String> options = Arrays.asList("Continue For Payment", ScreenFields.exit);
                      int option = consoleObj.printSelection(options);

                      String inClause = "";

                      for (int i = 0; i < appointmentID.size(); i++) {
                          if (i < appointmentID.size() - 1) {
                              inClause = inClause + appointmentID.get(i) + " ,";
                          } else {
                              if (i == appointmentID.size() - 1) {
                                  inClause = inClause + appointmentID.get(i);
                              }
                          }
                      }

                      int billingId = 0;
                      switch (option) {
                          case 1:
                              // Call Payment Interface screen code
                              DoctorAppointmentBookingByNameDAOImpl doctorAppointmentBookingByNameDAO = new DoctorAppointmentBookingByNameDAOImpl();
                              double checkoutAmount = doctorAppointmentBookingByNameDAOImpl.fetchDoctorCharges(doctorID);
                              Patient.setPatient(patientEmail);
                              PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
                              billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.D, checkoutAmount, "");
                              int check = doctorAppointmentBookingByNameDAO.updateBillingID(billingId, inClause);

                              if(check == 0) {
                                  System.out.println("Payment done successfully!");
                              } else {
                                  System.out.println("Error occurred during payment! Make a new booking!");
                              }
                              break;

                          case 2:
                              return;
                      }
                  } else {
                      return;
                  }
              } else {
                  return;
              }
          }
      }
    }
  }

    public static void main(String[] args) throws SQLException{
        DatabaseConnection.loadDatabaseConnection();
        DoctorAppointmentByNameOutput doctorAppointmentBookingByName = new DoctorAppointmentByNameOutput();
        doctorAppointmentBookingByName.displayOutput();
    }

}

