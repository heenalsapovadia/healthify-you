package persistence.common.jsonUtil.utilImpl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import persistence.common.jsonUtil.util.JsonIdealReportParser;
import persistence.common.reports.model.CBC;
import persistence.common.reports.model.Kidney;
import persistence.common.reports.model.Liver;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class JsonIdealReportParserImpl implements JsonIdealReportParser {

    private String fileName;

    public JsonIdealReportParserImpl(){
        fileName = "IdealReports.json";
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
        cbcMin.setRbc((float) (double) CBCPanel.get("RBCMin"));
        cbcMin.setWbc((int) (long) CBCPanel.get("WBCMin"));
        cbcMin.setPlatelets((int) (long) CBCPanel.get("plateletsMin"));
        cbcMin.setHaemoglobin((float) (double) CBCPanel.get("HaemoglobinMin"));
        cbcMin.setHematocrit((float) (double) CBCPanel.get("HematocritMin"));
        return cbcMin;
    }

    @Override
    public CBC getCBCMax(Map CBCPanel){
        CBC cbcMax = new CBC();
        cbcMax.setRbc((float) (double) CBCPanel.get("RBCMax"));
        cbcMax.setWbc((int) (long) CBCPanel.get("WBCMax"));
        cbcMax.setPlatelets((int) (long) CBCPanel.get("plateletsMax"));
        cbcMax.setHaemoglobin((float) (double) CBCPanel.get("HaemoglobinMax"));
        cbcMax.setHematocrit((float) (double) CBCPanel.get("HematocritMax"));
        return cbcMax;
    }

    @Override
    public Kidney getKidneyMin(Map kidneyPanel){
        Kidney kidneyPanelMin = new Kidney();
        kidneyPanelMin.setCreatinine(0);
        kidneyPanelMin.setBun((int) (long) kidneyPanel.get("BUNMin"));
        return kidneyPanelMin;
    }

    @Override
    public Kidney getKidneyMax(Map kidneyPanel){
        Kidney kidneyPanelMax = new Kidney();
        kidneyPanelMax.setCreatinine((float) (double) kidneyPanel.get("CreatinineMax"));
        kidneyPanelMax.setBun((int) (long) kidneyPanel.get("BUNMax"));
        return kidneyPanelMax;
    }

    @Override
    public Liver getLiverMin(Map liverPanel){
        Liver liverPanelMin = new Liver();
        liverPanelMin.setAlt(0);
        liverPanelMin.setAst(0);
        liverPanelMin.setAlp(0);
        liverPanelMin.setAlbumin((float) (double) liverPanel.get("AlbuminMin"));
        liverPanelMin.setBilirubin((float) (double) liverPanel.get("BilirubinMin"));
        return liverPanelMin;
    }

    @Override
    public Liver getLiverMax(Map liverPanel){
        Liver liverPanelMax = new Liver();
        liverPanelMax.setAlt((int) (long) liverPanel.get("ALTMax"));
        liverPanelMax.setAst((int) (long) liverPanel.get("ASTMax"));
        liverPanelMax.setAlp((int) (long) liverPanel.get("ALPMax"));
        liverPanelMax.setAlbumin((float) (double) liverPanel.get("AlbuminMin"));
        liverPanelMax.setBilirubin((float) (double) liverPanel.get("BilirubinMin"));
        return liverPanelMax;
    }
}
