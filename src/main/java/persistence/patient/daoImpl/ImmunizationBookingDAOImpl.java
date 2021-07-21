package persistence.patient.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.patient.dao.ImmunizationBookingDAO;
import presentation.startup.DatabaseConnection;

/**
 * @author Deeksha Sareen
 *
 */
public class ImmunizationBookingDAOImpl implements ImmunizationBookingDAO{

  Connection conn = DatabaseConnection.getConnection();
  @Override
  public List<String> getVaccineStock() {
    List<String> vaccinesInSlot = new ArrayList<>();
    ResultSet resultSet = null;
    String sql = "SELECT vaccine_name from vaccination_stock";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      resultSet = ps.executeQuery();
      while(resultSet.next()) {
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
    ResultSet resultSet = null;
    ArrayList<String> vaccineDetails = new ArrayList<>();
    String sql = "SELECT * from vaccination_stock where vaccine_name = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, vaccineName);
      resultSet = ps.executeQuery();
      while(resultSet.next()) {
         vaccineDetails.add(resultSet.getInt("vaccine_id")+"");
         vaccineDetails.add(resultSet.getInt("doses")+"");
         vaccineDetails.add(resultSet.getString("age_group_in_years"));
         vaccineDetails.add(resultSet.getInt("gap_in_days")+"");
      }
    } catch (SQLException e) {
      e.getLocalizedMessage();
    }
    return vaccineDetails;
  }

  @Override
  public ArrayList<String> getAppointments( int vaccineId, int patientId ) {
    ResultSet resultSet = null;
    ArrayList<String> appointmentdates = new ArrayList<>();
    String sql = "SELECT * from immunization_appointments where patient_id = ? and vaccine_id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, patientId);
      ps.setInt(1, vaccineId);
      resultSet = ps.executeQuery();
      if(resultSet.first() == false) {
        return appointmentdates;
      }
      while(resultSet.next()) {
         String dateofbooking = resultSet.getString("booked_for_date");
         appointmentdates.add(dateofbooking);
         
      }
    } catch (SQLException e) {
      e.getLocalizedMessage();
    }
    return appointmentdates;
  }

}
