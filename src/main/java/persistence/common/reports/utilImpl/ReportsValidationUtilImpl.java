package persistence.common.reports.utilImpl;

import persistence.common.reports.model.*;
import persistence.common.reports.util.ReportsValidationUtil;

/**
 * @author Heenal Sapovadia
 *
 */
public class ReportsValidationUtilImpl implements ReportsValidationUtil {

    @Override
    public boolean validateBloodReport(Blood bloodReport, CBC cbcMin, CBC cbcMax){
        CBC patientCBC = bloodReport.getCbcPanel();

        boolean rbc = (patientCBC.getRbc() > cbcMin.getRbc()) && (patientCBC.getRbc() < cbcMax.getRbc());
        boolean wbc = (patientCBC.getWbc() > cbcMin.getWbc()) && (patientCBC.getWbc() < cbcMax.getWbc());
        boolean platelets = (patientCBC.getPlatelets() > cbcMin.getPlatelets()) && (patientCBC.getPlatelets() < cbcMax.getPlatelets());
        boolean haemoglobin = (patientCBC.getHaemoglobin() > cbcMin.getHaemoglobin()) && (patientCBC.getHaemoglobin() < cbcMax.getHaemoglobin());
        boolean hematocrit = (patientCBC.getHematocrit() > cbcMin.getHematocrit()) && (patientCBC.getHematocrit() < cbcMax.getHematocrit());

        return rbc && wbc && platelets && haemoglobin && hematocrit;
    }

    @Override
    public boolean validateKidneyReport(Kidney kidneyReport, Kidney kidneyPanelMin, Kidney kidneyPanelMax){
        boolean creatinine = (kidneyReport.getCreatinine() > 0) && (kidneyReport.getCreatinine() < kidneyPanelMax.getCreatinine());
        boolean bun = (kidneyReport.getBun() > kidneyPanelMin.getBun()) && (kidneyReport.getBun() < kidneyPanelMax.getBun());

        return creatinine && bun;
    }

    @Override
    public boolean validateLiverReport(Liver liverReport, Liver liverPanelMin, Liver liverPanelMax){
        boolean alt = liverReport.getAlt() < liverPanelMax.getAlt();
        boolean ast = liverReport.getAst() < liverPanelMax.getAst();
        boolean alp = liverReport.getAlp() < liverPanelMax.getAlp();
        boolean albumin = (liverReport.getAlbumin() > liverPanelMin.getAlbumin()) && (liverReport.getAlbumin() < liverPanelMax.getAlbumin());
        boolean bilirubin = (liverReport.getBilirubin() > liverPanelMin.getBilirubin()) && (liverReport.getBilirubin() < liverPanelMax.getBilirubin());

        return alt && ast && alp && albumin && bilirubin;
    }

    @Override
    public boolean validateEyeReport(Vision eyeReport){
        boolean acuity;
        String[] vision = eyeReport.getAcuity().split("/");
        int nearVision = Integer.parseInt(vision[0]);
        int farVision = Integer.parseInt(vision[1]);
        acuity = (nearVision == 20) && (farVision == 20);
        return  acuity;
    }

}
