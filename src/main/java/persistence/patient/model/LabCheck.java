package persistence.patient.model;

/**
 * <pre>
 * Model class to load Lab Check Plans from database.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class LabCheck {
    private int checkupId;
    private String checkupName;
    private String checkupType;
    private String description;
    private double charges;

    public int getCheckupId() {
        return checkupId;
    }

    public void setCheckupId(int checkupId) {
        this.checkupId = checkupId;
    }

    public String getCheckupName() {
        return checkupName;
    }

    public void setCheckupName(String checkupName) {
        this.checkupName = checkupName;
    }

    public String getCheckupType() {
        return checkupType;
    }

    public void setCheckupType(String checkupType) {
        this.checkupType = checkupType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }
}
