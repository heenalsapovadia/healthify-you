package persistence.admin.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public interface BloodBankRecommendationDAO {

  public LinkedHashMap<String, Integer> fetchBloodGroupList(String startDate, String endDate) throws SQLException;

}
