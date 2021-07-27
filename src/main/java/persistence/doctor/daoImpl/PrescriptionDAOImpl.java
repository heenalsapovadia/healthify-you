package persistence.doctor.daoImpl;

import persistence.common.DatabaseConstants;
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

/**
 * @author Heenal Sapovadia
 * This class is responsible for managing Doctor Prescriptions
 */
public class PrescriptionDAOImpl implements PrescriptionDAO {

    private static final Logger LOGGER = Logger.getLogger(PrescriptionDAOImpl.class.getName());

    @Override
    public void insertPrescription(List<Prescription> prescriptionList){
        Connection connection = DatabaseConnection.instance();
        PrescriptionDAOImpl prescriptionDAO = new PrescriptionDAOImpl();
        int prescriptionId = prescriptionDAO.findMaxPrescriptionId() + 1;

        for(Prescription prescription : prescriptionList){
            String sql = "INSERT INTO prescription(prescription_id, appointment_id, patient_id, doctor_id, doctor_name, " +
                    "medicine_name, morning_dose,  afternoon_dose, evening_dose, dosage_days, prescription_date) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
                preparedStatement.setInt(10, prescription.getDosageDays());
                preparedStatement.setDate(11, prescription.getDate());

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
        Connection connection = DatabaseConnection.instance();

        String sql = "SELECT MAX(prescription_id) AS prescription_id FROM prescription";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(DatabaseConstants.PRESCRIPTION_ID);
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
        Connection connection = DatabaseConnection.instance();

        String sql = "SELECT * FROM prescription WHERE prescription_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, prescriptionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(prescriptionId);
                setPrescriptionObject(resultSet, prescription);
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return prescriptionList;
    }
    
    private void setPrescriptionObject(ResultSet resultSet, Prescription prescription) throws SQLException {
    	prescription.setAppointmentId(resultSet.getInt(DatabaseConstants.APPOINTMENT_ID));
        prescription.setDoctorId(resultSet.getInt(DatabaseConstants.DOCTOR_ID));
        prescription.setDoctorName(resultSet.getString(DatabaseConstants.DOCTOR_NAME));
        prescription.setPatientId(resultSet.getInt(DatabaseConstants.PATIENT_ID));
        prescription.setMedicineName(resultSet.getString(DatabaseConstants.MEDICINE_NAME));
        prescription.setMorning(resultSet.getInt(DatabaseConstants.MORNING_DOSE));
        prescription.setAfternoon(resultSet.getInt(DatabaseConstants.AFTERNOON_DOSE));
        prescription.setEvening(resultSet.getInt(DatabaseConstants.EVENING_DOSE));
        prescription.setDosageDays(resultSet.getInt(DatabaseConstants.DOSAGE_DAYS));
        prescription.setDate(resultSet.getDate(DatabaseConstants.PRESCRIPTION_DATE));
        prescription.setBillingId(resultSet.getInt(DatabaseConstants.BILLING_ID));
        prescription.setDosageDays(resultSet.getInt(DatabaseConstants.DOSAGE_DAYS));
    }

	@Override
	public List<Prescription> getPrescriptionByPatientId() {
		List<Prescription> prescriptionList = new ArrayList<>();
        Connection connection = DatabaseConnection.instance();
        String sqlStatement = "SELECT * FROM prescription WHERE patient_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setInt(1, Patient.instance().getPatientId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                setPrescriptionObject(resultSet, prescription);
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
