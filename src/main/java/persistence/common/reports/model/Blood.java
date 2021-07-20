package persistence.common.reports.model;

import java.sql.Date;

public class Blood {
    CBC cbcPanel;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CBC getCbcPanel() {
        return cbcPanel;
    }

    public void setCbcPanel(CBC cbcPanel) {
        this.cbcPanel = cbcPanel;
    }

}
