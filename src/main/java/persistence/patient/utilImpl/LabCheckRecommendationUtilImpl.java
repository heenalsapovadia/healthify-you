package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckDao;
import persistence.patient.daoImpl.LabCheckDaoImpl;
import persistence.patient.model.LabCheck;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckRecommendationUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabCheckRecommendationUtilImpl implements LabCheckRecommendationUtil {
    HashMap<Integer, LabCheck> labCheckMap;
    @Override
    public List<LabCheck> genderBasedRecommendation() {
        if(labCheckMap == null)
            setLabCheckMap();
        List<LabCheck> recommendations = new ArrayList<>();
        if(Patient.getPatient().getPatientGender().equals("F"))
            recommendations.add(labCheckMap.get(4));
        return recommendations;
    }

    @Override
    public List<LabCheck> ageBasedRecommendation() {
        if(labCheckMap == null)
            setLabCheckMap();
        List<LabCheck> recommendations = new ArrayList<>();
        Date dob = Date.valueOf(Patient.getPatient().getPatientDob());
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

    private void setLabCheckMap(){
        LabCheckDao labCheckDao = new LabCheckDaoImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        labCheckMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckup_id(), labCheck);
    }
}
