package persistence.admin.daoImpl;

import persistence.admin.dao.VaccineDemandDAO;
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

public class VaccineDemandDAOImpl implements VaccineDemandDAO {
    private static final Logger LOGGER = Logger.getLogger(VaccineDemandDAOImpl.class.getName());

    @Override
    public List<Map<String, Object>> getVaccinationData(){
        Connection conn = DatabaseConnection.getConnection();
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

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> dataRecord = new HashMap<>();
                dataRecord.put("appointmentId", rs.getInt("appointment_id"));
                dataRecord.put("patientId", rs.getInt("patient_id"));
                dataRecord.put("doctorId", rs.getInt("doctor_id"));
                dataRecord.put("date", rs.getDate("booked_for_date"));
                dataRecord.put("vaccineId", rs.getInt("vaccine_id"));
                dataRecord.put("gender", rs.getString("patient_gender"));
                dataRecord.put("dob", rs.getDate("patient_dob"));
                dataRecord.put("area", rs.getString("patient_address"));
                dataRecord.put("vaccineName", rs.getString("vaccine_name"));
                dataRecord.put("ageGroup", rs.getString("age_group_in_years"));

                dataRecords.add(dataRecord);
            }
            return dataRecords;
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return dataRecords;
    }
}
