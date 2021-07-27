package persistence.doctor.daoImpl;

import persistence.common.DatabaseConstants;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Heenal Sapovadia
 * This class is responsible for managing doctor appointments
 */
public class AppointmentDAOImpl implements AppointmentDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    @Override
    public Appointment validateAppointmentId(Appointment appointment) {
        Connection conn = DatabaseConnection.instance();

        int doctor_id = Doctor.instance().getDoctorId();

        String sql = "SELECT * FROM doctor_appointment WHERE appointment_id = ? AND doctor_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointment.getAppointmentId());
            ps.setInt(2, doctor_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                appointment.setPatientId(rs.getInt(DatabaseConstants.PATIENT_ID));
                appointment.setDoctorId(rs.getInt(DatabaseConstants.DOCTOR_ID));
                appointment.setBookedOnDate(rs.getDate(DatabaseConstants.BOOKED_ON_DATE));
                appointment.setBookedForDate(rs.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                appointment.setRescheduledDate(rs.getDate(DatabaseConstants.RESCHEDULED_DATE));
                appointment.setBillingId(rs.getInt(DatabaseConstants.BILLING_ID));

                return appointment;
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Connection conn = DatabaseConnection.instance();
        int patientId = Patient.instance().getPatientId();
        String sql = "UPDATE doctor_appointment " +
                        "SET rescheduled_date = ? " +
                        "WHERE appointment_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, appointment.getRescheduledDate());
            ps.setInt(2, appointment.getAppointmentId());

            ps.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
    }

    @Override
    public List<Appointment> fetchAppointmentsForPatient(){
        Connection conn = DatabaseConnection.instance();
        int patientId = Patient.instance().getPatientId();
        List<Appointment> appointmentList = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment WHERE patient_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt(DatabaseConstants.APPOINTMENT_ID));
                appointment.setPatientId(patientId);
                appointment.setDoctorId(rs.getInt(DatabaseConstants.DOCTOR_ID));
                appointment.setBookedOnDate(rs.getDate(DatabaseConstants.BOOKED_ON_DATE));
                appointment.setBookedForDate(rs.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                appointment.setRescheduledDate(rs.getDate(DatabaseConstants.RESCHEDULED_DATE));
                appointment.setBillingId(rs.getInt(DatabaseConstants.BILLING_ID));
                appointmentList.add(appointment);
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return appointmentList;
    }
}
