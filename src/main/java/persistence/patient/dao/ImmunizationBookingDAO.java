package persistence.patient.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deeksha Sareen
 *
 */

public interface ImmunizationBookingDAO {
  
    public List<String> getVaccineStock();
    public List<String> getVaccineDetail(String vaccineName);
    /**
     * @param vaccineId
     * @param patientId
     * @return
     */
    ArrayList<String> getAppointments(int vaccineId, int patientId);
}
