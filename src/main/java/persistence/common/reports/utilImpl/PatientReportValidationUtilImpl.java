package persistence.common.reports.utilImpl;

import org.json.simple.JSONArray;
import persistence.common.JSONConstants;
import persistence.common.jsonUtil.util.JsonIdealReportParser;
import persistence.common.jsonUtil.util.JsonPatientReportParser;
import persistence.common.jsonUtil.utilImpl.JsonIdealReportParserImpl;
import persistence.common.jsonUtil.utilImpl.JsonPatientReportParserImpl;
import persistence.common.reports.model.*;
import persistence.common.reports.util.PatientReportValidationUtil;
import persistence.patient.model.Patient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 *
 */
public class PatientReportValidationUtilImpl implements PatientReportValidationUtil {

    private JsonIdealReportParser jsonIdealReportParser;

    private JsonPatientReportParser jsonPatientReportParser;

    private ReportsValidationUtilImpl reportsValidationUtilImpl;

    private Map idealReports;

    private Map patientReports;

    public PatientReportValidationUtilImpl(){
        jsonPatientReportParser = new JsonPatientReportParserImpl();
        jsonIdealReportParser = new JsonIdealReportParserImpl();
        reportsValidationUtilImpl = new ReportsValidationUtilImpl();
        idealReports = new HashMap();
        patientReports = new HashMap();
        try {
            idealReports = jsonIdealReportParser.parseIdealReports();
            patientReports = jsonPatientReportParser.getPatientReport(Patient.instance().getPatientId());
        }
        catch (Exception e){
            System.out.println("Exception in JSON parsing : "+e.getMessage());
        }
    }

    @Override
    public boolean validateBloodReports(){
        if(patientReports == null || patientReports.isEmpty()) return true;
        JSONArray tests = (JSONArray) patientReports.get(JSONConstants.TESTS);
        List<Blood> bloodReportsList = jsonPatientReportParser.parseBloodReports((Map) tests.get(0));
        Map bloodPanel = (Map) idealReports.get("Blood");
        Map CBCPanel = (Map) bloodPanel.get("CBC");
        CBC cbcMin = jsonIdealReportParser.getCBCMin(CBCPanel);
        CBC cbcMax = jsonIdealReportParser.getCBCMax(CBCPanel);

        LocalDate today = LocalDate.now();
        boolean bloodIsNormal = true;
        for(Blood bloodReport : bloodReportsList){
            Date reportDate = bloodReport.getDate();
            LocalDate reportDateLocal = reportDate.toLocalDate();
            int monthsgap = Period.between(reportDateLocal, today).getMonths();
            if(monthsgap <= 6 && monthsgap > 1){
                bloodIsNormal = bloodIsNormal && reportsValidationUtilImpl.validateBloodReport(bloodReport, cbcMin, cbcMax);
            }
        }
        return bloodIsNormal;
    }

    @Override
    public boolean validateKidneyReports(){
        if(patientReports == null || patientReports.isEmpty()) return true;
        JSONArray tests = (JSONArray) patientReports.get(JSONConstants.TESTS);
        List<Kidney> kidneyReportsList = jsonPatientReportParser.parseKidneyReports((Map) tests.get(0));
        Map kidneyPanel = (Map) idealReports.get("KidneyFunction");
        Kidney kidneyPanelMin = jsonIdealReportParser.getKidneyMin(kidneyPanel);
        Kidney kidneyPanelMax = jsonIdealReportParser.getKidneyMax(kidneyPanel);

        LocalDate today = LocalDate.now();
        boolean kidneyIsNormal = true;
        for(Kidney kidneyReport : kidneyReportsList){
            Date reportDate = kidneyReport.getDate();
            LocalDate reportDateLocal = reportDate.toLocalDate();
            int monthsgap = Period.between(reportDateLocal, today).getMonths();
            if(monthsgap <= 6 && monthsgap > 1){
                kidneyIsNormal = kidneyIsNormal && reportsValidationUtilImpl.validateKidneyReport(kidneyReport, kidneyPanelMin, kidneyPanelMax);
            }
        }
        return kidneyIsNormal;
    }

    @Override
    public boolean validateLiverReports(){
        if(patientReports == null || patientReports.isEmpty()) return true;
        JSONArray tests = (JSONArray) patientReports.get(JSONConstants.TESTS);
        List<Liver> liverReportsList = jsonPatientReportParser.parseLiverReports((Map) tests.get(0));
        Map liverPanel = (Map) idealReports.get("LiverFunction");
        Liver liverPanelMin = jsonIdealReportParser.getLiverMin(liverPanel);
        Liver liverPanelMax = jsonIdealReportParser.getLiverMax(liverPanel);

        LocalDate today = LocalDate.now();
        boolean liverIsNormal = true;
        for(Liver liverReport : liverReportsList){
            Date reportDate = liverReport.getDate();
            LocalDate reportDateLocal = reportDate.toLocalDate();
            int monthsgap = Period.between(reportDateLocal, today).getMonths();
            if(monthsgap <= 6 && monthsgap > 1){
                liverIsNormal = liverIsNormal && reportsValidationUtilImpl.validateLiverReport(liverReport, liverPanelMin, liverPanelMax);
            }
        }
        return liverIsNormal;
    }

    @Override
    public boolean validateEyeReports(){
        if(patientReports == null || patientReports.isEmpty()) return true;
        JSONArray tests = (JSONArray) patientReports.get(JSONConstants.TESTS);
        List<Vision> visionReportsList = jsonPatientReportParser.parseEyeReports((Map) tests.get(0));
        LocalDate today = LocalDate.now();
        boolean visionIsNormal = true;
        boolean checkupInLastYear = false;
        for(Vision visionReport : visionReportsList){
            Date reportDate = visionReport.getDate();
            LocalDate reportDateLocal = reportDate.toLocalDate();
            int monthsgap = Period.between(reportDateLocal, today).getYears();
            if(monthsgap <= 1){
                visionIsNormal = visionIsNormal && reportsValidationUtilImpl.validateEyeReport(visionReport);
                checkupInLastYear = true;
            }
        }
        return visionIsNormal && checkupInLastYear;
    }
}
