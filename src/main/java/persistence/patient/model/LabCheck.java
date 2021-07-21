package persistence.patient.model;

public class LabCheck {
    private int checkup_id;
    private String checkup_name;
    private String checkup_type;
    private String description;
    private double charges;

    public int getCheckup_id() {
        return checkup_id;
    }

    public void setCheckup_id(int checkup_id) {
        this.checkup_id = checkup_id;
    }

    public String getCheckup_name() {
        return checkup_name;
    }

    public void setCheckup_name(String checkup_name) {
        this.checkup_name = checkup_name;
    }

    public String getCheckup_type() {
        return checkup_type;
    }

    public void setCheckup_type(String checkup_type) {
        this.checkup_type = checkup_type;
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
