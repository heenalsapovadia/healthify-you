package persistence.common.reports.utilImpl;

import org.json.simple.JSONArray;
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
import java.util.List;
import java.util.Map;

public class PatientReportValidationUtilImpl implements PatientReportValidationUtil {

    JsonIdealReportParser jsonIdealReportParser = new JsonIdealReportParserImpl();

    JsonPatientReportParser jsonPatientReportParser = new JsonPatientReportParserImpl();

    ReportsValidationUtilImpl reportsValidationUtilImpl = new ReportsValidationUtilImpl();

    Map idealReports;

    Map patientReports;

    public PatientReportValidationUtilImpl(){
        try {
            idealReports = jsonIdealReportParser.parseIdealReports();
            patientReports = jsonPatientReportParser.getPatientReport(Patient.getPatient().getPatientId());
        }
        catch (Exception e){
            System.out.println("Exception in JSON parsing : "+e.getMessage());
        }
    }

    @Override
    public boolean validateBloodReports(){
        JSONArray tests = (JSONArray) patientReports.get("tests");
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
        JSONArray tests = (JSONArray) patientReports.get("tests");
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
        JSONArray tests = (JSONArray) patientReports.get("tests");
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
        JSONArray tests = (JSONArray) patientReports.get("tests");
        List<Vision> visionReportsList = jsonPatientReportParser.parseEyeReports((Map) tests.get(0));
//        Map liverPanel = (Map) idealReports.get("LiverFunction");
        LocalDate today = LocalDate.now();
        boolean visionIsNormal = true;
        for(Vision visionReport : visionReportsList){
            Date reportDate = visionReport.getDate();
            LocalDate reportDateLocal = reportDate.toLocalDate();
            int monthsgap = Period.between(reportDateLocal, today).getYears();
            if(monthsgap >= 1){
                visionIsNormal = visionIsNormal && reportsValidationUtilImpl.validateEyeReport(visionReport);
            }
        }
        return visionIsNormal;
    }
}
