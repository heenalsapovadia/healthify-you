package persistence.patient.util;

import persistence.patient.model.LabCheck;

import java.util.List;

public interface LabCheckUtil {
    List<LabCheck> fetchLabCheckPlans();
}
