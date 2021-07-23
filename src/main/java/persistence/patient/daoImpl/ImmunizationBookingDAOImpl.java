package persistence.patient.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistence.admin.dao.ImmunizationDoctorsDAO;
import persistence.doctor.model.Doctor;
import persistence.patient.dao.ImmunizationBookingDAO;
import persistence.patient.model.ImmunizationBooking;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;

/**
 * @author Deeksha Sareen : This class is responsible for interacting with the
 *         database for facilitating booking of a vaccine.
 *
 */
public class ImmunizationBookingDAOImpl implements ImmunizationBookingDAO, ImmunizationDoctorsDAO {

	Connection conn = DatabaseConnection.getConnection();
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;

	@Override
	public List<String> getVaccineStock() {
		List<String> vaccinesInSlot = new ArrayList<>();
		ResultSet resultSet = null;
		String sql = "SELECT vaccine_name from vaccination_stock";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String vaccine = resultSet.getString("vaccine_name");
				vaccinesInSlot.add(vaccine);
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return vaccinesInSlot;
	}

	@Override
	public List<String> getVaccineDetail(String vaccineName) {

		ArrayList<String> vaccineDetails = new ArrayList<>();
		String sql = "SELECT * from vaccination_stock where vaccine_name = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, vaccineName);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				vaccineDetails.add(resultSet.getInt("vaccine_id") + "");
				vaccineDetails.add(resultSet.getInt("doses") + "");
				vaccineDetails.add(resultSet.getString("age_group_in_years"));
				vaccineDetails.add(resultSet.getInt("gap_in_days") + "");
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return vaccineDetails;
	}

	@Override
	public List<String> getAppointments(int vaccineId, int patientId) {
		ResultSet resultSet = null;
		ArrayList<String> appointmentdates = new ArrayList<>();
		String sql = "SELECT * from immunization_appointments where patient_id = ? and vaccine_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, patientId);
			ps.setInt(2, vaccineId);
			resultSet = ps.executeQuery();
			if (resultSet.first() == false) {
				return appointmentdates;
			}
			while (resultSet.next()) {
				String dateofbooking = resultSet.getString("booked_for_date");
				appointmentdates.add(dateofbooking);

			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return appointmentdates;
	}

	@Override
	public List<String> getSlots() {
		ResultSet resultSet = null;
		List<String> appointmentdates = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate = formatter.format(date);
		String sql = "SELECT * from immunization_slots where patient_id is null and slot_date > ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, currentDate);
			resultSet = ps.executeQuery();
			if (resultSet.next() == false) {
				return appointmentdates;
			} else {
				do {
					String slotDate = resultSet.getString("slot_date");
					String weekday = resultSet.getString("weekday");
					String slotTime = resultSet.getString("slot_time");
					String slot = slotDate + "," + weekday + "," + slotTime;
					appointmentdates.add(slot);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}

		return appointmentdates;
	}

	@Override
	public boolean assignPatientinDatabase(String slotChosen, int vaccineId) {

		String[] slotDetails = slotChosen.split(",");
		Patient patient = Patient.getPatient();
		int patientId = patient.getPatientId();
		String slotDate = slotDetails[0];
		String weekday = slotDetails[1];
		String slotTime = slotDetails[2];
		int doctorId = getDoctorAssigned(weekday, slotTime);
		String sql = "UPDATE immunization_slots set patient_id = ? where slot_date = ? and weekday = ? and slot_time = ?";
		String sql1 = "INSERT into immunization_appointments (patient_id,doctor_id,booked_for_date,booked_for_time,vaccine_id) values (?,?,?,?,?)";

		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setString(2, slotDate);
			preparedStatement.setString(3, weekday);
			preparedStatement.setString(4, slotTime);
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement(sql1);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setInt(2, doctorId);
			preparedStatement.setString(3, slotDate);
			preparedStatement.setString(4, slotTime);
			preparedStatement.setInt(5, vaccineId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.getLocalizedMessage();
			return false;
		}
		return true;
	}

	@Override
	public int getDoctorAssigned(String weekday, String slotTime) {
		ResultSet resultSet = null;
		String sql = "SELECT doctor_assigned from immunization_slots WHERE weekday = ? and slot_time = ?";
		int doctorId = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, weekday);
			ps.setString(2, slotTime);
			resultSet = ps.executeQuery();
			if (resultSet.first()) {
				doctorId = resultSet.getInt("doctor_assigned");
			}

		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return doctorId;
	}

	@Override
	public Map<Integer, String> getVaccineDetailById(List<Integer> vaccineIdList) {
		String wildcard = "?,".repeat(vaccineIdList.size());
		String sql = "SELECT vaccine_id, vaccine_name from vaccination_stock where vaccine_id in ("+ wildcard.substring(0, wildcard.length()-1)+")";
		Map<Integer, String> vaccineMap = new HashMap<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			for(int i=0; i<vaccineIdList.size(); i++) {
				ps.setInt(i+1, vaccineIdList.get(i));
			}
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				vaccineMap.put(resultSet.getInt("vaccine_id"), resultSet.getString("vaccine_name"));
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return vaccineMap;
	}

	@Override
	public List<ImmunizationBooking> getVaccineIdByPatientId() {
		List<ImmunizationBooking> bookingList = new ArrayList<>();
		String sql = "SELECT * from immunization_appointments where patient_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, Patient.getPatient().getPatientId());
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ImmunizationBooking booking = new ImmunizationBooking();
				booking.setAppointmentId(resultSet.getInt("appointment_id"));
				booking.setPatientId(resultSet.getInt("patient_id"));
				booking.setDoctorId(resultSet.getInt("doctor_id"));
				booking.setBookedForDate(resultSet.getDate("booked_for_date"));
				booking.setBookedForTime(resultSet.getTime("booked_for_time"));
				booking.setVaccineId(resultSet.getInt("vaccine_id"));
				bookingList.add(booking);
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return bookingList;
	}
}
