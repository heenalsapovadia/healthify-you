package persistence.admin.daoImpl;

import org.junit.Test;
import presentation.startup.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
* <pre>
* Perform tests for fetching blood group list based on order history from database
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class BloodBankrecommendationDAOImplTest {

  @Test
  public void fetchBloodGroupList() throws SQLException {
    BloodBankRecommendationDAOImpl bloodBankRecommendationDAOImpl = new BloodBankRecommendationDAOImpl();
    DatabaseConnection.loadDatabaseConnection();

    List<BloodBankRecommendationDAOImpl.Order> list = new ArrayList<BloodBankRecommendationDAOImpl.Order>();

    list.add(bloodBankRecommendationDAOImpl.new Order(1, "A+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(1, "O+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(2, "AB+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(2, "A+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(2, "B+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "AB+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "A+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "O+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(4, "A+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(4, "O+"));

    List<BloodBankRecommendationDAOImpl.Order> orders = bloodBankRecommendationDAOImpl.fetchBloodGroupList("A+");
    int size = orders.size();
    int count = 0;

    for(BloodBankRecommendationDAOImpl.Order o : list) {
      for(BloodBankRecommendationDAOImpl.Order o1 : orders) {
        if(o.orderNumber == o1.orderNumber) {
          if(o1.bloodGroup != null) {
            if (o.bloodGroup.equals(o1.bloodGroup)) {
              count++;
            }
          }
        }
      }
    }

    assertEquals(count, size);

  }

  @Test
  public void fetchBloodGroupList2() throws SQLException {
    BloodBankRecommendationDAOImpl bloodBankRecommendationDAOImpl = new BloodBankRecommendationDAOImpl();
    DatabaseConnection.loadDatabaseConnection();

    assertEquals(null, bloodBankRecommendationDAOImpl.fetchBloodGroupList("C+"));

  }

  @Test
  public void fetchBloodGroupList3() throws SQLException {
    BloodBankRecommendationDAOImpl bloodBankRecommendationDAOImpl = new BloodBankRecommendationDAOImpl();
    DatabaseConnection.loadDatabaseConnection();

    List<BloodBankRecommendationDAOImpl.Order> list = new ArrayList<BloodBankRecommendationDAOImpl.Order>();

    list.add(bloodBankRecommendationDAOImpl.new Order(2, "AB+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(2, "A+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(2, "B+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "AB+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "O+"));
    list.add(bloodBankRecommendationDAOImpl.new Order(3, "A+"));

    List<BloodBankRecommendationDAOImpl.Order> orders = bloodBankRecommendationDAOImpl.fetchBloodGroupList("AB+");

    int size = orders.size();
    int count = 0;

    for(BloodBankRecommendationDAOImpl.Order o : list) {
      for(BloodBankRecommendationDAOImpl.Order o1 : orders) {
        if(o.orderNumber == o1.orderNumber) {
          if(o1.bloodGroup != null) {
            if (o.bloodGroup.equals(o1.bloodGroup)) {
              count++;
            }
          }
        }
      }
    }

    assertEquals(count, size);

  }

}
