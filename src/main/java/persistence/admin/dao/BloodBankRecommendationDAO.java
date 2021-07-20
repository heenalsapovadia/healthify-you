package persistence.admin.dao;

import persistence.admin.daoImpl.BloodBankRecommendationDAOImpl;

import java.sql.SQLException;
import java.util.List;

/**
* <pre>
* DAO interface for blood bank recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/

public interface BloodBankRecommendationDAO {

  public List<BloodBankRecommendationDAOImpl.Order> fetchBloodGroupList(String bloodGroup) throws SQLException;

}
