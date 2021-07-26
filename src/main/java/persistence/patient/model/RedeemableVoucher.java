package persistence.patient.model;

/**
 * <pre>
 * Model class to load vouchers from database.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class RedeemableVoucher {
	
	private String voucherId;
	private String bloodGroup;
	private int validityInDays;
	private double points;
	
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getValidityInDays() {
		return validityInDays;
	}
	public void setValidityInDays(int validityInDays) {
		this.validityInDays = validityInDays;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
}
