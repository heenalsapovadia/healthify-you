package persistence.admin.utilImpl;

import org.junit.Test;
import static org.junit.Assert.*;
import presentation.startup.DatabaseConnection;

import java.util.ArrayList;
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

  /* Input Validation 3: numRec is any negative integer */
  @Test
  public void getBloodGroupList_IV3() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", -1));

  }

  /* Input Validation 4: numRec is greater than 8 */
  @Test
  public void getBloodGroupList_IV4() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", 9));

  }

  /* Control Flow test cases start here */

  /* Control Flow 1: numRec is a non-tying number at the boundary */
  @Test
  public void getBloodGroupList_CF1() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("A+");
    list.add("AB+");
    list.add("O+");
    list.add("B+");

    assertEquals(list, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", 4));

  }

  /* Control Flow 2: numRec is a tying number at the boundary */
  @Test
  public void getBloodGroupList_CF2() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("A+");
    list.add("AB+");

    assertEquals(list, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", 1));

  }

  /* Control Flow 3: numRec is a number containing tying elements */
  @Test
  public void getBloodGroupList_CF3() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("A+");
    list.add("AB+");

    assertEquals(list, bloodBankRecommendationUtil.getBloodGroupList("2021-01-19", "2021-07-19", 2));

  }

}
