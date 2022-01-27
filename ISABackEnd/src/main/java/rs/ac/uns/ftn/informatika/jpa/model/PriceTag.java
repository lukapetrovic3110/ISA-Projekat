package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class PriceTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long priceTagId;

	@Column(name = "price", nullable = false)
	private Double price;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
	public TimeInterval timeInterval;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
	public Medicine medicine;
   
   public PriceTag(Double price, TimeInterval timeInterval, Medicine medicine) {
		super();
		this.price = price;
		this.timeInterval = timeInterval;
		this.medicine = medicine;
   	}
   
   public PriceTag()
   {
	   
   }

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	
	public void setTimeInterval(TimeInterval timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Long getPriceTagId() {
		return priceTagId;
	}

	public void setPriceTagId(Long priceTagId) {
		this.priceTagId = priceTagId;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}