package persistence.doctor.utilImpl;

import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.util.PrescriptionValidationUtil;

public class PrescriptionValidationUtilImpl implements PrescriptionValidationUtil {

    @Override
    public Appointment validateAppointmentId(int id){
        Appointment appointment = new Appointment();
        appointment.setAppointment_id(id);

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        return appointmentDAO.validateAppointmentId(appointment);
    }
}
