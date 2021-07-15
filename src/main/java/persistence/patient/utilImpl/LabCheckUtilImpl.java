package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckDao;
import persistence.patient.daoImpl.LabCheckDaoImpl;
import persistence.patient.model.LabCheck;
import persistence.patient.util.LabCheckUtil;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.CommonErrors;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LabCheckUtilImpl implements LabCheckUtil {
    HashMap<Integer, LabCheck> labCheckMap;

    @Override
    public List<LabCheck> fetchLabCheckPlans() {
        LabCheckDao labCheckDao = new LabCheckDaoImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        labCheckMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckup_id(), labCheck);
        return labCheckList;
    }

    @Override
    public void fetchDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.print(ScreenFields.checkId + CommonConstants.commonTextSeparator);
        int checkup_id = sc.nextInt();
        while(!labCheckMap.containsKey(checkup_id)) {
            System.out.println(CommonErrors.invalidCheckUpId+CommonConstants.commonTextSeparator);
            checkup_id = sc.nextInt();
        }
        System.out.println("Details of "+labCheckMap.get(checkup_id).getCheckup_name());
        System.out.println(labCheckMap.get(checkup_id).getDescription());
    }
}
