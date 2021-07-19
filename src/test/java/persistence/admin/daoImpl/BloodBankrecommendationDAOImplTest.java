package persistence.admin.daoImpl;

import org.junit.Test;
import presentation.startup.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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

    ArrayList<String> bloodGroupList = new ArrayList<>();
    bloodGroupList.add("A+");
    bloodGroupList.add("AB+");
    bloodGroupList.add("O+");
    bloodGroupList.add("B+");

    assertEquals(bloodGroupList, bloodBankRecommendationDAOImpl.fetchBloodGroupList("2021-01-19", "2021-07-19"));

  }

  @Test
  public void fetchBloodGroupList2() throws SQLException {
    BloodBankRecommendationDAOImpl bloodBankRecommendationDAOImpl = new BloodBankRecommendationDAOImpl();
    DatabaseConnection.loadDatabaseConnection();

    ArrayList<String> bloodGroupList = new ArrayList<>();

    assertEquals(bloodGroupList, bloodBankRecommendationDAOImpl.fetchBloodGroupList("2020-01-19", "2020-07-19"));

  }

}
