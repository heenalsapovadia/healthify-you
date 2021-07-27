package persistence.admin.daoImpl;

import persistence.admin.dao.BloodBankRecommendationDAO;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static persistence.common.DatabaseConstants.ORDER_NUMBER;

/**
* <pre>
* DAOImpl class for blood bank recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class BloodBankRecommendationDAOImpl implements BloodBankRecommendationDAO {

  public class Order {
    public int orderNumber;
    public String bloodGroup;

    Order(int orderNumber, String bloodGroup) {
      this.orderNumber = orderNumber;
      this.bloodGroup = bloodGroup;
    }
  }

  @Override
  public List<Order> fetchBloodGroupList(String bloodGroup) {

    Connection connection = DatabaseConnection.instance();
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }
    ResultSet resultSet = null;

    String sql = "select * from blood_bank_orders where blood_group = \"" + bloodGroup + "\";";

    try {
      /* retrieves blood group list for the symptoms */
      resultSet = statement.executeQuery(sql);
      Set<Integer> orderNumberSet = new HashSet<>();

      if(!resultSet.next()) {
        System.err.println("Nothing was ordered for the mentioned blood group!");
        return null;
      } else {
          do {
            orderNumberSet.add(resultSet.getInt(DatabaseConstants.ORDER_NUMBER));
          } while (resultSet.next());
      }

      String inClause = "";
      int sizeCheck = 0;
      for(Integer i : orderNumberSet) {
        if(sizeCheck < (orderNumberSet.size()-1)) {
        inClause = inClause + i + " ,";
        sizeCheck++;
        } else {
            inClause = inClause + i;
        }
      }

      String sql2 = "select * from blood_bank_orders where order_number in (" + inClause+ ");";

      resultSet = statement.executeQuery(sql2);

      List<Order> orders = new ArrayList<Order>();
      if(!resultSet.next()) {
        System.err.println("Nothing was ordered for the mentioned blood group!");
        return null;
      } else {
          do {
            Order o = new Order(resultSet.getInt(DatabaseConstants.ORDER_NUMBER), resultSet.getString(DatabaseConstants.BLOOD_GROUP));
            orders.add(o);
          } while(resultSet.next());
      }

      return orders;

    } catch (SQLException se) {
        return null;
    }
  }

}
