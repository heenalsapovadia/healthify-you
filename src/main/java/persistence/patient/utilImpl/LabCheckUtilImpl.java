package persistence.patient.utilImpl;

import persistence.patient.model.LabCheck;
import persistence.patient.util.LabCheckUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 *
 */
public class LabCheckUtilImpl implements LabCheckUtil {

    public LabCheckUtilImpl(){}

    @Override
    public Map<Integer, LabCheck> fetchLabCheckMap(List<LabCheck> labCheckList) {
        Map<Integer, LabCheck> labCheckMap = new HashMap<>();
        for(LabCheck labCheck : labCheckList)
            labCheckMap.put(labCheck.getCheckupId(), labCheck);
        return labCheckMap;
    }

}
