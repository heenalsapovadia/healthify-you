package persistence.patient.dao;

import java.util.List;

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

}
