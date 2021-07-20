package persistence.doctor.util;

import persistence.doctor.model.Appointment;

public interface PrescriptionValidationUtil {
    Appointment validateAppointmentId(int id);
}
