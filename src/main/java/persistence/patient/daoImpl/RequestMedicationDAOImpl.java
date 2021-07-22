package persistence.patient.daoImpl;

import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMedicationDAOImpl {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    public List<Prescription> getPrescriptionDetails(int prescriptionId){
        List<Prescription> prescriptionList = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM prescription WHERE prescription_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, prescriptionId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescription_id(prescriptionId);
                prescription.setPatient_id(rs.getInt("patient_id"));
                prescription.setMedicine_name(rs.getString("medicine_name"));
                prescription.setMorning(rs.getInt("morning_dose"));
                prescription.setAfternoon(rs.getInt("afternoon_dose"));
                prescription.setEvening(rs.getInt("evening_dose"));
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return prescriptionList;
    }
}
