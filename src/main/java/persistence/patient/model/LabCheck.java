package persistence.patient.model;

public class LabCheck {
    int checkup_id;
    String checkup_name;
    String checkup_type;
    String description;

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
}
