package persistence.admin.daoImpl;

import persistence.admin.dao.BloodBankRecommendationDAO;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    public List<Order> fetchBloodGroupList(String bloodGroup) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rS = null;

        String sql = "select * from blood_bank_orders where blood_group = \"" + bloodGroup + "\";";

        try {
            /* retrieves blood group list for the symptoms */
            rS = statement.executeQuery(sql);
            Set<Integer> orderNumberSet = new HashSet<>();

            if(!rS.next()) {
                System.err.println("Nothing was ordered for the mentioned blood group!");
                return null;
            } else {
                do {
                  orderNumberSet.add(rS.getInt("order_number"));
                } while (rS.next());
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

            rS = statement.executeQuery(sql2);

            List<Order> orders = new ArrayList<Order>();
            if(!rS.next()) {
              System.err.println("Nothing was ordered for the mentioned blood group!");
              return null;
            } else {
                do {
                    Order o = new Order(rS.getInt("order_number"), rS.getString("blood_group"));
                    orders.add(o);
                } while(rS.next());
            }

            return orders;

        } catch (SQLException se) {
            return null;
        }

    }

}
