package persistence.common.jsonUtil.utilImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import persistence.common.jsonUtil.util.JsonPatientReportParser;
import persistence.common.reports.model.*;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPatientReportParserImpl implements JsonPatientReportParser {

    private String pathToResources;

    private String fileName;

    public JsonPatientReportParserImpl(){
        pathToResources = "src/main/resources";
        fileName = "patientHistory.json";
    }

    @Override
    public Map getPatientReport(int patientId) {
        try {
            Object obj = new JSONParser().parse(new FileReader(new File(pathToResources).getAbsolutePath() + "/" + fileName));
            JSONObject jo = (JSONObject) obj;
            JSONArray patients = (JSONArray) jo.get("patient");

            for(int i=0; i<patients.size(); i++){
                Map patient = (Map) patients.get(i);
                int id = (int) (long) patient.get("id");
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
        JSONArray bloodReports = (JSONArray) tests.get("Blood");
        List<Blood> bloodReportsList = new ArrayList<>();
        if(bloodReports == null) return bloodReportsList;
        for (int i = 0; i < bloodReports.size(); i++) {
            Blood bloodObj = new Blood();
            Map bloodReport = (Map) bloodReports.get(i);
            String date = (String) bloodReport.get("Date");
            bloodObj.setDate(Date.valueOf(date));

            CBC cbc = new CBC();
            Map cbcMap = (Map) bloodReport.get("CBC");
            cbc.setRbc((float) (double) cbcMap.get("RBC"));
            cbc.setWbc((int) (long) cbcMap.get("WBC"));
            cbc.setPlatelets((int) (long) cbcMap.get("Platelets"));
            cbc.setHaemoglobin((float) (double) cbcMap.get("Haemoglobin"));
            cbc.setHematocrit((float) (double) cbcMap.get("Hematocrit"));
            bloodObj.setCbcPanel(cbc);
            bloodObj.setDateOfCollection(Date.valueOf((String) bloodReport.get("DateOfCollection")));
            bloodReportsList.add(bloodObj);
        }
        return bloodReportsList;
    }

    @Override
    public List<Kidney> parseKidneyReports(Map tests){
        JSONArray kidneyReports = (JSONArray) tests.get("Kidney");
        List<Kidney> kidneyReportsList = new ArrayList<>();
        if(kidneyReports == null) return kidneyReportsList;
        for(int i=0; i<kidneyReports.size(); i++){
            Kidney kidneyObj = new Kidney();
            Map kidneyReport = (Map) kidneyReports.get(i);
            String date = (String) kidneyReport.get("Date");
            kidneyObj.setDate(Date.valueOf(date));
            kidneyObj.setCreatinine((float) (double) kidneyReport.get("Creatinine"));
            kidneyObj.setBun((int) (long) kidneyReport.get("BUN"));
            kidneyObj.setDateOfCollection(Date.valueOf((String) kidneyReport.get("DateOfCollection")));
            kidneyReportsList.add(kidneyObj);
        }
        return kidneyReportsList;
    }

    @Override
    public List<Liver> parseLiverReports(Map tests){
        JSONArray liverReports = (JSONArray) tests.get("Liver");
        List<Liver> liverReportsList = new ArrayList<>();
        if(liverReports == null) return liverReportsList;
        for(int i=0; i<liverReports.size(); i++){
            Liver liverObj = new Liver();
            Map liverReport = (Map) liverReports.get(i);
            String date = (String) liverReport.get("Date");
            liverObj.setDate(Date.valueOf(date));
            liverObj.setAlt((int) (long) liverReport.get("ALT"));
            liverObj.setAst((int) (long) liverReport.get("AST"));
            liverObj.setAlp((int) (long) liverReport.get("ALP"));
            liverObj.setAlbumin((float) (double) liverReport.get("Albumin"));
            liverObj.setBilirubin((float) (double) liverReport.get("Bilirubin"));
            liverObj.setDateOfCollection(Date.valueOf((String) liverReport.get("DateOfCollection")));
            liverReportsList.add(liverObj);
        }
        return liverReportsList;
    }

    @Override
    public List<Vision> parseEyeReports(Map tests){
        JSONArray visionReports = (JSONArray) tests.get("Vision");
        List<Vision> visionReportsList = new ArrayList<>();
        if(visionReports == null) return visionReportsList;
        for(int i=0; i<visionReports.size(); i++){
            Vision visionObj = new Vision();
            Map visionReport = (Map) visionReports.get(i);
            String date = (String) visionReport.get("Date");
            visionObj.setDate(Date.valueOf(date));
            visionObj.setAcuity((String) visionReport.get("Vision"));
            visionObj.setDateOfCollection(Date.valueOf((String) visionReport.get("DateOfCollection")));
            visionReportsList.add(visionObj);
        }
        return visionReportsList;
    }
    
    @Override
    public List<Covid> parseCovidReports(Map tests){
        JSONArray covidReports = (JSONArray) tests.get("Covid");
        List<Covid> covidReportsList = new ArrayList<>();
        if(covidReports == null) return covidReportsList;
        for(int i=0; i<covidReports.size(); i++){
        	Covid covidObj = new Covid();
            Map covidReport = (Map) covidReports.get(i);
            String date = (String) covidReport.get("Date");
            covidObj.setDate(Date.valueOf(date));
            RtPcr rtPcr = new RtPcr();
            Map rtPcrMap = (Map) covidReport.get("RTPCR");
            rtPcr.setSarsCov2((String) rtPcrMap.get("SARs-CoV2"));
            covidObj.setRtPcr(rtPcr);
            covidObj.setDateOfCollection(Date.valueOf((String) covidReport.get("DateOfCollection")));
            covidReportsList.add(covidObj);
        }
        return covidReportsList;
    }
}
