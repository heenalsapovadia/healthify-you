package persistence.admin.daoImpl;

import persistence.admin.dao.BloodBankRecommendationDAO;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
* <pre>
* DAOImpl class for blood bank recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class BloodBankRecommendationDAOImpl implements BloodBankRecommendationDAO {

  /**
   * <pre>
   * Inner class for Order
   * </pre>
   *
   * @author Samiksha Salgaonkar
   *
   */

  /* This is an inner class which groups orders based on their order numbers and blood groups ordered in that order */
  public class Order {
    public int orderNumber;    /* stores the order number */
    public String bloodGroup;    /* stores the blood group */

    /* parameterized constructor for the Order class */
    Order(int orderNumber, String bloodGroup) {
      this.orderNumber = orderNumber;   /* assigns the passed order number to the instance variable */
      this.bloodGroup = bloodGroup;    /* assigns the passed blood group to the instance variable */
    }
  }

  /* This method is passed the primary blood group entered by the patient, based on which it fetches
     the list of orders and the blood groups ordered for each of those orders */
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
      /* retrieves order numbers for the entered primary blood group */
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

      List<Order> orders = new ArrayList<Order>();    /* list to store Order instances with their order numbers and blood groups ordered in those orders */
      if(!resultSet.next()) {
        System.err.println("Nothing was ordered for the mentioned blood group!");
        return null;
      } else {
          do {
            Order o = new Order(resultSet.getInt(DatabaseConstants.ORDER_NUMBER), resultSet.getString(DatabaseConstants.BLOOD_GROUP));
            orders.add(o);
          } while(resultSet.next());
      }

      return orders;    /* returns the list of instances of class Order storing the order number and blood group */

    } catch (SQLException se) {
        return null;
    }
  }

}
