package persistence.admin.utilImpl;

import persistence.admin.util.DoctorRegistrationUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorRegistrationUtilImpl implements DoctorRegistrationUtil {

    @Override
    public boolean validateID(String doctorID) {
        if(doctorID!=null && doctorID.startsWith("REGN")) {
            return true;
        } else{
        return false;
        }
    }

    @Override
    public boolean validateFirstName(String fname) {
        if(fname!=null && !fname.equals("") && fname.matches("^[a-zA-Z]*$")) {
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
        if(specialization!=null && !specialization.equals("") && specialization.matches("^[ a-zA-Z]*$")) {
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
    public boolean validateContact(String contact) {
        boolean checkP = false;
        if(contact!=null && !contact.equals("") && contact.startsWith("902")) {
            String[] tempChar = contact.split("");
            for(int i = 0; i < contact.length(); i++) {
                if(Integer.valueOf(tempChar[i]) >=0 && Integer.valueOf(tempChar[i]) <=9) {
                    checkP = true;
                } else {
                    checkP = false;
                }
            }

            if(checkP) {
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }

    @Override
    public boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern p = Pattern.compile(regex);

        if(email!=null && !email.equals("")) {
            if(p.matcher(email).matches()) {
                return true;
            } else {
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
