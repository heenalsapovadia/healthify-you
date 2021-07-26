package persistence.doctor.dao;

import java.util.List;
import java.util.Map;
import persistence.doctor.model.Doctor;

public interface DoctorDAO {
    Doctor getDoctor(Doctor doctor);

    String getDoctorNameById(int doctorId);
    
    Map<Integer, String> getDoctorNameById(List<Integer> doctorId);
}
