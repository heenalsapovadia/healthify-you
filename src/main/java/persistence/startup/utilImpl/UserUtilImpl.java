package persistence.startup.utilImpl;

import persistence.doctor.model.Doctor;
import persistence.startup.util.UserUtil;

public class UserUtilImpl implements UserUtil {

    @Override
    public void loadUser(String email, String userType){
        switch (userType) {
            case "D":
                Doctor.setDoctor(email);
                break;
            case "P":
//                Patient.setPatient(email);
                break;
            case "A":
//                Admin.setAdmin(email);
                break;
        }

    }
}
