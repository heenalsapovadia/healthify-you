package persistence.doctor.daoImpl;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.model.Prescription;
import presentation.startup.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrescriptionDAOImpl implements PrescriptionDAO {

    @Override
    public void insertPrescription(List<Prescription> prescriptionList){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.loadDatabaseConnection();
        /*
        find existing max prescription id,
        and increment by 1
         */
        PrescriptionDAOImpl prescriptionDAO = new PrescriptionDAOImpl();
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        for(Prescription prescription : prescriptionList){
            String sql = "INSERT INTO prescription(prescription_id, appointment_id, patient_id, doctor_id, doctor_name, medicine_name, morning_dose,  afternoon_dose, evening_dose) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, prescriptionId);
                ps.setInt(2, prescription.getAppointment_id());
                ps.setInt(3, prescription.getPatient_id());
                ps.setInt(4, prescription.getDoctor_id());
                ps.setString(5, prescription.getDoctor_name());
                ps.setString(6, prescription.getMedicine_name());
                ps.setInt(7, prescription.getMorning());
                ps.setInt(8, prescription.getAfternoon());
                ps.setInt(9, prescription.getEvening());

                ps.executeUpdate();
            }
            catch (SQLException e){
//                LOGGER.log(Level.SEVERE, e.toString());
                System.out.println("SQL ERROR:"+e.getMessage());
            }
        }
    }

    @Override
    public int findMaxPrescriptionId() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.loadDatabaseConnection();

        String sql = "SELECT MAX(prescription_id) FROM prescription";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("MAX(prescription_id)");
        }
        catch (SQLException e){
//                LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return 0;
    }
}
