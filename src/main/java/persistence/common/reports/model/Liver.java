package persistence.common.reports.model;

import java.sql.Date;

/**
 * @author Heenal Sapovadia
 *
 */
public class Liver {
    private int alt;
    private int ast;
    private int alp;
    private float albumin;
    private float bilirubin;
    private Date date;
    private Date dateOfCollection;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public int getAlp() {
        return alp;
    }

    public void setAlp(int alp) {
        this.alp = alp;
    }

    public float getAlbumin() {
        return albumin;
    }

    public void setAlbumin(float albumin) {
        this.albumin = albumin;
    }

    public float getBilirubin() {
        return bilirubin;
    }

    public void setBilirubin(float bilirubin) {
        this.bilirubin = bilirubin;
    }

	public Date getDateOfCollection() {
		return dateOfCollection;
	}

	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}
}