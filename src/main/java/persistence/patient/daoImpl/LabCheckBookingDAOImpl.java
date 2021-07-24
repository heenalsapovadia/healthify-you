package persistence.patient.daoImpl;

import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabCheckBookingDAOImpl implements LabCheckBookingDAO {
    private static final Logger LOGGER = Logger.getLogger(LabCheckBookingDAOImpl.class.getName());

    @Override
    public void insertBooking(LabCheckBooking booking) {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "INSERT INTO labcheck_appointments(patient_id, healthcheck_id, booked_for_date, billing_id) VALUES(?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, booking.getPatient_id());
            ps.setInt(2, booking.getHealthcheck_id());
            ps.setDate(3, booking.getBooked_for_date());
            ps.setInt(4, booking.getBilling_id());

            ps.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }

    }

    @Override
    public List<LabCheckBooking> getAllBookings() {
        Connection conn = DatabaseConnection.getConnection();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE patient_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, Patient.getPatient().getPatientId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointment_id(rs.getInt("appointment_id"));
                labCheckBooking.setHealthcheck_id(rs.getInt("healthcheck_id"));
                labCheckBooking.setPatient_id(rs.getInt("patient_id"));
                labCheckBooking.setBooked_for_date(rs.getDate("booked_for_date"));
                labCheckBooking.setRescheduled_date(rs.getDate("rescheduled_date"));
                labCheckBooking.setBilling_id(rs.getInt("billing_id"));

                labCheckBookingList.add(labCheckBooking);
            }
            return labCheckBookingList;
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }

    @Override
    public List<LabCheckBooking> getBookingByDate(Date date) {
        Connection conn = DatabaseConnection.getConnection();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE booked_for_date = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointment_id(rs.getInt("appointment_id"));
                labCheckBooking.setHealthcheck_id(rs.getInt("healthcheck_id"));
                labCheckBooking.setPatient_id(rs.getInt("patient_id"));
                labCheckBooking.setBooked_for_date(rs.getDate("booked_for_date"));
                labCheckBooking.setRescheduled_date(rs.getDate("rescheduled_date"));
                labCheckBooking.setBilling_id(rs.getInt("billing_id"));

                labCheckBookingList.add(labCheckBooking);
            }
            return labCheckBookingList;
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }

	@Override
	public Map<Integer, String> getHealthChecks(List<Integer> healthCheckIdList) {
		Connection connection = DatabaseConnection.getConnection();
        Map<Integer, String> labCheckMap = new HashMap<>();
        String wildcard = "?,".repeat(healthCheckIdList.size());
        String sqlStatement = "SELECT * FROM labcheck_plans WHERE checkup_id in ("+wildcard.substring(0, wildcard.length()-1)+")";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
        	for(int i=0; i<healthCheckIdList.size(); i++) {
        		preparedStatement.setInt(i+1, healthCheckIdList.get(i));
        	}
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                labCheckMap.put(resultSet.getInt("checkup_id"), resultSet.getString("checkup_name"));
            }
        }
        catch (SQLException exception){
            LOGGER.log(Level.SEVERE, exception.toString());
            System.out.println("SQL ERROR:"+exception.getMessage());
        }
        return labCheckMap;
	}
}
