package persistence.patient.utilImpl;

import persistence.common.reports.util.PatientReportValidationUtil;
import persistence.common.reports.utilImpl.PatientReportValidationUtilImpl;
import persistence.patient.dao.LabCheckDAO;
import persistence.patient.daoImpl.LabCheckDAOImpl;
import persistence.patient.model.LabCheck;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckRecommendationUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 * This class is responsible for finding Lab Check recommendations based on different factors
 */
public class LabCheckRecommendationUtilImpl implements LabCheckRecommendationUtil {
    Map<Integer, LabCheck> labCheckMap;

    public LabCheckRecommendationUtilImpl() {
        labCheckMap = new HashMap<>();
    }

    @Override
    public List<LabCheck> genderBasedRecommendation() {
        if(labCheckMap.isEmpty())
            setLabCheckMap();
        List<LabCheck> recommendations = new ArrayList<>();
        if(Patient.instance().getPatientGender().equals("F"))
            recommendations.add(labCheckMap.get(4));
        return recommendations;
    }

    @Override
    public List<LabCheck> ageBasedRecommendation() {
        if(labCheckMap.isEmpty())
            setLabCheckMap();
        List<LabCheck> recommendations = new ArrayList<>();
        Date dob = Date.valueOf(Patient.instance().getPatientDob());
        LocalDate today = LocalDate.now();
        LocalDate dobLocal = dob.toLocalDate();

        int patientAge = Period.between(dobLocal, today).getYears();

        if (patientAge<=10)
            recommendations.add(labCheckMap.get(3));
        else if (patientAge<=30){
            recommendations.add(labCheckMap.get(1));
        }
        else if (patientAge>=50) {
            recommendations.add(labCheckMap.get(2));
            recommendations.add(labCheckMap.get(5));
        }
        return recommendations;
    }

    @Override
    public List<LabCheck> historyBasedRecommendation(){
        if(labCheckMap.isEmpty())
            setLabCheckMap();
        List<LabCheck> recommendations = new ArrayList<>();

        PatientReportValidationUtil patientReportValidationUtil = new PatientReportValidationUtilImpl();

        boolean bloodIsNormal = patientReportValidationUtil.validateBloodReports();
        boolean kidneyIsNormal = patientReportValidationUtil.validateKidneyReports();
        boolean liverIsNormal = patientReportValidationUtil.validateLiverReports();
        boolean visionIsNormal = patientReportValidationUtil.validateEyeReports();

        if(!bloodIsNormal) recommendations.add(labCheckMap.get(7));
        if(!kidneyIsNormal) recommendations.add(labCheckMap.get(9));
        if(!liverIsNormal) recommendations.add(labCheckMap.get(8));
        if(!visionIsNormal) recommendations.add(labCheckMap.get(10));

        return recommendations;
    }

    private void setLabCheckMap(){
        LabCheckDAO labCheckDao = new LabCheckDAOImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        labCheckMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckupId(), labCheck);
    }
}
