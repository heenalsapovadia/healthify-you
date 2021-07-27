package persistence.doctor.daoImpl;

import persistence.common.DatabaseConstants;
import persistence.doctor.dao.ScheduledAppointmentsDAO;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.doctor.model.ScheduledAppointmentsModel;
import persistence.patient.utilImpl.ImmunizationBookingUtilImpl;
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
/**
 * <pre>
 *  Scheduled Appointments database functionality
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class ScheduledAppointmentsDAOImpl implements ScheduledAppointmentsDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    // As per Appointment Date will display Appointment schedule
    public List<Appointment> getAppointmentsDetails(Date appoitmentDate) {
        List<Appointment> appointmentList = new ArrayList<>();
        Connection conn = DatabaseConnection.instance();
        String sql = "SELECT * FROM doctor_appointment WHERE booked_for_date = ? and doctor_id = ?";
        try( PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, (java.sql.Date) appoitmentDate);
            ps.setInt(2, Doctor.instance().getDoctorId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appointment appointment = new Appointment();
                appointment.setPatientId(rs.getInt(DatabaseConstants.PATIENT_ID));
                appointment.setDoctorId(rs.getInt(DatabaseConstants.DOCTOR_ID));
                appointment.setBookedOnDate(rs.getDate(DatabaseConstants.BOOKED_ON_DATE));
                appointment.setBookedForDate(rs.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                appointment.setRescheduledDate(rs.getDate(DatabaseConstants.RESCHEDULED_DATE));
                appointment.setBillingId(rs.getInt(DatabaseConstants.BILLING_ID));
                appointment.setAppointmentId(rs.getInt(DatabaseConstants.APPOINTMENT_ID));
                appointmentList.add(appointment);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return appointmentList;
    }

    public ScheduledAppointmentsModel getPatient(int patientId) {
        Connection conn = DatabaseConnection.instance();
        String sql = "select * from patients where patient_id = ?";
        ImmunizationBookingUtilImpl immunizationBookingUtil = new ImmunizationBookingUtilImpl();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Date dob = rs.getDate(DatabaseConstants.PATIENT_DOB);
                int age = immunizationBookingUtil.getAge(dob.toString());
                return new ScheduledAppointmentsModel(rs.getString(DatabaseConstants.PATIENT_NAME), age);
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return null;
    }
}
