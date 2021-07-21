package persistence.patient.utilImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import persistence.patient.daoImpl.ImmunizationBookingDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.ImmunizationBookingUtil;
import presentation.common.PrintToConsole;

/**
 * @author Deeksha Sareen
 *
 */
public class ImmunizationBookingUtilImpl implements ImmunizationBookingUtil {


  PrintToConsole print = PrintToConsole.getInstance();
  
  @Override
  public boolean vaccineEligibilityCheck(int vaccineId, int doses, String ageGroup, int vaccineGap) {
    Patient patient = Patient.getPatient();
    String dob = patient.getPatientDob();
    int patientId = patient.getPatientId();
    int patientAge = getAge(dob);
    if(checkAge(patientAge, ageGroup)!=false ) {
      if(checkLastDate(vaccineId, patientId, vaccineGap)!=false) {
        if(checkDoses(doses, vaccineId, patientId)!=false) {
          return true;
        }
      }
    }
    return false;

  }

  private boolean checkDoses(int doses, int vaccineId, int patientId) {
    ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
    ArrayList<String> appointmentsdates = new ArrayList<>(dao.getAppointments(vaccineId, patientId));
    if (appointmentsdates.size() > doses) {
      print.printScreenFields("You have exceeded the dose limit");
      return false;
    }
    return true;

  }

  public int getAge(String dob) {
    String dateofbirth = dob;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    int age = 0;
    try {
      date = sdf.parse(dateofbirth);
      Calendar calender = Calendar.getInstance();
      calender.setTime(date);
      int year = calender.get(Calendar.YEAR);
      int month = calender.get(Calendar.MONTH) + 1;
      int day = calender.get(Calendar.DATE);
      LocalDate localdate = LocalDate.of(year, month, day);
      LocalDate currentdate = LocalDate.now();
      Period period = Period.between(localdate, currentdate);
      age = period.getYears();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return age;
  }

  private boolean checkAge(int patientage, String agegroup) {
    String[] agerange = agegroup.split(" to ");
    int leftrange = (int)Integer.parseInt(agerange[0]);
    int rightrange = (int)Integer.parseInt(agerange[0]);
          
    if (patientage >= leftrange && patientage <= rightrange) {
      return true;
    }
    print.printScreenFields("Your age ( "+ patientage+" years ) is not eligible for this vaccine");
    return false;

  }

  private boolean checkLastDate(int vaccineId, int patientId, int vaccineGap) {

    ImmunizationBookingDAOImpl dao = new ImmunizationBookingDAOImpl();
    ArrayList<String> appointmentsdates = new ArrayList<>(dao.getAppointments(vaccineId, patientId));
    if(appointmentsdates.size()==0) {
      return true;
    }
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());
    String recentDate = Collections.max(appointmentsdates);
    if (getAge(recentDate) >= vaccineGap) {
      return true;
    }
    else {
       int days = vaccineGap-getAge(recentDate);
       print.printScreenFields("Come back after" + days + "days");
    }
    return false;

  }

}
