package persistence.patient.utilImpl;

import persistence.patient.util.DoctorAppointmentBookingByNameUtil;
import presentation.common.CommonErrors;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <pre>
* Class for handling utility methods of doctor appointment booking dashboard
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class DoctorAppointmentBookingByNameUtilImpl implements DoctorAppointmentBookingByNameUtil {

    public boolean validateID(int doctorID) throws SQLException {

        if (doctorID == 0) {
            return false;
        } else {
            String sql = "select distinct doctor_id from doctors;";

            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rS = null;

            try {
                /* retrieves doctor_id(s) list from the database */
                rS = statement.executeQuery(sql);

                Set<Integer> doctorIDSet = new HashSet<>();
                if (!rS.next()) {
                    return false;
                } else {
                    doctorIDSet.add(rS.getInt("doctor_id"));
                    return true;
                }
            } catch (SQLException se) {
                return false;
            }
        }
    }


    public boolean validateName(String name) {
        if (name != null) {
            if (name.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean validateDate(String date, List<String> datesAvailable) {
        String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (date != null) {
            if (!date.isEmpty()) {
                if (matcher.matches()) {
                    if (datesAvailable.contains(date)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public int validateEmail(String email) throws SQLException {
        String emailregex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailregex);
        Matcher matcher = pattern.matcher(email);
        int identifier;

        if (matcher.matches() == false || email == null || email == "") {
            return -1;
        } else {
            String sql = "select patient_id from patients where patient_email = ";

            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rS = null;

            try {
                /* retrieves doctor_id(s) list from the database */
                rS = statement.executeQuery(sql + "\"" + email + "\"");

                if (!rS.next()) {
                    return -1;
                } else {
                    do {
                        identifier = rS.getInt("patient_id");
                    } while (rS.next());
                }
            } catch (SQLException se) {
                return -1;
            }
            return identifier;
        }
    }

}
