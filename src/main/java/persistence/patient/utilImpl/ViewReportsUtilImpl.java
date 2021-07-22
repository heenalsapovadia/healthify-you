/**
 * 
 */
package persistence.patient.utilImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import persistence.common.jsonUtil.util.JsonPatientReportParser;
import persistence.common.jsonUtil.utilImpl.JsonPatientReportParserImpl;
import persistence.common.reports.model.Blood;
import persistence.common.reports.model.Covid;
import persistence.common.reports.model.Kidney;
import persistence.common.reports.model.Liver;
import persistence.common.reports.model.Vision;
import persistence.patient.model.Patient;
import persistence.patient.util.ViewReportsUtil;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Gurleen Saluja
 *
 */
public class ViewReportsUtilImpl implements ViewReportsUtil {
	
	private JsonPatientReportParser jsonReportParser = new JsonPatientReportParserImpl();
	private Map<Object, Object> patientReports;
	private JSONArray tests;
	
	public ViewReportsUtilImpl() {
		jsonReportParser = new JsonPatientReportParserImpl();
		patientReports = jsonReportParser.getPatientReport(Patient.getPatient().getPatientId());
		tests = (JSONArray) patientReports.get("tests");
	}

	@Override
	public Map<String, List<String>> fetchReportByDate(String date) {
		Map<String, List<String>> reportsMap = new HashMap<>();
		
		List<String> bloodReports = getBloodReportByDate(date);
		List<String> kidneyReports = getKidneyReportByDate(date);
		List<String> liverReports = getLiverReportByDate(date);
		List<String> visionReports = getVisionReportByDate(date);
		List<String> covidReports = getCovidReportByDate(date);
		
		if(!bloodReports.isEmpty()) {
			reportsMap.put(ScreenTitles.VIEW_BLOOD_REPORTS, bloodReports);
		}
		if(!kidneyReports.isEmpty()) {
			reportsMap.put(ScreenTitles.VIEW_KIDNEY_REPORTS, kidneyReports);
		}
		if(!liverReports.isEmpty()) {
			reportsMap.put(ScreenTitles.VIEW_LIVER_REPORTS, liverReports);
		}
		if(!visionReports.isEmpty()) {
			reportsMap.put(ScreenTitles.VIEW_VISION_REPORTS, visionReports);
		}
		if(!covidReports.isEmpty()) {
			reportsMap.put(ScreenTitles.VIEW_COVID_REPORTS, covidReports);
		}
		
		return reportsMap;
	}

	@Override
	public Map<String, List<String>> fetchReportByDateRange(String fromDate, String toDate) {
		Map<String, List<String>> outputMap = new HashMap<>();
		int i = 0;
		
		List<String> bloodReports = getBloodReport(fromDate, toDate, i);
		List<String> kidneyReports = getKidneyReport(fromDate, toDate, i);
		List<String> liverReports = getLiverReport(fromDate, toDate, i);
		List<String> visionReports = getVisionReport(fromDate, toDate, i);
		List<String> covidReports = getCovidReport(fromDate, toDate, i);
		
		if(!bloodReports.isEmpty()) {
			outputMap.put(ScreenTitles.VIEW_BLOOD_REPORTS, bloodReports);
		}
		if(!kidneyReports.isEmpty()) {
			outputMap.put(ScreenTitles.VIEW_KIDNEY_REPORTS, kidneyReports);
		}
		if(!liverReports.isEmpty()) {
			outputMap.put(ScreenTitles.VIEW_LIVER_REPORTS, liverReports);
		}
		if(!visionReports.isEmpty()) {
			outputMap.put(ScreenTitles.VIEW_VISION_REPORTS, visionReports);
		}
		if(!covidReports.isEmpty()) {
			outputMap.put(ScreenTitles.VIEW_COVID_REPORTS, covidReports);
		}
		
		return outputMap;
	}
	
	public String getBloodReportByTest() {
		List<Blood> bloodReportsList = jsonReportParser.parseBloodReports((Map) tests.get(0));
		Blood bloodReportObj = null;
		StringBuilder output = null;
		output = new StringBuilder();
		for(Blood report: bloodReportsList) {
			if(output.isEmpty()) {
				bloodReportObj = report;
				setBloodReportContent(output, report);
			}
			else if(!output.isEmpty() && bloodReportObj != null 
					&& report.getDate().toString().compareTo(bloodReportObj.getDate().toString()) > 0) {
				output = new StringBuilder();
				setBloodReportContent(output, report);
			}
		}
		
		return output.toString();
	}
	
	private List<String> getBloodReportByDate(String date) {
		List<Blood> bloodReportsList = jsonReportParser.parseBloodReports((Map) tests.get(0));//i
		List<String> outputReportList = new ArrayList<>();
		StringBuilder output = null;
		if(date != null) {
			for(Blood report: bloodReportsList) {
				if(date.compareTo(report.getDate().toString()) == 0) {
					output = new StringBuilder();
					setBloodReportContent(output, report);
					outputReportList.add(output.toString());
				}
			}
		}
		
		return outputReportList;
	}
	
	private StringBuilder setBloodReportContent(StringBuilder output, Blood report) {
		output.append(ScreenFields.DATE_OF_COLLECTION).append(CommonConstants.commonTextSeparator).append(report.getDateOfCollection()).append(CommonConstants.NEW_LINE);
		output.append(ScreenFields.DATE).append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getDate()).append(CommonConstants.NEW_LINE);
		output.append("CBC:").append(CommonConstants.NEW_LINE).append(CommonConstants.singleTab);
		output.append("RBC").append(CommonConstants.singleTab).append(CommonConstants.commonTextSeparator).append(report.getCbcPanel().getRbc()).append(CommonConstants.NEW_LINE).append(CommonConstants.singleTab);
		output.append("Hematocrit").append(CommonConstants.commonTextSeparator).append(report.getCbcPanel().getHematocrit()).append(CommonConstants.NEW_LINE).append(CommonConstants.singleTab);
		output.append("Haemoglobin").append(CommonConstants.commonTextSeparator).append(report.getCbcPanel().getHaemoglobin()).append(CommonConstants.NEW_LINE).append(CommonConstants.singleTab);
		output.append("WBC").append(CommonConstants.singleTab).append(CommonConstants.commonTextSeparator).append(report.getCbcPanel().getWbc()).append(CommonConstants.NEW_LINE).append(CommonConstants.singleTab);
		output.append("Platelets").append(CommonConstants.commonTextSeparator).append(report.getCbcPanel().getPlatelets()).append(CommonConstants.NEW_LINE);
		
		return output;
	}
	
	public String getKidneyReportByTest() {
		List<Kidney> kidneyReportsList = jsonReportParser.parseKidneyReports((Map) tests.get(0));//i
		Kidney kidneyReportObj = null;
		StringBuilder output = null;
		output = new StringBuilder();
		for(Kidney report: kidneyReportsList) {
			if(output.isEmpty()) {
				kidneyReportObj = report;
				setKidneyReportContent(output, report);
			}
			else if(!output.isEmpty() && kidneyReportObj != null 
					&& report.getDate().toString().compareTo(kidneyReportObj.getDate().toString()) > 0) {
				output = new StringBuilder();
				setKidneyReportContent(output, report);
			}
		}
		
		return output.toString();
	}
	
	private List<String> getKidneyReportByDate(String date) {
		List<Kidney> kidneyReportsList = jsonReportParser.parseKidneyReports((Map) tests.get(0));//i
		List<String> outputReportList = new ArrayList<>();
		StringBuilder output = null;
		if(date != null) {
			for(Kidney report: kidneyReportsList) {
				if(date.compareTo(report.getDate().toString()) == 0) {
					output = new StringBuilder();
					setKidneyReportContent(output, report);
					outputReportList.add(output.toString());
				}
			}
		}
		
		return outputReportList;
	}
	
	private void setKidneyReportContent(StringBuilder output, Kidney report) {
		output.append(ScreenFields.DATE_OF_COLLECTION).append(CommonConstants.commonTextSeparator).append(report.getDateOfCollection()).append(CommonConstants.NEW_LINE);
		output.append(ScreenFields.DATE).append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getDate()).append(CommonConstants.NEW_LINE);
		output.append("Creatinine").append(CommonConstants.singleTab).append(CommonConstants.commonTextSeparator).append(report.getCreatinine()).append(CommonConstants.NEW_LINE);
		output.append("BUN").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getBun()).append(CommonConstants.NEW_LINE);
	}
	
	public String getLiverReportByTest() {
		List<Liver> liverReportsList = jsonReportParser.parseLiverReports((Map) tests.get(0));//i
		Liver liverReportObj = null;
		StringBuilder output = null;
		output = new StringBuilder();
		for(Liver report: liverReportsList) {
			if(output.isEmpty()) {
				liverReportObj = report;
				setLiverReportContent(output, report);
			}
			else if(!output.isEmpty() && liverReportObj != null 
					&& report.getDate().toString().compareTo(liverReportObj.getDate().toString()) > 0) {
				output = new StringBuilder();
				setLiverReportContent(output, report);
			}
		}
		
		return output.toString();
	}
	
	private List<String> getLiverReportByDate(String date) {
		List<Liver> liverReportsList = jsonReportParser.parseLiverReports((Map) tests.get(0));//i
		List<String> outputReportList = new ArrayList<>();
		StringBuilder output = null;
		if(date != null) {
			for(Liver report: liverReportsList) {
				if(date.compareTo(report.getDate().toString()) == 0) {
					output = new StringBuilder();
					setLiverReportContent(output, report);
					outputReportList.add(output.toString());
				}
			}
		}
		
		return outputReportList;
	}
	
	private void setLiverReportContent(StringBuilder output, Liver report) {
		output.append(ScreenFields.DATE_OF_COLLECTION).append(CommonConstants.commonTextSeparator).append(report.getDateOfCollection()).append(CommonConstants.NEW_LINE);
		output.append(ScreenFields.DATE).append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getDate()).append(CommonConstants.NEW_LINE);
		output.append("ALT").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getAlt()).append(CommonConstants.NEW_LINE);
		output.append("AST").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getAst()).append(CommonConstants.NEW_LINE);
		output.append("ALP").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getAlp()).append(CommonConstants.NEW_LINE);
		output.append("Albumin").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getAlbumin()).append(CommonConstants.NEW_LINE);
		output.append("Bilirubin").append(CommonConstants.singleTab).append(CommonConstants.commonTextSeparator).append(report.getBilirubin()).append(CommonConstants.NEW_LINE);
	}
	
	public String getVisionReportByTest() {
		List<Vision> visionReportsList = jsonReportParser.parseEyeReports((Map) tests.get(0));//i
		Vision visionReportObj = null;
		StringBuilder output = null;
		output = new StringBuilder();
		for(Vision report: visionReportsList) {
			if(output.isEmpty()) {
				visionReportObj = report;
				setVisionReportContent(output, report);
			}
			else if(!output.isEmpty() && visionReportObj != null 
					&& report.getDate().toString().compareTo(visionReportObj.getDate().toString()) > 0) {
				output = new StringBuilder();
				setVisionReportContent(output, report);
			}
		}
		
		return output.toString();
	}
	
	private List<String> getVisionReportByDate(String date) {
		List<Vision> visionReportsList = jsonReportParser.parseEyeReports((Map) tests.get(0));//i
		List<String> outputReportList = new ArrayList<>();
		StringBuilder output = null;
		if(date != null) {
			for(Vision report: visionReportsList) {
				if(date.compareTo(report.getDate().toString()) == 0) {
					output = new StringBuilder();
					setVisionReportContent(output, report);
					outputReportList.add(output.toString());
				}
			}
		}
		
		return outputReportList;
	}
	
	private void setVisionReportContent(StringBuilder output, Vision report) {
		output.append(ScreenFields.DATE_OF_COLLECTION).append(CommonConstants.commonTextSeparator).append(report.getDateOfCollection()).append(CommonConstants.NEW_LINE);
		output.append(ScreenFields.DATE).append(CommonConstants.commonTextSeparator).append(report.getDate()).append(CommonConstants.NEW_LINE);
		output.append("Vision").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getAcuity()).append(CommonConstants.NEW_LINE);
	}
	
	public String getCovidReportByTest() {
		List<Covid> covidReportsList = jsonReportParser.parseCovidReports((Map) tests.get(0));//i
		Covid covidReportObj = null;
		StringBuilder output = null;
		output = new StringBuilder();
		for(Covid report: covidReportsList) {
			if(output.isEmpty()) {
				covidReportObj = report;
				setCovidReportContent(output, report);
			}
			else if(!output.isEmpty() && covidReportObj != null 
					&& report.getDate().toString().compareTo(covidReportObj.getDate().toString()) > 0) {
				output = new StringBuilder();
				setCovidReportContent(output, report);
			}
		}
		
		return output.toString();
	}
	
	private List<String> getCovidReportByDate(String date) {
		List<Covid> covidReportsList = jsonReportParser.parseCovidReports((Map) tests.get(0));//i
		List<String> outputReportList = new ArrayList<>();
		StringBuilder output = null;
		if(date != null) {
			for(Covid report: covidReportsList) {
				if(date.compareTo(report.getDate().toString()) == 0) {
					output = new StringBuilder();
					setCovidReportContent(output, report);
					outputReportList.add(output.toString());
				}
			}
		}
		
		return outputReportList;
	}
	
	private void setCovidReportContent(StringBuilder output, Covid report) {
		output.append(ScreenFields.DATE_OF_COLLECTION).append(CommonConstants.commonTextSeparator).append(report.getDateOfCollection()).append(CommonConstants.NEW_LINE);
		output.append(ScreenFields.DATE).append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getDate()).append(CommonConstants.NEW_LINE);
		output.append("Covid").append(CommonConstants.DOUBLE_TAB).append(CommonConstants.commonTextSeparator).append(report.getRtPcr().getSarsCov2()).append(CommonConstants.NEW_LINE);
	}
	
	private List<String> getBloodReport(String fromDate, String toDate, int i) {
		List<String> outputReportList = new ArrayList<>();
		List<Blood> bloodReportsList = jsonReportParser.parseBloodReports((Map) tests.get(i));
		for(Blood report: bloodReportsList) {
			StringBuilder output = new StringBuilder();
			if((report.getDate().toString().compareTo(fromDate) >= 0) 
					&& (report.getDate().toString().compareTo(toDate) <= 0)) {
				setBloodReportContent(output, report);
				outputReportList.add(output.toString());
			}
		}
		
		return outputReportList;
	}
	
	private List<String> getKidneyReport(String fromDate, String toDate, int i) {
		List<String> outputReportList = new ArrayList<>();
		List<Kidney> kidneyReportsList = jsonReportParser.parseKidneyReports((Map) tests.get(i));
		for(Kidney report: kidneyReportsList) {
			StringBuilder output = new StringBuilder();
			if((report.getDate().toString().compareTo(fromDate) >= 0) 
					&& (report.getDate().toString().compareTo(toDate) <= 0)) {
				setKidneyReportContent(output, report);
				outputReportList.add(output.toString());
			}
		}
		
		return outputReportList;
	}
	
	private List<String> getVisionReport(String fromDate, String toDate, int i) {
		List<String> outputReportList = new ArrayList<>();
		List<Vision> visionReportsList = jsonReportParser.parseEyeReports((Map) tests.get(i));
		for(Vision report: visionReportsList) {
			StringBuilder output = new StringBuilder();
			if((report.getDate().toString().compareTo(fromDate) >= 0) 
					&& (report.getDate().toString().compareTo(toDate) <= 0)) {
				setVisionReportContent(output, report);
				outputReportList.add(output.toString());
			}
		}
		
		return outputReportList;
	}
	
	private List<String> getLiverReport(String fromDate, String toDate, int i) {
		List<String> outputReportList = new ArrayList<>();
		List<Liver> liverReportsList = jsonReportParser.parseLiverReports((Map) tests.get(i));
		for(Liver report: liverReportsList) {
			StringBuilder output = new StringBuilder();
			if((report.getDate().toString().compareTo(fromDate) >= 0) 
					&& (report.getDate().toString().compareTo(toDate) <= 0)) {
				setLiverReportContent(output, report);
				outputReportList.add(output.toString());
			}
		}
		
		return outputReportList;
	}
	
	private List<String> getCovidReport(String fromDate, String toDate, int i) {
		List<String> outputReportList = new ArrayList<>();
		List<Covid> covidReportsList = jsonReportParser.parseCovidReports((Map) tests.get(i));
		for(Covid report: covidReportsList) {
			StringBuilder output = new StringBuilder();
			if((report.getDate().toString().compareTo(fromDate) >= 0) 
					&& (report.getDate().toString().compareTo(toDate) <= 0)) {
				setCovidReportContent(output, report);
				outputReportList.add(output.toString());
			}
		}
		
		return outputReportList;
	}
}
