package persistence.patient.dao;

import persistence.patient.model.LabCheck;

import java.util.List;

public interface LabCheckDao {
    List<LabCheck> getAvailablePlans();
}
