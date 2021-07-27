package persistence.admin.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import persistence.admin.dao.ImmunizationDoctorsDAO;
import persistence.admin.dao.ImmunizationSlotDAO;
import persistence.admin.util.CurrentWeekdays;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;

/**
 * @author Deeksha Sareen: This class is responsible for managing the
 *         immunization slots
 *
 */
public class ImmunizationSlotDAOImpl implements ImmunizationSlotDAO, ImmunizationDoctorsDAO {

	Connection conn = DatabaseConnection.instance();

	/* This method gets the doctor assigned to a specific slot */
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
				doctorId = resultSet.getInt(DatabaseConstants.DOCTOR_ASSIGNED);
			}

		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return doctorId;

	}

	/* This method gets the doctors available */
	@Override
	public Queue<Integer> getDoctorsAvailable() {
		Queue<Integer> doctorsID = new LinkedList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		String sql = "SELECT doctor_id, first_name, last_name FROM doctors";
		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(DatabaseConstants.DOCTOR_ID);
				doctorsID.add(id);
			}
		} catch (SQLException | NullPointerException e) {
			e.getMessage();
		}

		return doctorsID;
	}

	/* This method gets all the slots timings available */
	@Override
	public ArrayList<String> getSlotTiming() {
		ArrayList<String> slotTime = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		String sql = "SELECT slot_time FROM immunization_slots";
		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String slot = resultSet.getString(DatabaseConstants.SLOT_TIME);
				if (!slotTime.contains(slot)) { // if slots do not contain the slot
					slotTime.add(slot);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slotTime;

	}

	/* This is a helper method to compare dates and times */
	private int dateTimeComparator(String slot, String currentTime, String currentDate, String date, int doctor) {

		if (date.compareTo(currentDate) < 0) {
			doctor = 0;
		} else {
			if (slot.endsWith("am") && currentTime.toLowerCase().endsWith("am") // comparing the current time with the
																				// slot
																				// time
					|| slot.endsWith("pm") && currentTime.toLowerCase().endsWith("pm")) {
				if ((currentTime.toLowerCase()).compareTo(slot) > 0 && date.compareTo(currentDate) == 0) {
					doctor = 0;
				}
			} else if (slot.endsWith("am") && currentTime.toLowerCase().endsWith("pm")
					&& date.compareTo(currentDate) == 0) {
				doctor = 0;
			}
		}
		return doctor;

	}

	/* This method gets all the doctors assigned to slots */
	@Override
	public LinkedHashMap<String, ArrayList<Integer>> getAssignedDoctors(int updateChoice) {
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		LinkedHashMap<String, ArrayList<Integer>> doctorsPerDay = new LinkedHashMap<>();
		String sql = "SELECT * FROM immunization_slots";
		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String slot = resultSet.getString(DatabaseConstants.SLOT_TIME);
				int doctor = resultSet.getInt(DatabaseConstants.DOCTOR_ASSIGNED);
				String date = resultSet.getString(DatabaseConstants.SLOT_DATE);
				SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss aa");
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				Calendar now = Calendar.getInstance();
				String currentDate = dateFormatter.format(now.getTime());
				String currentTime = timeFormatter.format(new Date());
				if (updateChoice == 0) { // when slots have not been assigned
					doctor = dateTimeComparator(slot, currentTime, currentDate, date, doctor);
				}
				String weekday = resultSet.getString(DatabaseConstants.SLOTWEEKDAY);
				if (doctorsPerDay.containsKey(weekday)) {
					ArrayList<Integer> doctors = new ArrayList<>(doctorsPerDay.get(weekday));
					doctors.add(doctor);
					doctorsPerDay.put(weekday, doctors);
				} else {
					ArrayList<Integer> newEntry = new ArrayList<>();
					newEntry.add(doctor);
					doctorsPerDay.put(weekday, newEntry);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorsPerDay;
	}

	/* This method updates the slots in the database */
	@Override
	public void updateSlotsInDatabase(LinkedHashMap<String, ArrayList<Integer>> updatedRecords) {
		CurrentWeekdays week = new CurrentWeekdays();
		ArrayList<String> dates = new ArrayList<>(week.getDates());
		int i = 0;
		for (Map.Entry<String, ArrayList<Integer>> entry : updatedRecords.entrySet()) {
			String weekday = entry.getKey();
			ArrayList<Integer> slots = new ArrayList<>(entry.getValue()); // stores the slots
			String date = dates.get(i);
			int counter = 1;
			for (int slot : slots) {
				String sql = "UPDATE immunization_slots SET doctor_assigned = ? , slot_date = ?, patient_id = null where slot_time = ? and weekday = ?";
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setInt(1, slot);
					ps.setString(2, date);
					if (counter == 1) {
						ps.setString(3, "11:00:00 am"); // since we have assumed only 3 slots are there per day, these
														// values are
														// fixed.
					}
					if (counter == 2) {
						ps.setString(3, "12:00:00 pm");
					}
					if (counter == 3) {
						ps.setString(3, "01:00:00 pm");
					}
					ps.setString(4, weekday);
					ps.executeUpdate();
					counter++;
				} catch (SQLException e) {
					e.getLocalizedMessage();
				}
			}
			i++;
		}
	}

}
