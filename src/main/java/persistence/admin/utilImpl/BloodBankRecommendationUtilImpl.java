package persistence.admin.utilImpl;

import persistence.admin.daoImpl.BloodBankRecommendationDAOImpl;
import persistence.admin.util.BloodBankRecommendationUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BloodBankRecommendationUtilImpl implements BloodBankRecommendationUtil {

    public boolean validateDate(String date) {
        String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getBloodGroupList(String startDate, String endDate, int numRec) {

        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);

        if(date2.isBefore(date1)) {
            System.err.println("End date cannot be before the start date");
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
        LinkedHashMap<String, Integer> bloodGroupList = new LinkedHashMap<>();
        try {
            bloodGroupList = bloodBankRecommendationDAOImpl.fetchBloodGroupList(startDate, endDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (bloodGroupList == null) {
            return null;
        } else if (bloodGroupList.isEmpty()) {
            return null;
        }

        LinkedHashMap<String, Integer> finalBloodGroupList = bloodGroupList;
        Map<Integer, List<String>> groupedList = bloodGroupList.keySet().stream().collect(Collectors.groupingBy(value -> finalBloodGroupList.get(value)));
        TreeMap<Integer, List<String>> sortedList = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
        sortedList.putAll(groupedList);

        int cnt = 0;

        List<String> bloodGroups = new ArrayList<String>();    /* arraylist of recommendations to be returned to the main function */
        Iterator<Map.Entry<Integer, List<String>>> im = sortedList.entrySet().iterator();
        do {
            Map.Entry<Integer, List<String>> etr = im.next();
            List<String> temp = etr.getValue();
            bloodGroups.addAll(temp);
            cnt++;
        } while (((bloodGroups.size()<numRec) && (cnt<numRec)) && im.hasNext());

        return bloodGroups;

    }

}
