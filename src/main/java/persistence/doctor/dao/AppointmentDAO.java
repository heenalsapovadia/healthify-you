package persistence.doctor.dao;

import persistence.doctor.model.Appointment;

public interface AppointmentDAO {
     Appointment validateAppointmentId(Appointment appointment);
}
