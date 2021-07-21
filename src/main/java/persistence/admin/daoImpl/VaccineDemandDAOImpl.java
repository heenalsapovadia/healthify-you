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
        List<Map<String, Object>> records = new ArrayList<>();

        String sql = "SELECT appointment_id, Vaccine.patient_id, doctor_id, booked_for_date,\n" +
                "vaccine_id, vaccine_name, administered_age_group, patient_gender, patient_dob, patient_address\n" +
                "FROM\n" +
                "(SELECT appointment_id, patient_id, doctor_id, booked_for_date,\n" +
                "vaccine_id, vaccine_name, administered_age_group\n" +
                "FROM CSCI5308_12_DEVINT.immunization_appointments as ImmA \n" +
                "join CSCI5308_12_DEVINT.vaccination_stock as Vacc\n" +
                "where ImmA.vaccine_id = Vacc.vaccine_ref) AS Vaccine\n" +
                "join CSCI5308_12_DEVINT.patients as Pat \n" +
                "where Vaccine.patient_id = Pat.patient_id;";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> record = new HashMap<>();
                record.put("appointmentId", rs.getInt("appointment_id"));
                record.put("patientId", rs.getInt("patient_id"));
                record.put("doctorId", rs.getInt("doctor_id"));
                record.put("date", rs.getDate("booked_for_date"));
                record.put("vaccineId", rs.getInt("vaccine_id"));
                record.put("gender", rs.getString("patient_gender"));
                record.put("dob", rs.getDate("patient_dob"));
                record.put("area", rs.getString("patient_address"));
                record.put("vaccineName", rs.getString("vaccine_name"));
                record.put("ageGroup", rs.getString("administered_age_group"));

                records.add(record);
            }
            return records;
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }
}
