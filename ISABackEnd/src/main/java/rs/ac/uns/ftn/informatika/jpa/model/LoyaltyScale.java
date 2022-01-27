package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class LoyaltyScale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scaleId;
	
	@Column(name = "from", nullable = false)
    private int from;
	
	@Column(name = "to", nullable = false)
    private int to;   
	
	@Enumerated(EnumType.STRING)
    public LoyaltyCategory loyaltyCategory;
	   
	public LoyaltyScale(Long scaleId, int from, int to, LoyaltyCategory loyaltyCategory) {
		super();
		this.scaleId = scaleId;
		this.from = from;
		this.to = to;
		this.loyaltyCategory = loyaltyCategory;
	}
	
	public LoyaltyScale()
	{
		
	}
	
	public Long getScaleId() {
		return scaleId;
	}
	public void setScaleId(Long scaleId) {
		this.scaleId = scaleId;
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