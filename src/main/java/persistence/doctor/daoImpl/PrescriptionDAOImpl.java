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
        Connection connection = DatabaseConnection.getConnection();
        /*
        find existing max prescription id,
        and increment by 1
         */
        PrescriptionDAOImpl prescriptionDAO = new PrescriptionDAOImpl();
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        for(Prescription prescription : prescriptionList){
            String sql = "INSERT INTO prescription(prescription_id, appointment_id, patient_id, doctor_id, doctor_name, medicine_name, morning_dose,  afternoon_dose, evening_dose) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, prescriptionId);
                preparedStatement.setInt(2, prescription.getAppointmentId());
                preparedStatement.setInt(3, prescription.getPatientId());
                preparedStatement.setInt(4, prescription.getDoctorId());
                preparedStatement.setString(5, prescription.getDoctorName());
                preparedStatement.setString(6, prescription.getMedicineName());
                preparedStatement.setInt(7, prescription.getMorning());
                preparedStatement.setInt(8, prescription.getAfternoon());
                preparedStatement.setInt(9, prescription.getEvening());

                preparedStatement.executeUpdate();
            }
            catch (SQLException sqlException){
                LOGGER.log(Level.SEVERE, sqlException.toString());
                System.out.println("SQL ERROR:"+sqlException.getMessage());
            }
        }
    }

    @Override
    public int findMaxPrescriptionId() {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT MAX(prescription_id) FROM prescription";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt("MAX(prescription_id)");
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Prescription> getPrescriptionById(int prescriptionId){
        List<Prescription> prescriptionList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM prescription WHERE prescription_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, prescriptionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(prescriptionId);
                prescription.setAppointmentId(resultSet.getInt("appointment_id"));
                prescription.setDoctorId(resultSet.getInt("doctor_id"));
                prescription.setDoctorName(resultSet.getString("doctor_name"));
                prescription.setPatientId(resultSet.getInt("patient_id"));
                prescription.setMedicineName(resultSet.getString("medicine_name"));
                prescription.setMorning(resultSet.getInt("morning_dose"));
                prescription.setAfternoon(resultSet.getInt("afternoon_dose"));
                prescription.setEvening(resultSet.getInt("evening_dose"));
                prescription.setPrescriptionDate(resultSet.getDate("prescription_date"));
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return prescriptionList;
    }

	@Override
	public List<Prescription> getPrescriptionByPatientId() {
		List<Prescription> prescriptionList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM prescription WHERE patient_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Patient.getPatient().getPatientId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(resultSet.getInt("prescription_id"));
                prescription.setAppointmentId(resultSet.getInt("appointment_id"));
                prescription.setDoctorId(resultSet.getInt("doctor_id"));
                prescription.setDoctorName(resultSet.getString("doctor_name"));
                prescription.setPatientId(resultSet.getInt("patient_id"));
                prescription.setMedicineName(resultSet.getString("medicine_name"));
                prescription.setMorning(resultSet.getInt("morning_dose"));
                prescription.setAfternoon(resultSet.getInt("afternoon_dose"));
                prescription.setEvening(resultSet.getInt("evening_dose"));
                prescription.setPrescriptionDate(resultSet.getDate("prescription_date"));
                prescription.setBillingId(resultSet.getInt("billing_id"));
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return prescriptionList;
	}
}
