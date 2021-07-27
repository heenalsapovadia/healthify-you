package persistence.patient.util;

import persistence.patient.model.LabCheck;
import java.util.List;
import java.util.Map;

public interface LabCheckUtil {

    Map<Integer, LabCheck> fetchLabCheckMap(List<LabCheck> labCheckList);

}
