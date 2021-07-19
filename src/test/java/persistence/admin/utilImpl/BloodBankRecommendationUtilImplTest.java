package persistence.admin.utilImpl;

import org.junit.Test;
import static org.junit.Assert.*;
import presentation.startup.DatabaseConnection;

import java.util.List;

/**
* <pre>
* Perform tests for recommending blood group based on order history
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class BloodBankRecommendationUtilImplTest {

  /* Input Validations test cases start here */

  /* Input Validation 1: Start date > End date */
  @Test
  public void getBloodGroupList_IV1() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("2021-08-19", "2021-07-19", 8));

  }

  /* Input Validation 2: numRec is 0 */
  @Test
  public void getBloodGroupList_IV2() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", 0));

  }

}
