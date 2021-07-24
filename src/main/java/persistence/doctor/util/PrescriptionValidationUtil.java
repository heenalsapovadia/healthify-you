package persistence.doctor.util;

import persistence.doctor.model.Appointment;

import java.util.Set;

public interface PrescriptionValidationUtil {
    Appointment validateAppointmentId(int id);

    boolean validateMedicineName(String medicineName, Set<String> medicineList);
}
