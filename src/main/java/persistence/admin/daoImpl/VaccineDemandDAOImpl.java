package persistence.admin.daoImpl;

import persistence.admin.dao.VaccineDemandDAO;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Heenal Sapovadia
 * This class is responsible for fetching the Vaccination data for statistics
 */
public class VaccineDemandDAOImpl implements VaccineDemandDAO {
    private static final Logger LOGGER = Logger.getLogger(VaccineDemandDAOImpl.class.getName());

    @Override
    public List<Map<String, Object>> getVaccinationData(){
        Connection connection = DatabaseConnection.instance();
        List<Map<String, Object>> dataRecords = new ArrayList<>();

        String sql = "SELECT appointment_id, Vaccine.patient_id, doctor_id, booked_for_date,\n" +
                "vaccine_id, vaccine_name, age_group_in_years, patient_gender, patient_dob, patient_address\n" +
                "FROM\n" +
                "(SELECT appointment_id, patient_id, doctor_id, booked_for_date,\n" +
                "Vacc.vaccine_id, vaccine_name, age_group_in_years\n" +
                "FROM CSCI5308_12_DEVINT.immunization_appointments as ImmA \n" +
                "join CSCI5308_12_DEVINT.vaccination_stock as Vacc\n" +
                "where ImmA.vaccine_id = Vacc.vaccine_id) AS Vaccine\n" +
                "join CSCI5308_12_DEVINT.patients as Pat \n" +
                "where Vaccine.patient_id = Pat.patient_id;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Map<String, Object> dataRecord = new HashMap<>();
                dataRecord.put("appointmentId", resultSet.getInt(DatabaseConstants.APPOINTMENT_ID));
                dataRecord.put("patientId", resultSet.getInt(DatabaseConstants.PATIENT_ID));
                dataRecord.put("doctorId", resultSet.getInt(DatabaseConstants.DOCTOR_ID));
                dataRecord.put("date", resultSet.getDate(DatabaseConstants.BOOKED_FOR_DATE));
                dataRecord.put("vaccineId", resultSet.getInt(DatabaseConstants.VACCINE_ID));
                dataRecord.put("gender", resultSet.getString(DatabaseConstants.PATIENT_GENDER));
                dataRecord.put("dob", resultSet.getDate(DatabaseConstants.PATIENT_DOB));
                dataRecord.put("area", resultSet.getString(DatabaseConstants.PATIENT_ADDRESS));
                dataRecord.put("vaccineName", resultSet.getString(DatabaseConstants.VACCINE_NAME));
                dataRecord.put("ageGroup", resultSet.getString(DatabaseConstants.AGE_GROUP_IN_YEARS));

                dataRecords.add(dataRecord);
            }
            return dataRecords;
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return dataRecords;
    }
}
