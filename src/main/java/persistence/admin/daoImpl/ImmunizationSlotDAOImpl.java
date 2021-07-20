package persistence.admin.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;

import persistence.admin.dao.ImmunizationSlotDAO;
import persistence.admin.util.CurrentWeekdays;
import persistence.admin.utilImpl.ImmunizationSlotUtilImpl;
import presentation.startup.DatabaseConnection;

public class ImmunizationSlotDAOImpl implements ImmunizationSlotDAO {

  Connection conn = DatabaseConnection.getConnection();
  
  @Override
  public int getLastDoctorAssigned() {
    ResultSet resultSet = null;
    String sql = "SELECT doctor_assigned from immunization_slots WHERE weekday = ? and slot_time = ?";
    int doctorId = 0;
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, "Friday");
      ps.setString(2, "01:00:00 pm");
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
  public Queue<Integer> getDoctorsAvailable() {
    Queue<Integer> doctorsID = new LinkedList<>();
    PreparedStatement preparedStatement;
    ResultSet resultSet = null;
    String sql = "SELECT doctor_id, first_name, last_name FROM doctors";
    try {
      preparedStatement = conn.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("doctor_id");
        doctorsID.add(id);
      }
    } catch (SQLException | NullPointerException e) {
      e.getMessage();
    }

    return doctorsID;
  }

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
        String slot = resultSet.getString("slot_time");
        if (!slotTime.contains(slot)) {
          slotTime.add(slot);
        }

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return slotTime;

  }

  private int dateTimeComparator(String slot, String currentTime, String currentDate, String date , int doctor) {
    
    if (slot.endsWith("am") && currentTime.toLowerCase().endsWith("am")
            || slot.endsWith("pm") && currentTime.toLowerCase().endsWith("pm")) {
      if ((currentTime.toLowerCase()).compareTo(slot) > 0 && date.compareTo(currentDate) <= 0) {
        doctor = 0;
      }
    } else if (slot.endsWith("am") && currentTime.toLowerCase().endsWith("pm") && date.compareTo(currentDate) <= 0) {
      doctor = 0;
    }
    return doctor;

  }

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
        String slot = resultSet.getString("slot_time");
        int doctor = resultSet.getInt("doctor_assigned");
        String date = resultSet.getString("slot_date");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss aa");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd");
        Calendar now = Calendar.getInstance();
        String currentDate = dateFormatter.format(now.getTime());
        String currentTime = timeFormatter.format(new Date());
        if (updateChoice == 0) {
          doctor = dateTimeComparator(slot, currentTime, currentDate, date , doctor);
        }
        String weekday = resultSet.getString("weekday");
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
  
  @Override
  public void updateSlotsInDatabase(LinkedHashMap<String, ArrayList<Integer>> updatedRecords) {
    CurrentWeekdays week = new CurrentWeekdays();
    ArrayList<String> dates = new ArrayList<>(week.getDates());
    int i = 0;
    for (Map.Entry<String, ArrayList<Integer>> entry : updatedRecords.entrySet()) {
      String weekday = entry.getKey();
      ArrayList<Integer> slots = new ArrayList<>(entry.getValue());
      String date = dates.get(i);
      int counter = 1;
      for (int slot : slots) {
        String sql = "UPDATE immunization_slots SET doctor_assigned = ? , slot_date = ? where slot_time = ? and weekday = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
          ps.setInt(1, slot);
          ps.setString(2, date);
          if (counter == 1) {
            ps.setString(3, "11:00:00 am");
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
