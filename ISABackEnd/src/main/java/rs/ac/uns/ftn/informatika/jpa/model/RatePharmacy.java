package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class RatePharmacy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratePhId;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Pharmacy pharmacy;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Patient patient;
   
	public RatePharmacy(Long ratePhId, int rating, Pharmacy pharmacy, Patient patient) {
		super();
		this.ratePhId = ratePhId;
		this.rating = rating;
		this.pharmacy = pharmacy;
		this.patient = patient;
    }
	
	public RatePharmacy()
	{
		
	}

	public Long getRatePhId() {
		return ratePhId;
	}
	
	public void setRatePhId(Long ratePhId) {
		this.ratePhId = ratePhId;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}