package persistence.patient.utilImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import persistence.patient.model.Patient;
import persistence.patient.util.ViewReportsUtil;
import presentation.common.ScreenTitles;
import presentation.startup.DatabaseConnection;

class ViewReportsUtilImplTest {

	private ViewReportsUtil viewReportsUtil; 
			
	@Test
	void testFetchReportByDate() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		Map<String, List<String>> actualReportsMap = viewReportsUtil.fetchReportByDate("2021-04-20");
		Map<String, List<String>> expectedReportsMap = new HashMap<>();
		List<String> bloodValuesList = new ArrayList<>();
		String report = "Date of Collection		:		2021-04-19\n"
				+ "Date				:		2021-04-20\n"
				+ "CBC:\n"
				+ "	RBC			:		4.32\n"
				+ "	Hematocrit		:		38.8\n"
				+ "	Haemoglobin		:		13.5\n"
				+ "	WBC			:		3500\n"
				+ "	Platelets		:		150000\n";
		bloodValuesList.add(report);
		expectedReportsMap.put(ScreenTitles.VIEW_BLOOD_REPORTS, bloodValuesList);
		actualReportsMap.keySet().stream().forEach((key) -> {
	        List<String> actualList = actualReportsMap.get(key);
	        List<String> expectedList = expectedReportsMap.get(key);
	        for(int i=0; i<actualList.size(); i++) {
	        	assertTrue(actualList.get(i).equals(expectedList.get(i)));
	        }
	    });
	}

	@Test
	void testFetchReportByDateRange() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		Map<String, List<String>> actualReportsMap = viewReportsUtil.fetchReportByDateRange("2021-04-01", "2021-04-21");
		Map<String, List<String>> expectedReportsMap = new HashMap<>();
		List<String> bloodValuesList = new ArrayList<>();
		String report = "Date of Collection		:		2021-04-19\n"
				+ "Date				:		2021-04-20\n"
				+ "CBC:\n"
				+ "	RBC			:		4.32\n"
				+ "	Hematocrit		:		38.8\n"
				+ "	Haemoglobin		:		13.5\n"
				+ "	WBC			:		3500\n"
				+ "	Platelets		:		150000\n";
		bloodValuesList.add(report);
		expectedReportsMap.put(ScreenTitles.VIEW_BLOOD_REPORTS, bloodValuesList);
		actualReportsMap.keySet().stream().forEach((key) -> {
	        List<String> actualList = actualReportsMap.get(key);
	        List<String> expectedList = expectedReportsMap.get(key);
	        for(int i=0; i<actualList.size(); i++) {
	        	assertTrue(actualList.get(i).equals(expectedList.get(i)));
	        }
	    });
	}

	@Test
	void testGetBloodReportByTest() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		String actualReport = viewReportsUtil.getBloodReportByTest();
		String expectedReport = "Date of Collection		:		2020-05-20\n"
				+ "Date				:		2021-05-21\n"
				+ "CBC:\n"
				+ "	RBC			:		4.35\n"
				+ "	Hematocrit		:		60.0\n"
				+ "	Haemoglobin		:		14.0\n"
				+ "	WBC			:		3501\n"
				+ "	Platelets		:		150010\n";
		assertEquals(expectedReport, actualReport);
	}

	@Test
	void testGetKidneyReportByTest() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		String actualReport = viewReportsUtil.getKidneyReportByTest();
		String expectedReport = "Date of Collection		:		2021-05-20\n"
				+ "Date				:		2021-05-21\n"
				+ "Creatinine			:		1.5\n"
				+ "BUN				:		10\n";
		assertEquals(expectedReport, actualReport);
	}

	@Test
	void testGetLiverReportByTest() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		String actualReport = viewReportsUtil.getLiverReportByTest();
		String expectedReport = "Date of Collection		:		2021-05-20\n"
				+ "Date				:		2021-05-21\n"
				+ "ALT				:		30\n"
				+ "AST				:		40\n"
				+ "ALP				:		119\n"
				+ "Albumin				:		6.8\n"
				+ "Bilirubin			:		0.5\n";
		assertEquals(expectedReport, actualReport);
	}

	@Test
	void testGetVisionReportByTest() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		String actualReport = viewReportsUtil.getVisionReportByTest();
		String expectedReport = "Date of Collection		:		2021-05-20\n"
				+ "Date		:		2021-05-21\n"
				+ "Vision				:		20/40\n"
				+ "";
		for(byte b: actualReport.getBytes())
			System.out.print(b+", ");
		System.out.println();
		for(byte b: expectedReport.getBytes())
			System.out.print(b+", ");
		assertEquals(expectedReport, actualReport);
	}

	@Test
	void testGetCovidReportByTest() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		viewReportsUtil = new ViewReportsUtilImpl();
		String actualReport = viewReportsUtil.getCovidReportByTest();
		String expectedReport = "Date of Collection		:		2021-04-29\n"
				+ "Date				:		2021-05-01\n"
				+ "Covid				:		Detected\n";
		assertEquals(expectedReport, actualReport);
	}
}
