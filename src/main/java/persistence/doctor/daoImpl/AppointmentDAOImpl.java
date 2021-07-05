package persistence.doctor.daoImpl;

import persistence.doctor.dao.AppointmentDAO;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean validateAppointmentId(){
        return false;
    }
}
