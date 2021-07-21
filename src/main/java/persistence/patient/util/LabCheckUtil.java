package persistence.patient.util;

import persistence.patient.model.LabCheck;
import java.util.List;
import java.util.Map;

public interface LabCheckUtil {
    List<LabCheck> fetchLabCheckPlans();

    void fetchDetails();

    Map<Integer, LabCheck> fetchLabCheckMap();
}
