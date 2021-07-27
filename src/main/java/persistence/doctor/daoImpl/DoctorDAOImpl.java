package persistence.doctor.daoImpl;

import persistence.common.DatabaseConstants;
import persistence.doctor.dao.DoctorDAO;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Heenal Sapovadia
 *
 */
public class DoctorDAOImpl implements DoctorDAO {
    private static final Logger LOGGER = Logger.getLogger(DoctorDAOImpl.class.getName());

    @Override
    public Doctor getDoctor(Doctor doctor) {
        Connection conn = DatabaseConnection.instance();

        String sql = "SELECT * FROM doctors WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, doctor.getEmail());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setJoiningDate(rs.getDate("joining_date"));
                doctor.setDegree(rs.getString("degree"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setBirthDate(rs.getDate("birth_date"));
                doctor.setContactNumber(rs.getString("contact_number"));
                doctor.setCity(rs.getString("city"));
                return doctor;
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }

    @Override
    public String getDoctorNameById(int doctorId){
        Connection conn = DatabaseConnection.instance();
        StringBuilder doctorName = new StringBuilder();

        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doctorName.append(rs.getString(DatabaseConstants.FIRST_NAME));
                doctorName.append(" ");
                doctorName.append(rs.getString(DatabaseConstants.LAST_NAME));
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return doctorName.toString();
    }
    
    @Override
    public Map<Integer, String> getDoctorNameById(List<Integer> doctorId){
    	Map<Integer, String> doctorMap = new HashMap<>();
        Connection conn = DatabaseConnection.instance();
        StringBuilder doctorName;
        String wildcard = "?,".repeat(doctorId.size());
        String sql = "SELECT * FROM doctors WHERE doctor_id in ("+wildcard.substring(0, wildcard.length()-1)+")";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
        	for(int i=0; i<doctorId.size(); i++) {
        		ps.setInt(i+1, doctorId.get(i));
        	}
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	doctorName = new StringBuilder();
                doctorName.append(rs.getString(DatabaseConstants.FIRST_NAME));
                doctorName.append(" ");
                doctorName.append(rs.getString(DatabaseConstants.LAST_NAME));
                doctorMap.put(rs.getInt(DatabaseConstants.DOCTOR_ID), doctorName.toString());
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return doctorMap;
    }
}
