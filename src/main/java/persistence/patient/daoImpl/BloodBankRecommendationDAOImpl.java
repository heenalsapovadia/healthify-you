package persistence.patient.daoImpl;

import persistence.admin.dao.BloodBankRecommendationDAO;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BloodBankRecommendationDAOImpl implements BloodBankRecommendationDAO {

  @Override
  public ArrayList<String> fetchBloodGroupList(String startDate, String endDate) throws SQLException {

    Connection conn = DatabaseConnection.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rS = null;

    String sql = "select sum(quantity) as sum_quantity, blood_group from blood_bank_orders where order_date between \"" + startDate + "\" and \"" + endDate + "\" group by blood_group order by sum_quantity desc, blood_group;";

    try {
      /* retrieves blood group list for the symptoms */
      rS = statement.executeQuery(sql);

      ArrayList<String> bloodGroupList = new ArrayList<>();
      while (rS.next()) {
        bloodGroupList.add(rS.getString("blood_group"));
      }

      if(bloodGroupList.isEmpty()) {
        System.out.println("Nothing to recommend!");
        return bloodGroupList;
      } else {
        return bloodGroupList;
      }

    } catch (SQLException se) {
      return null;
    }

  }

}
