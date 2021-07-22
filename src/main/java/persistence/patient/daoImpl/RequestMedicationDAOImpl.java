package persistence.patient.daoImpl;

import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMedicationDAOImpl {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

     public ArrayList<String> fetchPrescriptionName(int prescription_id) throws SQLException {
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rS = null;

            String sql = "select presciption_id from prescription where prescription_id = \"" +prescription_id+ "\"" + ";";

            try {
                /* retrieves doctor list for the symptoms */
                rS = statement.executeQuery(sql);

                ArrayList<String> prescriptionNameList = new ArrayList<>();
                while (rS.next()) {
                   // prescriptionNameList.add(rS.getInt("prescription_id"));
                }
                return prescriptionNameList;

            } catch (SQLException se) {
                return null;
            }

        }
}
