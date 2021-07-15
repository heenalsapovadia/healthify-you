package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckDao;
import persistence.patient.daoImpl.LabCheckDaoImpl;
import persistence.patient.model.LabCheck;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckRecommendationUtil;

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
        return null;
    }

    private void setLabCheckMap(){
        LabCheckDao labCheckDao = new LabCheckDaoImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        labCheckMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckup_id(), labCheck);
    }
}
