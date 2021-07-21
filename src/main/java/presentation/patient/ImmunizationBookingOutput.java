package presentation.patient;

import java.util.ArrayList;
import java.util.List;
import persistence.patient.daoImpl.ImmunizationBookingDAOImpl;
import persistence.patient.utilImpl.ImmunizationBookingUtilImpl;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Deeksha Sareen
 * 
 */
public class ImmunizationBookingOutput {
  
  PrintToConsole print = PrintToConsole.getInstance();
  public void immunizationBooking() {
    
    print.printHeader(ScreenTitles.bookImmunization);
    ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
    List<String> selection = new ArrayList(dao.getVaccineStock());
    selection.add(ScreenFields.goBack);
    int sel = print.printSelection(selection);
    ArrayList<String> vaccinedetail = new ArrayList<>(dao.getVaccineDetail(selection.get(sel-1)));
    if(sel!= 15) {
      print.printHeader(ScreenTitles.bookImmunization);
      print.printScreenFields(ScreenFields.vaccinename+" = "+selection.get(sel-1));
      print.printSingleNewLine();
      print.printScreenFields(ScreenFields.doses+" = "+vaccinedetail.get(1));
      print.printScreenFields(ScreenFields.agegroup+" = "+vaccinedetail.get(2));
      immunizationEligibilityCheck(Integer.parseInt(vaccinedetail.get(0)),Integer.parseInt(vaccinedetail.get(1)), vaccinedetail.get(2),Integer.parseInt(vaccinedetail.get(3)));
    }
    
  }
  
  public void immunizationEligibilityCheck(int vaccineId, int doses, String ageGroup , int vaccineGap) {
    print.printScreenFields(ScreenFields.elibilitycheck);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ImmunizationBookingUtilImpl util = new ImmunizationBookingUtilImpl();
    if(util.vaccineEligibilityCheck(vaccineId,doses,ageGroup,vaccineGap)==true) {
      print.printScreenFields(ScreenFields.success);
    }
    else {
      print.printScreenFields(ScreenFields.fail);
    }
    
  }
}
