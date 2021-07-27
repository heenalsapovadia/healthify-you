package presentation.patient;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.patient.daoImpl.DoctorAppointmentBookingBySpecializationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.DoctorAppointmentBookingBySpecializationUtilImpl;
import presentation.common.PaymentInterfaceOutput;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static presentation.common.CommonErrors.NOT_AVAILABLE;
import static presentation.common.ScreenFields.*;
import static presentation.common.ScreenTitles.*;

/**
* <pre>
* Output class for doctor appointment booking by specialization
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorAppointmentBySpecializationOutput {

  public void displayOutput() throws SQLException {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader(APPPOINTMENT_BY_SPECIALIZATION_TITLE);

    List<String> selectionOptions = new ArrayList<>();
    selectionOptions.add("Search doctor");
    selectionOptions.add("Exit");
    int choice = consoleObj.printSelection(selectionOptions);

    while (choice != 2) {
      if (choice == 1) {
        consoleObj.printHeader(SEARCH_DOCTOR);

        Scanner sc = new Scanner(System.in);
        consoleObj.printScreenFields(DOCTOR_SPECIALIZATION_INPUT);
        String specialization = sc.nextLine().toUpperCase(Locale.ROOT);

        DoctorAppointmentBookingBySpecializationDAOImpl doctorAppointmentBookingBySpecializationDAOImpl = new DoctorAppointmentBookingBySpecializationDAOImpl();
        Map<Integer, String> doctorIdentifierList = doctorAppointmentBookingBySpecializationDAOImpl.fetchDoctorIdentifier(specialization);
        int doctorID = 0;
        if (doctorIdentifierList == null) {
          System.err.println(NOT_AVAILABLE);
          return;
        } else {
          consoleObj.printSubHeading(SUBHEADING);
          System.out.println(DOCTOR_IDENTIFIER_INPUT);
          for (Integer i : doctorIdentifierList.keySet()) {
            System.out.println(i + ": " + doctorIdentifierList.get(i));
          }
          doctorID = sc.nextInt();
          if (!doctorIdentifierList.containsKey(doctorID)) {
            System.err.println("Entered doctor identifier not in the list!");
            return;
          } else {
            Map<Integer, List<String>> result = new HashMap<>();
            result = doctorAppointmentBookingBySpecializationDAOImpl.fetchDoctorAvailability(doctorID);

            if (result != null) {
              List<String> datesAvailable = result.get(doctorID);
              DoctorAppointmentBookingBySpecializationUtilImpl doctorAppointmentBookingBySpecializationUtil = new DoctorAppointmentBookingBySpecializationUtilImpl();
              Scanner sc1 = new Scanner(System.in);

              System.out.println(DOCTOR_AVAILABILITY_DATE_INPUT);
              System.out.println(datesAvailable);

              System.out.println("Please enter your choice from the dates mentioned above");
              String appointmentDate = sc1.nextLine().trim();

              while (!doctorAppointmentBookingBySpecializationUtil.validateDate(appointmentDate, datesAvailable)) {
                Scanner sc2 = new Scanner(System.in);
                System.out.println(datesAvailable);
                System.out.println(DOCTOR_AVAILABILITY_DATE_SELECTION);
                appointmentDate = sc2.nextLine().trim();
              }

              System.out.println(EMAIL_ADDRESS_INPUT);
              String patientEmail = sc.next();
              int patientID = doctorAppointmentBookingBySpecializationUtil.validateEmail(patientEmail);

              while (patientID == -1) {
                System.err.println("Email address entered is not registered with us! Please enter registered patient email address for whom the appointment should be booked!");
                patientEmail = sc.next();
                patientID = doctorAppointmentBookingBySpecializationUtil.validateEmail(patientEmail);
              }

              LocalDate date = LocalDate.now();
              String bookedDate = date.toString();
              List<String> options = Arrays.asList("Continue For Payment", ScreenFields.EXIT);
              int option = consoleObj.printSelection(options);
              int billingId = 0;
              switch (option) {
                case 1:
                  // Call Payment Interface screen code
                  DoctorAppointmentBookingBySpecializationDAOImpl doctorAppointmentBookingBySpecializationDAO = new DoctorAppointmentBookingBySpecializationDAOImpl();
                  double checkoutAmount = doctorAppointmentBookingBySpecializationDAO.fetchDoctorCharges(doctorID);
                  Patient.setPatient(patientEmail);
                  PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
                  billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.D, checkoutAmount, "");
                  int appointmentID = doctorAppointmentBookingBySpecializationDAOImpl.addDoctorAppointment(patientID, doctorID, bookedDate, appointmentDate, billingId);
                  if (appointmentID != -1) {
                    System.out.println("Appointment booked successfully!");
                    System.out.println("Appointment ID: " +appointmentID);
                  } else {
                    System.err.println("An error occured, make a new booking!");
                  }
                  break;

                case 2:
                  return;
              }
            } else {
              System.err.println("Day availability for the mentioned doctor not updated in the system!");
            }
          }
        }
      }

      if (choice == 2) {
        DoctorAppointmentBookingDashboard doctorAppointmentBookingDashboard = new DoctorAppointmentBookingDashboard();
        doctorAppointmentBookingDashboard.display();
      }

      consoleObj.printHeader(APPPOINTMENT_BY_SPECIALIZATION_TITLE);
      choice = consoleObj.printSelection(selectionOptions);

    }

    if (choice == 2) {
      DoctorAppointmentBookingDashboard doctorAppointmentBookingDashboard = new DoctorAppointmentBookingDashboard();
      doctorAppointmentBookingDashboard.display();
    }
  }

}

