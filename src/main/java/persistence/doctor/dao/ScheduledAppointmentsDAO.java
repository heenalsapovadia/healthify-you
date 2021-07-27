package persistence.doctor.dao;

import persistence.doctor.model.Appointment;
import persistence.doctor.model.ScheduledAppointmentsModel;
import java.util.Date;
import java.util.List;
/**
 * <pre>
 * Scheduled Appointments - DAO interface get all details from doctor appointment table
 * and fetches details from patient table
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface ScheduledAppointmentsDAO {

    // method to get all appointment details from doctor appointment table
     List<Appointment> getAppointmentsDetails(Date appoitmentDate);

     // patient details from patient table
     ScheduledAppointmentsModel getPatient(int patientId);
}
