/**
 * 
 */
package persistence.patient.util;

import java.util.List;
import java.util.Map;

/**
 * @author Gurleen Saluja
 *
 */
public interface ViewReportsUtil {
	
	/**
	 * <pre>
	 * Fetches all reports from JSON by date.
	 * 
	 * Returns a map by screen title to be printed to console.
	 * </pre>
	 * 
	 * @param date
	 * @return Map
	 */
	Map<String, List<String>> fetchReportByDate(String date);
	
	/**
	 * <pre>
	 * Fetches all reports from JSON by date range.
	 * 
	 * Returns a map by screen title to be printed to console.
	 * </pre>
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return Map
	 */
	Map<String, List<String>> fetchReportByDateRange(String fromDate, String toDate);
	
	/**
	 * <pre>
	 * Fetches blood test reports from JSON.
	 * </pre>
	 * 
	 * @return string
	 */
	String getBloodReportByTest();
	
	/**
	 * <pre>
	 * Fetches kidney test reports from JSON.
	 * 
	 * Returns a string of data to be printed to console 
	 * by specific test report class.
	 * </pre>
	 * 
	 * @return string
	 */
	String getKidneyReportByTest();
	
	/**
	 * <pre>
	 * Fetches liver test reports from JSON.
	 * 
	 * Returns a string of data to be printed to console 
	 * by specific test report class.
	 * </pre>
	 * 
	 * @return
	 */
	String getLiverReportByTest();
	
	/**
	 * <pre>
	 * Fetches eye test reports from JSON.
	 * 
	 * Returns a string of data to be printed to console 
	 * by specific test report class.
	 * </pre>
	 * 
	 * @return string
	 */
	String getVisionReportByTest();
	
	/**
	 * <pre>
	 * Fetches covid test reports from JSON.
	 * 
	 * Returns a string of data to be printed to console 
	 * by specific test report class.
	 * </pre>
	 * 
	 * @return string
	 */
	String getCovidReportByTest();
}
