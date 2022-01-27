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
public class Discount {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)   
   private Long discountId;
   
   @Enumerated(EnumType.STRING)
   public LoyaltyCategory loyaltyCategory;
   
   @Column(name = "percent", nullable = false)
   private int percent;
	   
   public Discount(int percent, Long discountId, LoyaltyCategory loyaltyCategory) {
		super();
		this.percent = percent;
		this.discountId = discountId;
		this.loyaltyCategory = loyaltyCategory;
	}
   
   public Discount()
   {
	   
   }
   
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public Long getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}
	public LoyaltyCategory getLoyaltyCategory() {
		return loyaltyCategory;
	}
	public void setLoyaltyCategory(LoyaltyCategory loyaltyCategory) {
		this.loyaltyCategory = loyaltyCategory;
	}
	   
	   

}