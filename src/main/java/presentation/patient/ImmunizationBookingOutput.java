package presentation.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistence.patient.daoImpl.ImmunizationBookingDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.ImmunizationBookingUtilImpl;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Deeksha Sareen : This class is responsible for the flow of the
 *         immunization booking dashboard.
 * 
 */
public class ImmunizationBookingOutput {

  PrintToConsole print = PrintToConsole.getInstance();
  ImmunizationBookingUtilImpl util = new ImmunizationBookingUtilImpl();
  ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
  String vaccineName;

  public void immunizationBooking() {

    print.printHeader(ScreenTitles.bookImmunization);
    List<String> selection = new ArrayList(dao.getVaccineStock());
    selection.add(ScreenFields.goBack);
    print.printSubHeading(ScreenTitles.vaccines);
    int sel = print.printSelection(selection);
    ArrayList<String> vaccinedetail = new ArrayList<>(dao.getVaccineDetail(selection.get(sel - 1)));
    if (sel != 17) {
      vaccineName = selection.get(sel - 1);
      print.printHeader(ScreenTitles.bookImmunization);
      print.printScreenFields(ScreenFields.vaccinename + "        =     " + selection.get(sel - 1));
      print.printScreenFields(ScreenFields.doses + "     =     " + vaccinedetail.get(1));
      print.printScreenFields(ScreenFields.agegroup + "     =     " + vaccinedetail.get(2));
      immunizationEligibilityCheck(Integer.parseInt(vaccinedetail.get(0)), Integer.parseInt(vaccinedetail.get(1)),
              vaccinedetail.get(2), Integer.parseInt(vaccinedetail.get(3)));
    }
    if (sel == 17) {
      BookingDashboard dashboard = BookingDashboard.getInstance();
      dashboard.displayOutput();
    }

  }

  public void immunizationEligibilityCheck(int vaccineId, int doses, String ageGroup, int vaccineGap) {
    print.printSingleNewLine();
    print.printScreenFields(ScreenFields.elibilitycheck);
    
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.getMessage();
    }
    if (util.vaccineEligibilityCheck(vaccineId, doses, ageGroup, vaccineGap) == true) {
      print.printScreenFields(ScreenFields.success);
      bookVaccine(vaccineId);
    } else {
      print.printScreenFields(ScreenFields.fail);
      print.printSingleNewLine();
    }

  }

  public void bookVaccine(int vaccineId) {
    print.printScreenFields("Book an appointment? Yes / No");
    print.printScreenFields(ScreenFields.selection);
    List<String> selection = Arrays.asList("Yes", "No");
    int choice = print.printSelection(selection);

    if (choice == 1) {
      print.printHeader(ScreenTitles.bookImmunization);
      if (dao.getSlots().isEmpty()) {
        print.printScreenFields("Sorry. No slots are available. Try again next week");
      }
      List<String> selection2 = dao.getSlots();
      int sel = print.printSelection(selection2);
      if (sel != 0) {
        String slot = dao.getSlots().get(sel - 1);
        if (dao.assignPatientinDatabase(dao.getSlots().get(sel - 1), vaccineId)) {
          confirmationStatus(slot);
        }
      } else {
        print.printScreenFields(CommonErrors.errorMessage);
      }
    }
    
  }

  private void confirmationStatus(String slot) {
    print.printScreenFields("________________");
    Patient patient = Patient.getPatient();
    print.printScreenFields("Successfully booked immunization appointment !");
    String patientName = patient.getPatientName();
    int patientAge = util.getAge(patient.getPatientDob());
    String patientGender = patient.getPatientGender();
    print.printScreenFields("Name     =  " + patientName);
    print.printScreenFields("Age      =  " + patientAge);
    print.printScreenFields("Gender   =  " + patientGender);
    print.printScreenFields("Vaccine  =  " + vaccineName);
    print.printScreenFields("Block    =  Vaccine Centre");
    print.printScreenFields("Time     =  " + slot);
    print.printSingleNewLine();
    print.printScreenFields("Please reach designated block with your immunization card. Thank you!");
  }
}
