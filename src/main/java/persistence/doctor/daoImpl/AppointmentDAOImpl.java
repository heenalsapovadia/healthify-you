package persistence.doctor.daoImpl;

import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDAOImpl implements AppointmentDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    @Override
    public Appointment validateAppointmentId(Appointment appointment) {
        Connection conn = DatabaseConnection.getConnection();

        int doctor_id = Doctor.getDoctor().getDoctor_id();

        String sql = "SELECT * FROM doctor_appointment WHERE appointment_id = ? AND doctor_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointment.getAppointment_id());
            ps.setInt(2, doctor_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                appointment.setPatient_id(rs.getInt("patient_id"));
                appointment.setDoctor_id(rs.getInt("doctor_id"));
                appointment.setBooked_on_date(rs.getDate("booked_on_date"));
                appointment.setBooked_for_date(rs.getDate("booked_for_date"));
                appointment.setRescheduled_date(rs.getDate("rescheduled_date"));
                appointment.setBilling_id(rs.getInt("billing_id"));

                return appointment;
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }
}
