package persistence.doctor.daoImpl;

import persistence.doctor.dao.DoctorDAO;
import persistence.doctor.model.Doctor;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAOImpl implements DoctorDAO {
    private static final Logger LOGGER = Logger.getLogger(DoctorDAOImpl.class.getName());

    @Override
    public Doctor getDoctor(Doctor doctor) {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM doctors WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, doctor.getEmail());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                doctor.setDoctor_id(rs.getInt("doctor_id"));
                doctor.setFirst_name(rs.getString("first_name"));
                doctor.setLast_name(rs.getString("last_name"));
                doctor.setJoining_date(rs.getDate("joining_date"));
                doctor.setDegree(rs.getString("degree"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setBirth_date(rs.getDate("birth_date"));
                doctor.setContact_number(rs.getString("contact_number"));
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
        Connection conn = DatabaseConnection.getConnection();
        StringBuilder doctorName = new StringBuilder();

        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doctorName.append(rs.getString("first_name"));
                doctorName.append(" ");
                doctorName.append(rs.getString("last_name"));
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return doctorName.toString();
    }
}
