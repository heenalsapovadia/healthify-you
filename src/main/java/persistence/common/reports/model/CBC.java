package persistence.common.reports.model;

public class CBC {
    private float rbc;
    private int wbc;
    private int platelets;
    private double haemoglobin;
    private float hematocrit;

    public float getRbc() {
        return rbc;
    }

    public void setRbc(float rbc) {
        this.rbc = rbc;
    }

    public int getWbc() {
        return wbc;
    }

    public void setWbc(int wbc) {
        this.wbc = wbc;
    }

    public int getPlatelets() {
        return platelets;
    }

    public void setPlatelets(int platelets) {
        this.platelets = platelets;
    }

    public double getHaemoglobin() {
        return haemoglobin;
    }

    public void setHaemoglobin(double haemoglobin) {
        this.haemoglobin = haemoglobin;
    }

    public float getHematocrit() {
        return hematocrit;
    }

    public void setHematocrit(float hematocrit) {
        this.hematocrit = hematocrit;
    }
}
