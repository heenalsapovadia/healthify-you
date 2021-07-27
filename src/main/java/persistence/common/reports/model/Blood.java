package persistence.common.reports.model;

import java.sql.Date;

/**
 * @author Heenal Sapovadia
 *
 */
public class Blood {
    private CBC cbcPanel;
    private Date date;
    private Date dateOfCollection;

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

	public Date getDateOfCollection() {
		return dateOfCollection;
	}

	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}

}
