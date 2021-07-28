package persistence.patient.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
* <pre>
* Perform operations for fetching doctor based on symptoms from the database
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface DoctorRecommendationDAO {

  /**
  * @param symptom
  * @return ArrayList<Integer>
  */
  public ArrayList<Integer> fetchDoctorList(String symptom) throws SQLException;

  /**
  * @param recf
  * @return ArrayList<String>
  */
  public ArrayList<String> getDoctorName(ArrayList<Integer> recf);

}
