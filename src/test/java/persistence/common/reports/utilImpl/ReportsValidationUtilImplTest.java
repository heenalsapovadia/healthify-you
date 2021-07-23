package persistence.common.reports.utilImpl;

import org.junit.Test;
import persistence.common.reports.model.*;
import persistence.common.reports.util.ReportsValidationUtil;
import static org.junit.Assert.*;

public class ReportsValidationUtilImplTest {

    ReportsValidationUtil reportsValidationUtil = new ReportsValidationUtilImpl();;

    @Test
    public void validateBloodReport() {
        Blood bloodReport = new Blood();
        CBC cbcReport = new CBC();
        cbcReport.setRbc((float) 4.5);
        cbcReport.setWbc(3600);
        cbcReport.setPlatelets(210000);
        cbcReport.setHaemoglobin((float) 15);
        cbcReport.setHematocrit((float) 39.1);
        bloodReport.setCbcPanel(cbcReport);

        CBC cbcMin = new CBC();
        cbcMin.setRbc((float) 4.32);
        cbcMin.setWbc(3500);
        cbcMin.setPlatelets(150000);
        cbcMin.setHaemoglobin((float) 13.5);
        cbcMin.setHematocrit((float) 38.8);

        CBC cbcMax = new CBC();
        cbcMax.setRbc((float) 5.72);
        cbcMax.setWbc(10500);
        cbcMax.setPlatelets(450000);
        cbcMax.setHaemoglobin((float) 17.5);
        cbcMax.setHematocrit((float) 50.0);

        assertTrue(reportsValidationUtil.validateBloodReport(bloodReport, cbcMin, cbcMax));
    }

    @Test
    public void validateKidneyReport() {
        Kidney kidneyReport = new Kidney();
        kidneyReport.setCreatinine((float) 1.2);
        kidneyReport.setBun(12);

        Kidney kidneyPanelMin = new Kidney();
        kidneyPanelMin.setCreatinine(0);
        kidneyPanelMin.setBun(7);

        Kidney kidneyPanelMax = new Kidney();
        kidneyPanelMax.setCreatinine((float) 1.4);
        kidneyPanelMax.setBun(20);

        assertTrue(reportsValidationUtil.validateKidneyReport(kidneyReport, kidneyPanelMin, kidneyPanelMax));
    }

    @Test
    public void validateLiverReport() {
        Liver liverReport = new Liver();
        liverReport.setAlt(31);
        liverReport.setAst(34);
        liverReport.setAlp(100);
        liverReport.setAlbumin((float) 3.6);
        liverReport.setBilirubin((float) 0.2);

        Liver liverPanelMin = new Liver();
        liverPanelMin.setAlt(0);
        liverPanelMin.setAst(0);
        liverPanelMin.setAlp(0);
        liverPanelMin.setAlbumin((float) 3.5);
        liverPanelMin.setBilirubin((float) 0.1);

        Liver liverPanelMax = new Liver();
        liverPanelMax.setAlt(33);
        liverPanelMax.setAst(40);
        liverPanelMax.setAlp(120);
        liverPanelMax.setAlbumin((float) 5.0);
        liverPanelMax.setBilirubin((float) 1.2);

        assertTrue(reportsValidationUtil.validateLiverReport(liverReport, liverPanelMin, liverPanelMax));
    }

    @Test
    public void validateEyeReport() {
        Vision eyeReport = new Vision();
        eyeReport.setAcuity("20/20");

        assertTrue(reportsValidationUtil.validateEyeReport(eyeReport));
    }
}