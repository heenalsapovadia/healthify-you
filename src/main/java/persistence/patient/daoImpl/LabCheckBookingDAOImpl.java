package persistence.patient.daoImpl;

import persistence.common.DatabaseConstants;
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

/**
 * @author Heenal Sapovadia
 *
 */
public class LabCheckBookingDAOImpl implements LabCheckBookingDAO {
    private static final Logger LOGGER = Logger.getLogger(LabCheckBookingDAOImpl.class.getName());

    @Override
    public void insertBooking(LabCheckBooking booking) {
        Connection connection = DatabaseConnection.instance();

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
        Connection connection = DatabaseConnection.instance();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE patient_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Patient.instance().getPatientId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointmentId(resultSet.getInt(DatabaseConstants.APPOINTMENT_ID));
                labCheckBooking.setHealthcheckId(resultSet.getInt(DatabaseConstants.HEALTHCHECK_ID));
                labCheckBooking.setPatientId(resultSet.getInt(DatabaseConstants.PATIENT_ID));
                labCheckBooking.setBookedForDate(resultSet.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                labCheckBooking.setRescheduledDate(resultSet.getDate(DatabaseConstants.RESCHEDULED_DATE));
                labCheckBooking.setBillingId(resultSet.getInt(DatabaseConstants.BILLING_ID));

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
        Connection connection = DatabaseConnection.instance();

        List<LabCheckBooking> labCheckBookingList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_appointments WHERE booked_for_date = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheckBooking labCheckBooking = new LabCheckBooking();
                labCheckBooking.setAppointmentId(resultSet.getInt(DatabaseConstants.APPOINTMENT_ID));
                labCheckBooking.setHealthcheckId(resultSet.getInt(DatabaseConstants.HEALTHCHECK_ID));
                labCheckBooking.setPatientId(resultSet.getInt(DatabaseConstants.PATIENT_ID));
                labCheckBooking.setBookedForDate(resultSet.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                labCheckBooking.setRescheduledDate(resultSet.getDate(DatabaseConstants.RESCHEDULED_DATE));
                labCheckBooking.setBillingId(resultSet.getInt(DatabaseConstants.BILLING_ID));

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
		Connection connection = DatabaseConnection.instance();
        Map<Integer, String> labCheckMap = new HashMap<>();
        String wildcard = "?,".repeat(healthCheckIdList.size());
        String sql = "SELECT * FROM labcheck_plans WHERE checkup_id in ("+wildcard.substring(0, wildcard.length()-1)+")";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        	for(int i=0; i<healthCheckIdList.size(); i++) {
        		preparedStatement.setInt(i+1, healthCheckIdList.get(i));
        	}
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                labCheckMap.put(resultSet.getInt(DatabaseConstants.CHECKUP_ID), resultSet.getString(DatabaseConstants.CHECKUP_NAME));
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return labCheckMap;
	}
}
