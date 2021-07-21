package persistence.doctor.dao;

import persistence.doctor.model.Appointment;

import java.util.List;

public interface AppointmentDAO {
     Appointment validateAppointmentId(Appointment appointment);

     void updateAppointment(Appointment appointment);

     List<Appointment> fetchAppointmentsForPatient();
}
