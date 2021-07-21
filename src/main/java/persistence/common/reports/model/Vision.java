package persistence.common.reports.model;

import java.sql.Date;

public class Vision {
    private String acuity;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAcuity() {
        return acuity;
    }

    public void setAcuity(String acuity) {
        this.acuity = acuity;
    }
}
