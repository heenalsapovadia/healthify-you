package persistence.admin.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BloodBankRecommendationDAO {

  public ArrayList<String> fetchBloodGroupList(String startDate, String endDate) throws SQLException;

}
