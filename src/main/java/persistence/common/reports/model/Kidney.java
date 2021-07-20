package persistence.common.reports.model;

import java.sql.Date;

public class Kidney {
    float creatinine;
    int bun;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(float creatinine) {
        this.creatinine = creatinine;
    }

    public int getBun() {
        return bun;
    }

    public void setBun(int bun) {
        this.bun = bun;
    }
}
