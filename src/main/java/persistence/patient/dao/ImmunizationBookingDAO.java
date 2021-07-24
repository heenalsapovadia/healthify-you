package persistence.patient.dao;

import java.util.List;
import java.util.Map;
import persistence.patient.model.ImmunizationBooking;

/**
 * @author Deeksha Sareen
 *
 */

public interface ImmunizationBookingDAO {

  public List<String> getVaccineStock();

  public List<String> getVaccineDetail(String vaccineName);

  public List<String> getAppointments(int vaccineId, int patientId);

  public List<String> getSlots();

  public boolean assignPatientinDatabase(String slotChosen, int vaccineId);
  
  public Map<Integer, String> getVaccineDetailById(List<Integer> vaccineId);
  
  public List<ImmunizationBooking> getVaccineIdByPatientId();
  
}
