package persistence.admin.utilImpl;

import persistence.admin.daoImpl.BloodBankRecommendationDAOImpl;
import persistence.admin.util.BloodBankRecommendationUtil;
import java.util.*;
import java.util.stream.Collectors;

/**
* <pre>
* Util class for blood bank recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public class BloodBankRecommendationUtilImpl implements BloodBankRecommendationUtil {

  public HashSet<ArrayList<String>> getBloodGroupList(String bloodGroup, int numRec) {

    bloodGroup = bloodGroup.toUpperCase(Locale.ROOT);
    ArrayList<String> bloodGroupOptions = new ArrayList<>();
    bloodGroupOptions.add("A+");
    bloodGroupOptions.add("AB+");
    bloodGroupOptions.add("O+");
    bloodGroupOptions.add("B+");
    bloodGroupOptions.add("A-");
    bloodGroupOptions.add("AB-");
    bloodGroupOptions.add("B-");
    bloodGroupOptions.add("O-");

    if(!bloodGroupOptions.contains(bloodGroup)) {
      return null;
    }

    if(numRec <= 0 ) {
      System.err.println("Enter number of recommendation greater than 0!");
      return null;
    }

    if(numRec != 1 && numRec != 2 && numRec != 3 && numRec != 4 && numRec != 5 && numRec != 6 && numRec !=7 && numRec != 8 ) {
      System.err.println("Enter number of recommendation between 1 to 8!");
      return null;
    }

    BloodBankRecommendationDAOImpl bloodBankRecommendationDAOImpl = new BloodBankRecommendationDAOImpl();
    List<BloodBankRecommendationDAOImpl.Order> orders = new ArrayList<>();
    orders = bloodBankRecommendationDAOImpl.fetchBloodGroupList(bloodGroup);

    if (orders == null) {
      return null;
    } else if (orders.isEmpty()) {
        return null;
    }

    Set<Integer> orderNumberSet = new HashSet<>();
    for(BloodBankRecommendationDAOImpl.Order o : orders) {
      orderNumberSet.add(o.orderNumber);
    }

    ArrayList<ArrayList<String>> bloodGroupList = new ArrayList<>();
    for(Integer i : orderNumberSet) {
      int orderNumberCheck = i;
      ArrayList<String> groupList = new ArrayList<>();
      for(BloodBankRecommendationDAOImpl.Order o : orders) {
        if(o.orderNumber == orderNumberCheck) {
          groupList.add(o.bloodGroup);
        }
      }

      bloodGroupList.add(groupList);

    }

    TreeMap<ArrayList<String>, Integer> rec = new TreeMap<>();    /* map to store key and value pairs of list of blood groups ordered together and their frequencies respectively */
    TreeMap<Integer, ArrayList<String>> groupMap = new TreeMap<>();
    TreeMap<Integer, Integer> groupFrequency = new TreeMap<>();
    Map<Integer, Integer> groupSize = new TreeMap<>();
    int counter = 0;
    for(ArrayList<String> temp : bloodGroupList) {
      if(groupMap.containsValue(temp)) {
        continue;
      }

      int frequency = Collections.frequency(bloodGroupList, temp);
      counter++;
      groupMap.put(counter, temp);
      groupSize.put(counter, temp.size());
      groupFrequency.put(counter, frequency);

    }

    Map<Integer, List<Integer>> frequencyMap = new TreeMap<>();    /* map that groups blood group lists (s) of same frequencies with frequency as key and lists of blood group(s) as value */
    frequencyMap = groupFrequency.keySet().stream().collect(Collectors.groupingBy(value -> groupFrequency.get(value)));

    TreeMap<Integer, List<Integer>> sortedFrequencyMap = new TreeMap<>(Collections.reverseOrder());
    sortedFrequencyMap.putAll(frequencyMap);

    int recCount = 0;
    int bloodGroupSize = 0;

    List<Integer> bloodGroups = new ArrayList<>();    /* arraylist of recommendations to be returned to the main function */
    Iterator<Map.Entry<Integer, List<Integer>>> im = sortedFrequencyMap.entrySet().iterator();
    do {
      Map.Entry<Integer, List<Integer>> etr = im.next();
      List<Integer> temp = etr.getValue();
      bloodGroups.addAll(temp);
      recCount++;
      for(Integer i : temp) {
        bloodGroupSize = bloodGroupSize + groupSize.get(i) - 1;
      }
    } while (((bloodGroupSize<numRec) && (recCount<numRec)) && im.hasNext());

    ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
    for(Integer k : bloodGroups) {
      if(groupMap.containsKey(k)) {
        resultSet.add(groupMap.get(k));
      } else {
      }
    }

    HashSet<ArrayList<String>> result = new HashSet<>();
    if(resultSet.isEmpty()) {
      return null;
    } else {
        for(ArrayList<String> list : resultSet) {
          list.remove(bloodGroup);
          result.add(list);
        }
        return result;
    }

  }

  public boolean validateBloodGroup(String bloodGroup) {
    bloodGroup = bloodGroup.toUpperCase(Locale.ROOT);
    ArrayList<String> bloodGroupOptions = new ArrayList<>();
    bloodGroupOptions.add("A+");
    bloodGroupOptions.add("AB+");
    bloodGroupOptions.add("O+");
    bloodGroupOptions.add("B+");
    bloodGroupOptions.add("A-");
    bloodGroupOptions.add("AB-");
    bloodGroupOptions.add("B-");
    bloodGroupOptions.add("O-");

    return bloodGroupOptions.contains(bloodGroup);
  }

  public boolean validateNumRec(int numRec) {
    if(numRec <= 0 ) {
      System.err.println("Enter number of recommendation greater than 0!");
      return false;
    }

    if(numRec != 1 && numRec != 2 && numRec != 3 && numRec != 4 && numRec != 5 && numRec != 6 && numRec !=7 && numRec != 8 ) {
      System.err.println("Enter number of recommendation between 1 to 8!");
      return false;
    }

    return true;
  }

}
