package persistence.common.jsonUtil.utilImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import persistence.common.JSONConstants;
import persistence.common.jsonUtil.util.JsonPatientReportParser;
import persistence.common.reports.model.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 * This class is responsible for parsing the Patient reports JSON file
 */
public class JsonPatientReportParserImpl implements JsonPatientReportParser {

    private String fileName;

    public JsonPatientReportParserImpl(){
        fileName = JSONConstants.PATIENTS_REPORTS_FILENAME;
    }

    @Override
    public Map getPatientReport(int patientId) {
        try {
        	InputStream fileInputStream = JsonPatientReportParserImpl.class.getClassLoader().getResourceAsStream(fileName);
        	Reader reader = new InputStreamReader(fileInputStream);
            Object obj = new JSONParser().parse(reader);
            JSONObject jo = (JSONObject) obj;
            JSONArray patients = (JSONArray) jo.get(JSONConstants.PATIENT);

            for(int i=0; i<patients.size(); i++){
                Map patient = (Map) patients.get(i);
                int id = (int) (long) patient.get(JSONConstants.ID);
                if(id == patientId) {
                    return patient;
                }
            }
        }
        catch (Exception e){
            System.out.println("Json Parsing exception : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<Blood> parseBloodReports(Map tests) {
        JSONArray bloodReports = (JSONArray) tests.get(JSONConstants.BLOOD);
        List<Blood> bloodReportsList = new ArrayList<>();
        if(bloodReports == null) return bloodReportsList;
        for (int i = 0; i < bloodReports.size(); i++) {
            Blood bloodObj = new Blood();
            Map bloodReport = (Map) bloodReports.get(i);
            String date = (String) bloodReport.get(JSONConstants.DATE);
            bloodObj.setDate(Date.valueOf(date));

            CBC cbc = new CBC();
            Map cbcMap = (Map) bloodReport.get(JSONConstants.CBC);
            cbc.setRbc((float) (double) cbcMap.get(JSONConstants.RBC));
            cbc.setWbc((int) (long) cbcMap.get(JSONConstants.WBC));
            cbc.setPlatelets((int) (long) cbcMap.get(JSONConstants.PLATELETS));
            cbc.setHaemoglobin((float) (double) cbcMap.get(JSONConstants.HAEMOGLOBIN));
            cbc.setHematocrit((float) (double) cbcMap.get(JSONConstants.HEMATOCRIT));
            bloodObj.setCbcPanel(cbc);
            bloodObj.setDateOfCollection(Date.valueOf((String) bloodReport.get(JSONConstants.DATE_OF_COLLECTION)));
            bloodReportsList.add(bloodObj);
        }
        return bloodReportsList;
    }

    @Override
    public List<Kidney> parseKidneyReports(Map tests){
        JSONArray kidneyReports = (JSONArray) tests.get(JSONConstants.KIDNEY);
        List<Kidney> kidneyReportsList = new ArrayList<>();
        if(kidneyReports == null) return kidneyReportsList;
        for(int i=0; i<kidneyReports.size(); i++){
            Kidney kidneyObj = new Kidney();
            Map kidneyReport = (Map) kidneyReports.get(i);
            String date = (String) kidneyReport.get(JSONConstants.DATE);
            kidneyObj.setDate(Date.valueOf(date));
            kidneyObj.setCreatinine((float) (double) kidneyReport.get(JSONConstants.CREATININE));
            kidneyObj.setBun((int) (long) kidneyReport.get(JSONConstants.BUN));
            kidneyObj.setDateOfCollection(Date.valueOf((String) kidneyReport.get(JSONConstants.DATE_OF_COLLECTION)));
            kidneyReportsList.add(kidneyObj);
        }
        return kidneyReportsList;
    }

    @Override
    public List<Liver> parseLiverReports(Map tests){
        JSONArray liverReports = (JSONArray) tests.get(JSONConstants.LIVER);
        List<Liver> liverReportsList = new ArrayList<>();
        if(liverReports == null) return liverReportsList;
        for(int i=0; i<liverReports.size(); i++){
            Liver liverObj = new Liver();
            Map liverReport = (Map) liverReports.get(i);
            String date = (String) liverReport.get(JSONConstants.DATE);
            liverObj.setDate(Date.valueOf(date));
            liverObj.setAlt((int) (long) liverReport.get(JSONConstants.ALT));
            liverObj.setAst((int) (long) liverReport.get(JSONConstants.AST));
            liverObj.setAlp((int) (long) liverReport.get(JSONConstants.ALP));
            liverObj.setAlbumin((float) (double) liverReport.get(JSONConstants.ALBUMIN));
            liverObj.setBilirubin((float) (double) liverReport.get(JSONConstants.BILIRUBIN));
            liverObj.setDateOfCollection(Date.valueOf((String) liverReport.get(JSONConstants.DATE_OF_COLLECTION)));
            liverReportsList.add(liverObj);
        }
        return liverReportsList;
    }

    @Override
    public List<Vision> parseEyeReports(Map tests){
        JSONArray visionReports = (JSONArray) tests.get(JSONConstants.VISION);
        List<Vision> visionReportsList = new ArrayList<>();
        if(visionReports == null) return visionReportsList;
        for(int i=0; i<visionReports.size(); i++){
            Vision visionObj = new Vision();
            Map visionReport = (Map) visionReports.get(i);
            String date = (String) visionReport.get(JSONConstants.DATE);
            visionObj.setDate(Date.valueOf(date));
            visionObj.setAcuity((String) visionReport.get(JSONConstants.VISION));
            visionObj.setDateOfCollection(Date.valueOf((String) visionReport.get(JSONConstants.DATE_OF_COLLECTION)));
            visionReportsList.add(visionObj);
        }
        return visionReportsList;
    }
    
    @Override
    public List<Covid> parseCovidReports(Map tests){
        JSONArray covidReports = (JSONArray) tests.get(JSONConstants.COVID);
        List<Covid> covidReportsList = new ArrayList<>();
        if(covidReports == null) return covidReportsList;
        for(int i=0; i<covidReports.size(); i++){
        	Covid covidObj = new Covid();
            Map covidReport = (Map) covidReports.get(i);
            String date = (String) covidReport.get(JSONConstants.DATE);
            covidObj.setDate(Date.valueOf(date));
            RtPcr rtPcr = new RtPcr();
            Map rtPcrMap = (Map) covidReport.get(JSONConstants.RTPCR);
            rtPcr.setSarsCov2((String) rtPcrMap.get(JSONConstants.SARS_COV2));
            covidObj.setRtPcr(rtPcr);
            covidObj.setDateOfCollection(Date.valueOf((String) covidReport.get(JSONConstants.DATE_OF_COLLECTION)));
            covidReportsList.add(covidObj);
        }
        return covidReportsList;
    }
}
