package persistence.doctor.utilImpl;

import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Appointment;

public class PrescriptionValidationUtilImpl {
    /*
    create doctor appointmnet models and dao
    create appointment model obj - set appt id
    fetch appt from table
    and validate
     */
    public Appointment validateAppointmentId(int id){
        Appointment appointment = new Appointment();
        appointment.setAppointment_id(id);

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        return appointmentDAO.validateAppointmentId(appointment);
    }
}
