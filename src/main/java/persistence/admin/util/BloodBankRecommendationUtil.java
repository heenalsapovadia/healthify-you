package persistence.admin.util;

import java.util.*;

/**
 * <pre>
 * Perform operations for recommending blood group based on order history
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 */

public interface BloodBankRecommendationUtil {

    public HashSet<ArrayList<String>> getBloodGroupList(String bloodGroup, int numRec);

}
