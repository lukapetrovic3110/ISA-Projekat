package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCategory;

public class LoyaltyCardDTO {
	public int points;
	public LoyaltyCategory loyaltyCategory;
	
	public LoyaltyCategory getloyaltyCategory() {
		return loyaltyCategory;
	}
	public void setloyaltyCategory(LoyaltyCategory loyaltyCategory) {
		this.loyaltyCategory = loyaltyCategory;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public LoyaltyCardDTO(int points, LoyaltyCategory loyaltyCategory) {
		super();
		this.points = points;
		this.loyaltyCategory = loyaltyCategory;
	}
	public LoyaltyCardDTO() {

	}
	
	
}
