/**
 * 
 */
package persistence.common.reports.model;

import java.sql.Date;

/**
 * @author Gurleen Saluja
 *
 */
public class Covid {
	
	private Date date;
	private Date dateOfCollection;
	private RtPcr rtPcr;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateOfCollection() {
		return dateOfCollection;
	}
	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}
	public RtPcr getRtPcr() {
		return rtPcr;
	}
	public void setRtPcr(RtPcr rtPcr) {
		this.rtPcr = rtPcr;
	}
}
