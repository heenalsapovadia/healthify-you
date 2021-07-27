package persistence.common.reports.model;

import java.sql.Date;

/**
 * @author Heenal Sapovadia
 *
 */
public class Kidney {
    private float creatinine;
    private int bun;
    private Date date;
    private Date dateOfCollection;

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

	public Date getDateOfCollection() {
		return dateOfCollection;
	}

	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}
}
