package persistence.doctor.utilImpl;

import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.util.PrescriptionValidationUtil;

import java.util.Set;

/**
 * @author Heenal Sapovadia
 * This class is responsible for Doctor Prescription related Validations
 */
public class PrescriptionValidationUtilImpl implements PrescriptionValidationUtil {

    @Override
    public Appointment validateAppointmentId(int id){
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(id);

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        return appointmentDAO.validateAppointmentId(appointment);
    }

    @Override
    public boolean validateMedicineName(String medicineName, Set<String> medicineList) {
        String medicineNameToLowerCase = medicineName.toLowerCase();
        return medicineList.contains(medicineNameToLowerCase);
    }
}
