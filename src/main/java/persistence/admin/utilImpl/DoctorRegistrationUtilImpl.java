package persistence.admin.utilImpl;

import persistence.admin.daoImpl.DoctorRegistrationDAOImpl;
import persistence.admin.util.DoctorRegistrationUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <pre>
* Perform operations for registering doctor in the system
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRegistrationUtilImpl implements DoctorRegistrationUtil {

  @Override
  public boolean validateFirstName(String fname) {
    if(fname!=null && !fname.equals("") && fname.matches("^[ a-zA-Z]*$")) {
      return true;
    } else{
        return false;
    }
  }

  @Override
  public boolean validateLastName(String lname) {
    if(lname!=null && !lname.equals("") && lname.matches("^[a-zA-Z]*$")) {
      return true;
    } else{
        return false;
    }
  }

  @Override
  public boolean validateDegree(String degree) {
    if(degree!=null && !degree.equals("") && degree.matches("^[ a-zA-Z]*$")) {
      return true;
    } else{
        return false;
    }
  }

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
        return false;
    }
  }

  @Override
  public boolean validateCity(String city) {
    if(city!=null && !city.equals("") && city.matches("^[ a-zA-Z]*$")) {
      return true;
    } else{
        return false;
    }
  }

  @Override
  public boolean validateContact(Long contact) {

    int length = (int) (Math.log10(contact) + 1);

    if(length == 10) {
      String temp = contact.toString();

      if(temp.startsWith("902")) {
        return true;
      } else {
          System.err.println("The contact number should begin with 902");
          return false;
      }
    } else {
        System.err.println("Contact number should be 10 digits long!");
        return false;
    }
  }

  @Override
  public boolean validateEmail(String email) {
    DoctorRegistrationDAOImpl doctorRegistrationDAOImpl = new DoctorRegistrationDAOImpl();
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                 "[a-zA-Z0-9_+&*-]+)*@healthifyyou.com";

    Pattern p = Pattern.compile(regex);

    if(email!=null && !email.equals("")) {
      if(p.matcher(email).matches()) {
        if(doctorRegistrationDAOImpl.checkDoctorExists(email)) {
          System.out.println("Doctor is registered with the system already!");
          return false;
        } else {
            return true;
        }
      } else {
          System.out.println("Invalid email address format! The email address should include @ and . ");
          return false;
      }
    } else {
        return false;
    }
  }

  @Override
  public boolean validatePassword(String password) {
    String regex = "^(?=.*[0-9])"
               + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

    Pattern p = Pattern.compile(regex);

    if(password!=null && !password.equals("")) {
      if(p.matcher(password).matches()){
        return true;
      } else {
          return false;
      }
    } else {
        return false;
    }
  }

  @Override
  public boolean validateDate(String date) {
    String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(date);
    if (matcher.matches()) {
      return true;
    } else {
        return false;
    }
  }

}
