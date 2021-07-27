package persistence.common.jsonUtil.utilImpl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import persistence.common.JSONConstants;
import persistence.common.jsonUtil.util.JsonIdealReportParser;
import persistence.common.reports.model.CBC;
import persistence.common.reports.model.Kidney;
import persistence.common.reports.model.Liver;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 * This class is responsible for parsing the Ideal reports JSON file
 */
public class JsonIdealReportParserImpl implements JsonIdealReportParser {

    private String fileName;

    public JsonIdealReportParserImpl(){
        fileName = JSONConstants.IDEAL_REPORTS_FILENAME;
    }

    @Override
    public Map parseIdealReports() {
        try {
        	InputStream fileInputStream = JsonPatientReportParserImpl.class.getClassLoader().getResourceAsStream(fileName);
        	Reader reader = new InputStreamReader(fileInputStream);
            Object obj = new JSONParser().parse(reader);
            return (JSONObject) obj;
        }
        catch (Exception e){
            System.out.println("Json Parsing exception : "+e.getMessage());
        }
        return null;
    }

    @Override
    public CBC getCBCMin(Map CBCPanel){
        CBC cbcMin = new CBC();
        cbcMin.setRbc((float) (double) CBCPanel.get(JSONConstants.RBC_MIN));
        cbcMin.setWbc((int) (long) CBCPanel.get(JSONConstants.WBC_MIN));
        cbcMin.setPlatelets((int) (long) CBCPanel.get(JSONConstants.PLATELETS_MIN));
        cbcMin.setHaemoglobin((float) (double) CBCPanel.get(JSONConstants.HAEMOGLOBIN_MIN));
        cbcMin.setHematocrit((float) (double) CBCPanel.get(JSONConstants.HEMATOCRIT_MIN));
        return cbcMin;
    }

    @Override
    public CBC getCBCMax(Map CBCPanel){
        CBC cbcMax = new CBC();
        cbcMax.setRbc((float) (double) CBCPanel.get(JSONConstants.RBC_MAX));
        cbcMax.setWbc((int) (long) CBCPanel.get(JSONConstants.WBC_MAX));
        cbcMax.setPlatelets((int) (long) CBCPanel.get(JSONConstants.PLATELETS_MAX));
        cbcMax.setHaemoglobin((float) (double) CBCPanel.get(JSONConstants.HAEMOGLOBIN_MAX));
        cbcMax.setHematocrit((float) (double) CBCPanel.get(JSONConstants.HEMATOCRIT_MAX));
        return cbcMax;
    }

    @Override
    public Kidney getKidneyMin(Map kidneyPanel){
        Kidney kidneyPanelMin = new Kidney();
        kidneyPanelMin.setCreatinine(0);
        kidneyPanelMin.setBun((int) (long) kidneyPanel.get(JSONConstants.BUN_MIN));
        return kidneyPanelMin;
    }

    @Override
    public Kidney getKidneyMax(Map kidneyPanel){
        Kidney kidneyPanelMax = new Kidney();
        kidneyPanelMax.setCreatinine((float) (double) kidneyPanel.get(JSONConstants.CREATININE_MAX));
        kidneyPanelMax.setBun((int) (long) kidneyPanel.get(JSONConstants.BUN_MAX));
        return kidneyPanelMax;
    }

    @Override
    public Liver getLiverMin(Map liverPanel){
        Liver liverPanelMin = new Liver();
        liverPanelMin.setAlt(0);
        liverPanelMin.setAst(0);
        liverPanelMin.setAlp(0);
        liverPanelMin.setAlbumin((float) (double) liverPanel.get(JSONConstants.ALBUMIN_MIN));
        liverPanelMin.setBilirubin((float) (double) liverPanel.get(JSONConstants.BILIRUBIN_MIN));
        return liverPanelMin;
    }

    @Override
    public Liver getLiverMax(Map liverPanel){
        Liver liverPanelMax = new Liver();
        liverPanelMax.setAlt((int) (long) liverPanel.get(JSONConstants.ALT_MAX));
        liverPanelMax.setAst((int) (long) liverPanel.get(JSONConstants.AST_MAX));
        liverPanelMax.setAlp((int) (long) liverPanel.get(JSONConstants.ALP_MAX));
        liverPanelMax.setAlbumin((float) (double) liverPanel.get(JSONConstants.ALBUMIN_MIN));
        liverPanelMax.setBilirubin((float) (double) liverPanel.get(JSONConstants.BILIRUBIN_MIN));
        return liverPanelMax;
    }
}
