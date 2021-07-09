package persistence.patient.dao;
import persistence.patient.model.BloodBankService;

import java.util.List;

public interface BloodBankServiceDAO {
    void insertBloodBankServiceDetails(List<BloodBankService> bloodBankServiceList);
}