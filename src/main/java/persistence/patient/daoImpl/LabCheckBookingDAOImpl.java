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
        Connection connection = DatabaseConnection.getConnection();

        String sql = "INSERT INTO labcheck_appointments(patient_id, healthcheck_id, booked_for_date, billing_id) VALUES(?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, booking.getPatientId());
            preparedStatement.setInt(2, booking.getHealthcheckId());
            preparedStatement.setDate(3, booking.getBookedForDate());
            preparedStatement.setInt(4, booking.getBillingId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }

    }

    @Override
    public List<LabCheckBooking> getAllBookings() {
        Connection connection = DatabaseConnection.getConnection();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE patient_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Patient.getPatient().getPatientId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointmentId(resultSet.getInt("appointment_id"));
                labCheckBooking.setHealthcheckId(resultSet.getInt("healthcheck_id"));
                labCheckBooking.setPatientId(resultSet.getInt("patient_id"));
                labCheckBooking.setBookedForDate(resultSet.getDate("booked_for_date"));
                labCheckBooking.setRescheduledDate(resultSet.getDate("rescheduled_date"));
                labCheckBooking.setBillingId(resultSet.getInt("billing_id"));

                labCheckBookingList.add(labCheckBooking);
            }
            return labCheckBookingList;
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<LabCheckBooking> getBookingByDate(Date date) {
        Connection connection = DatabaseConnection.getConnection();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE booked_for_date = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointmentId(resultSet.getInt("appointment_id"));
                labCheckBooking.setHealthcheckId(resultSet.getInt("healthcheck_id"));
                labCheckBooking.setPatientId(resultSet.getInt("patient_id"));
                labCheckBooking.setBookedForDate(resultSet.getDate("booked_for_date"));
                labCheckBooking.setRescheduledDate(resultSet.getDate("rescheduled_date"));
                labCheckBooking.setBillingId(resultSet.getInt("billing_id"));

                labCheckBookingList.add(labCheckBooking);
            }
            return labCheckBookingList;
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return null;
    }

	@Override
	public Map<Integer, String> getHealthChecks(List<Integer> healthCheckIdList) {
		Connection connection = DatabaseConnection.getConnection();
        Map<Integer, String> labCheckMap = new HashMap<>();
        String wildcard = "?,".repeat(healthCheckIdList.size());
        String sql = "SELECT * FROM labcheck_plans WHERE checkup_id in ("+wildcard.substring(0, wildcard.length()-1)+")";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        	for(int i=0; i<healthCheckIdList.size(); i++) {
        		preparedStatement.setInt(i+1, healthCheckIdList.get(i));
        	}
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                labCheckMap.put(resultSet.getInt("checkup_id"), resultSet.getString("checkup_name"));
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return labCheckMap;
	}
}
