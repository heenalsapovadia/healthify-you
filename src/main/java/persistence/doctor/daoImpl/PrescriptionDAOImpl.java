package persistence.doctor.daoImpl;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionDAOImpl implements PrescriptionDAO {

    private static final Logger LOGGER = Logger.getLogger(PrescriptionDAOImpl.class.getName());

    @Override
    public void insertPrescription(List<Prescription> prescriptionList){
        Connection conn = DatabaseConnection.getConnection();
        /*
        find existing max prescription id,
        and increment by 1
         */
        PrescriptionDAOImpl prescriptionDAO = new PrescriptionDAOImpl();
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        for(Prescription prescription : prescriptionList){
            String sql = "INSERT INTO prescription(prescription_id, appointment_id, patient_id, doctor_id, doctor_name, " +
                    "medicine_name, morning_dose,  afternoon_dose, evening_dose, dosage_days, prescription_date) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
                ps.setInt(10, prescription.getDosageDays());
                ps.setDate(11, prescription.getDate());

                ps.executeUpdate();
            }
            catch (SQLException e){
                LOGGER.log(Level.SEVERE, e.toString());
                System.out.println("SQL ERROR:"+e.getMessage());
            }
        }
    }

    @Override
    public int findMaxPrescriptionId() {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT MAX(prescription_id) FROM prescription";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("MAX(prescription_id)");
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Prescription> getPrescriptionById(int prescriptionId){
        List<Prescription> prescriptionList = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM prescription WHERE prescription_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, prescriptionId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescription_id(prescriptionId);
                prescription.setAppointment_id(rs.getInt("appointment_id"));
                prescription.setDoctor_id(rs.getInt("doctor_id"));
                prescription.setDoctor_name(rs.getString("doctor_name"));
                prescription.setPatient_id(rs.getInt("patient_id"));
                prescription.setMedicine_name(rs.getString("medicine_name"));
                prescription.setMorning(rs.getInt("morning_dose"));
                prescription.setAfternoon(rs.getInt("afternoon_dose"));
                prescription.setEvening(rs.getInt("evening_dose"));
                prescription.setDosageDays(rs.getInt("dosage_days"));
                prescription.setDate(rs.getDate("prescription_date"));
                prescription.setBillingId(rs.getInt("billing_id"));

                prescriptionList.add(prescription);
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return prescriptionList;
    }

	@Override
	public List<Prescription> getPrescriptionByPatientId() {
		List<Prescription> prescriptionList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sqlStatement = "SELECT * FROM prescription WHERE patient_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setInt(1, Patient.getPatient().getPatientId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescription_id(resultSet.getInt("prescription_id"));
                prescription.setAppointment_id(resultSet.getInt("appointment_id"));
                prescription.setDoctor_id(resultSet.getInt("doctor_id"));
                prescription.setDoctor_name(resultSet.getString("doctor_name"));
                prescription.setPatient_id(resultSet.getInt("patient_id"));
                prescription.setMedicine_name(resultSet.getString("medicine_name"));
                prescription.setMorning(resultSet.getInt("morning_dose"));
                prescription.setAfternoon(resultSet.getInt("afternoon_dose"));
                prescription.setEvening(resultSet.getInt("evening_dose"));
                prescription.setDate(resultSet.getDate("prescription_date"));
                prescription.setBillingId(resultSet.getInt("billing_id"));
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException exception){
            LOGGER.log(Level.SEVERE, exception.toString());
        }
        return prescriptionList;
    }
}
