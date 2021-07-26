package persistence.doctor.daoImpl;

import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.doctor.model.PatientDetailsModel;
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

public class ScheduledaAppointmentsDAOImpl {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    // As per Appointment Date will display Appointment schedule
    public List<Appointment> getAppointmentsDetails(Date appoitmentDate) {
        List<Appointment> appointmentList = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM doctor_appointment WHERE booked_for_date = ? and doctor_id = ?";
        try( PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, (java.sql.Date) appoitmentDate);
            ps.setInt(2, Doctor.instance().getDoctorId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appointment appointment = new Appointment();
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setBookedOnDate(rs.getDate("booked_on_date"));
                appointment.setBookedForDate(rs.getDate("booked_for_date"));
                appointment.setRescheduledDate(rs.getDate("rescheduled_date"));
                appointment.setBillingId(rs.getInt("billing_id"));
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointmentList.add(appointment);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return appointmentList;
    }

    public PatientDetailsModel getPatient(int patientId) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "select * from patients where patient_id = ?";
        ImmunizationBookingUtilImpl immunizationBookingUtil = new ImmunizationBookingUtilImpl();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Date dob = rs.getDate("patient_dob");
                int age = immunizationBookingUtil.getAge(dob.toString());
                return new PatientDetailsModel(rs.getString("patient_name"), age);
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return null;
    }

}
