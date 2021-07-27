package persistence.common.reports.model;

import java.sql.Date;

/**
 * @author Heenal Sapovadia
 *
 */
public class Vision {
    private String acuity;
    private Date date;
    private Date dateOfCollection;

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

	public Date getDateOfCollection() {
		return dateOfCollection;
	}

	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}
}
