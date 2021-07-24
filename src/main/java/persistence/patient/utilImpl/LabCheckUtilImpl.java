package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckDAO;
import persistence.patient.daoImpl.LabCheckDAOImpl;
import persistence.patient.model.LabCheck;
import persistence.patient.util.LabCheckUtil;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.CommonErrors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LabCheckUtilImpl implements LabCheckUtil {
    Map<Integer, LabCheck> labCheckMap;

    public LabCheckUtilImpl(){
        labCheckMap = new HashMap<>();
    }

    @Override
    public List<LabCheck> fetchLabCheckPlans() {
        LabCheckDAO labCheckDao = new LabCheckDAOImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckup_id(), labCheck);
        return labCheckList;
    }

    @Override
    public void fetchDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.print(ScreenFields.HEALTH_CHECK_NUMBER + CommonConstants.COMMON_TEXT_SEPARATOR);
        int checkup_id = sc.nextInt();
        while(!labCheckMap.containsKey(checkup_id)) {
            System.out.println(CommonErrors.invalidCheckUpId+CommonConstants.COMMON_TEXT_SEPARATOR);
            checkup_id = sc.nextInt();
        }
        System.out.println("------------ "+"Details of "+labCheckMap.get(checkup_id).getCheckup_name() + " ------------");
        System.out.println("Description : "+labCheckMap.get(checkup_id).getDescription());
        System.out.println("Charges : "+labCheckMap.get(checkup_id).getCharges());
    }

    @Override
    public Map<Integer, LabCheck> fetchLabCheckMap(){
        if(labCheckMap.isEmpty()) {
            fetchLabCheckPlans();
        }
        return labCheckMap;
    }
}
