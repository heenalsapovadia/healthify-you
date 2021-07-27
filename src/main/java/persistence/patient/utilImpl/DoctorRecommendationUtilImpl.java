package persistence.patient.utilImpl;

import persistence.patient.daoImpl.DoctorRecommendationDAOImpl;
import persistence.patient.util.DoctorRecommendationUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* <pre>
* Perform operations for recommending doctor based on symptoms
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRecommendationUtilImpl implements DoctorRecommendationUtil {

  public ArrayList<String> getDoctorRecommendations(String symptom, int support, int numRec) {

    if(numRec <= 0) {
      System.err.println("Enter number of recommendation greater than 0");
      return null;
    }

    if(support<0) {
      System.err.println("Support cannot be negative! Enter support 0 or greater than 0");
      return null;
    }

    ArrayList<String> symptomList = new ArrayList<>();
    symptomList.add("Cough");
    symptomList.add("Cold");
    symptomList.add("Fever");
    symptomList.add("Nausea");
    symptomList.add("Digestive issues");

    if(!symptomList.contains(symptom)) {
      System.err.println("Symptom not in the list of given options!");
      return null;
    }

    DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

    ArrayList<Integer> doctorIDList = new ArrayList();

    doctorIDList = doctorRecommendationDAOImpl.fetchDoctorList(symptom);

    if(support <= doctorIDList.size()) {
      TreeMap<Integer, Integer> rec = new TreeMap<Integer, Integer>();    /* map to store key and value pairs of doctor_id and their frequencies respectively */
      Map<Integer, List<Integer>> fmap = new TreeMap<Integer, List<Integer>>();    /* map that groups doctor_id(s) of same frequencies with frequency as key and list of doctor_id(s) as value */
      ArrayList<Integer> recf = new ArrayList<Integer>();    /* arraylist of recommendations to be returned to the main function */
      int cnt = 0;    /* counter used while adding numRec to rect list */

      /* sorts a by keys(frequency) and then adds to a TreeMap, TreeMap is used as it maintains natural order and its easy to sort a TreeMap with keys */
      /* Collections.reverseOrder() is used to sort the map in descending order */
      TreeMap<Integer, List<Integer>> mf = new TreeMap<>(Collections.reverseOrder());

      /* created hashSet of unique doctor_id(s) for calculation frequencies of doctor_id(s) */
      /* hashSet consists of all doctor_id(s) of the hospital */
      Set<Integer> st = new HashSet<Integer>(doctorIDList);

      /* counting frequency of doctor_id who have attended patients with that symptoms */
      /* Collections.frequency() method uses collection as a parameter and the object whose frequency is to be calculated from that collection */
      /* Iterating through every course of hashSet st and passing it as a parameter to the frequency method along with doctorIDList */
      /* add the doctor_id and its frequency to a map of doctor_id as key and frequency as value */
      for (Integer b : st) {
        rec.put(b, Collections.frequency(doctorIDList, b));
      }

      int f = rec.size();

      /* fmap is a TreeMap created by grouping courses of similar frequencies and storing the list of doctor_id(s) as keys and their frequencies as values */
      /* Collectors.groupingBy() method groups key, of specified map, having same value */
      fmap = rec.keySet().stream().collect(Collectors.groupingBy(value -> rec.get(value)));

      /* entries of fmap are put into mf which is a TreeMap of reverseOrder. This will sort the keys in descending order */
      mf.putAll(fmap);

      /* Iterator to iterate through the entries of mf TreeMap */
      Iterator<Map.Entry<Integer, List<Integer>>> im = mf.entrySet().iterator();

      /* validates if there are enough key-value pairs for recommendation */
      /* size of the map before grouping values is used for validation to avoid any discrepancies */
      if (f >= numRec) {

        /* do-while loop will perform the following tasks:
            1. iterate through the reversely sorted grouped map mf,
            2. store every value(list of courses of same frequency) in a list of string and then add the list to arraylist to be returned.
            3. it will increment the counter (cnt) everytime a course or group of courses of same frequencies are added to the recf arraylist,
            4. the loop will run until the cnt equals or exceeds numRec or the size of rec exceeds numRec and mf has more entries,
            5. do while loop ensures that the courses with same frequencies at the boundary are added and returned
        */
        do {
            Map.Entry<Integer, List<Integer>> etr = im.next();
            List<Integer> yut = etr.getValue();
            recf.addAll(yut);
            cnt++;
        } while (((recf.size()<numRec) && (cnt<numRec)) && im.hasNext());

        ArrayList<String> doctorList = new ArrayList(recf);
        doctorList = doctorRecommendationDAOImpl.getDoctorName(recf);

        return doctorList;
      } else {
          System.err.println("Not enough recommendations available!");
          return null;
      }

    } else {
        System.err.println("Not enough support to recommend!");
        return null;
    }
  }

}
