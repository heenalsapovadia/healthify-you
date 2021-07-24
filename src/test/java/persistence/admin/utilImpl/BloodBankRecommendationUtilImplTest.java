package persistence.admin.utilImpl;

import org.junit.Test;
import static org.junit.Assert.*;
import presentation.startup.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashSet;
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

  /* Test Case 1: bloodgroup not in the list */
  @Test
  public void getBloodGroupList_IV1() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("O", 5));
  }

  /* Test Case 2: numRec is 0 */
  @Test
  public void getBloodGroupList_IV2() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("A+", 0));
  }

  /* Test Case 3: numRec is -1 */
  @Test
  public void getBloodGroupList_IV3() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("A+", -1));
  }

  /* Test Case 4: numRec is anything other than 1, 2, 3, 4, 5, 6, 7, 8 */
  @Test
  public void getBloodGroupList_IV4() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertEquals(null, bloodBankRecommendationUtil.getBloodGroupList("A+", 9));
  }

  /* Control Flow test cases start here */

  /* Control Flow 1: numRec is a non-tying number at the boundary */
  @Test
  public void getBloodGroupList_CF1() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("O+");
    HashSet<List<String>> result = new HashSet<>();
    result.add(list);
    assertEquals(result, bloodBankRecommendationUtil.getBloodGroupList("A+", 1));
  }

  /* Control Flow 2: numRec is a tying number at the boundary */
  @Test
  public void getBloodGroupList_CF2() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("O+");
    List<String> list1 = new ArrayList();
    list1.add("AB+");
    list1.add("O+");
    List<String> list2 = new ArrayList();
    list2.add("AB+");
    list2.add("B+");
    HashSet<List<String>> result = new HashSet<>();
    result.add(list);
    result.add(list1);
    result.add(list2);
    assertEquals(result, bloodBankRecommendationUtil.getBloodGroupList("A+", 2));
  }

  /* Control Flow 3: numRec is a number containing tying elements */
  @Test
  public void getBloodGroupList_CF3() {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    List<String> list = new ArrayList();
    list.add("O+");
    List<String> list1 = new ArrayList();
    list1.add("AB+");
    list1.add("O+");
    List<String> list2 = new ArrayList();
    list2.add("AB+");
    list2.add("B+");
    HashSet<List<String>> result = new HashSet<>();
    result.add(list);
    result.add(list1);
    result.add(list2);
    assertEquals(result, bloodBankRecommendationUtil.getBloodGroupList("A+", 3));
  }

  @Test
  public void validateBloodGroup_1() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertFalse(bloodBankRecommendationUtil.validateBloodGroup("A"));
    assertFalse(bloodBankRecommendationUtil.validateBloodGroup("AB"));
    assertFalse(bloodBankRecommendationUtil.validateBloodGroup("B"));
    assertFalse(bloodBankRecommendationUtil.validateBloodGroup("O"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("A+"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("AB+"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("B+"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("O+"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("A-"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("AB-"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("B-"));
    assertTrue(bloodBankRecommendationUtil.validateBloodGroup("O-"));
  }

  @Test
  public void validateNumRec_1() {
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();
    assertFalse(bloodBankRecommendationUtil.validateNumRec(0));
    assertFalse(bloodBankRecommendationUtil.validateNumRec(-2));
    assertTrue(bloodBankRecommendationUtil.validateNumRec(2));
    assertFalse(bloodBankRecommendationUtil.validateNumRec(9));
  }

}
