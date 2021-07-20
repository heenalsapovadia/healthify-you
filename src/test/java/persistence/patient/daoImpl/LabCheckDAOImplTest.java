package persistence.patient.daoImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckDAO;
import persistence.patient.model.LabCheck;
import java.util.List;
import static org.junit.Assert.*;

public class LabCheckDAOImplTest {

    @Test
    public void getAvailablePlans() {
        LabCheckDAO labCheckDao = new LabCheckDAOImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        assertEquals(10, labCheckList.size());
    }
}