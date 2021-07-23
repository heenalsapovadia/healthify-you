package persistence.doctor.dao;

import java.util.List;

public interface DoctorAvailabilityDAO {
    List<String> getAvailabilityByDoctor(int doctorId);
}
