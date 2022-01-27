package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCategory;

public class LoyaltyScaleDTO {

	private int from;
	private int to;
	private LoyaltyCategory loyaltyCategory;
	
	public LoyaltyScaleDTO() {}
	
	public LoyaltyScaleDTO(int from, int to, LoyaltyCategory loyaltyCategory) {
		super();
		this.from = from;
		this.to = to;
		this.loyaltyCategory = loyaltyCategory;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public LoyaltyCategory getLoyaltyCategory() {
		return loyaltyCategory;
	}

	public void setLoyaltyCategory(LoyaltyCategory loyaltyCategory) {
		this.loyaltyCategory = loyaltyCategory;
	}
	
}
