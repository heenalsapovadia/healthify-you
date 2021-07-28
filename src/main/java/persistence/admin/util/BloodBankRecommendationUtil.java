package persistence.admin.util;

import java.util.*;

/**
* <pre>
* Perform operations for recommending blood group based on frequently ordered blood groups
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface BloodBankRecommendationUtil {

  /**
   * @param bloodGroup,numRec
   * @return HashSet<ArrayList<String>>
   */
  public HashSet<ArrayList<String>> getBloodGroupList(String bloodGroup, int numRec);

}
