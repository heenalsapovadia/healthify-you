package persistence.patient.dao;

import persistence.patient.model.LabCheck;
import java.util.List;

public interface LabCheckDAO {
    List<LabCheck> getAvailablePlans();
}
