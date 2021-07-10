package persistence.doctor.dao;

import persistence.doctor.model.Doctor;

public interface DoctorDAO {
    Doctor getDoctor(String email);
}
