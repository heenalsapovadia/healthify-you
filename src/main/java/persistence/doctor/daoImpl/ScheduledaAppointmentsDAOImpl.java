package persistence.doctor.daoImpl;

import persistence.doctor.model.Appointment;
import presentation.startup.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduledaAppointmentsDAOImpl {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    // As per Appointment Date will display Appointment schedule
    public List<Appointment> getAppointmentsDetails(Date appoitmentDate) {
        List<Appointment> appointmentList = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM doctor_appointment WHERE booked_on_date = ?";
        try( PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, (java.sql.Date) appoitmentDate);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appointment appointment = new Appointment();
                appointment.setPatient_id(rs.getInt("patient_id"));
                appointment.setDoctor_id(rs.getInt("doctor_id"));
                appointment.setBooked_on_date(rs.getDate("booked_on_date"));
                appointment.setBooked_for_date(rs.getDate("booked_for_date"));
                appointment.setRescheduled_date(rs.getDate("rescheduled_date"));
                appointment.setBilling_id(rs.getInt("billing_id"));
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointmentList.add(appointment);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return appointmentList;
    }
}
