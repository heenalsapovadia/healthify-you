package persistence.patient.utilImpl;

import persistence.patient.daoImpl.DoctorAppointmentBookingBySpecializationDAOImpl;
import persistence.patient.util.DoctorAppointmentBookingBySpecializationUtil;
import presentation.common.CommonErrors;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <pre>
* Class for handling utility methods of doctor appointment booking dashboard
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorAppointmentBookingBySpecializationUtilImpl implements DoctorAppointmentBookingBySpecializationUtil{

  @Override
  public boolean validateSpecialization(String specialization) {
    specialization = specialization.toUpperCase(Locale.ROOT);
    List<String> specializationList = new ArrayList<>();
    specializationList.add("ENT SPECIALIST");
    specializationList.add("CARDIOLOGIST");
    specializationList.add("PHYSICIAN");
    specializationList.add("OTHERS");

    if(specialization!=null && !specialization.equals("") && specialization.matches("^[ a-zA-Z]*$") && specializationList.contains(specialization)) {
      return true;
    } else{
        System.err.println("Invalid option! Enter specialization from the list");
        return false;
    }
  }

  @Override
  public boolean validateID(int doctorID) throws SQLException {
    DoctorAppointmentBookingBySpecializationDAOImpl doctorAppointmentBookingBySpecializationDAO = new DoctorAppointmentBookingBySpecializationDAOImpl();

    if (doctorID == 0) {
      return false;
    } else {
      if(doctorAppointmentBookingBySpecializationDAO.checkDoctorExists(doctorID) == 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  @Override
  public boolean validateDate(String date, List<String> datesAvailable) {
    String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
    if (date != null && datesAvailable != null) {
      if (!date.isEmpty() && !datesAvailable.isEmpty()) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches() && datesAvailable.contains(date)) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public int validateEmail(String email) throws SQLException {
    DoctorAppointmentBookingBySpecializationDAOImpl doctorAppointmentBookingBySpecializationDAO = new DoctorAppointmentBookingBySpecializationDAOImpl();

    String emailregex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    Pattern pattern = Pattern.compile(emailregex);
    Matcher matcher = pattern.matcher(email);
    int identifier;

    if (matcher.matches() == false || email == null || email == "") {
      System.err.println(CommonErrors.EMAIL_ERROR);
      return -1;
    } else {
      identifier = doctorAppointmentBookingBySpecializationDAO.checkPatientExists(email);
      return identifier;
    }
  }

}
