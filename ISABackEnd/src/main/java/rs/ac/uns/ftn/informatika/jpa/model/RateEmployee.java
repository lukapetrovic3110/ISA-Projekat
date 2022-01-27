package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class RateEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rateId;
	
	private int rating;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Patient patient;
	
	@OneToOne(fetch = FetchType.LAZY)
    public PharmacyEmployee pharmacyEmployee;
   
   public RateEmployee(Long rateId, int rating, Patient patient, PharmacyEmployee pharmacyEmployee) {
		super();
		this.rateId = rateId;
		this.rating = rating;
		this.patient = patient;
		this.pharmacyEmployee = pharmacyEmployee;
   }
   
   public RateEmployee()
   {
   }
	
	public Long getRateId() {
		return rateId;
	}
	
	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public PharmacyEmployee getPharmacyEmployee() {
		return pharmacyEmployee;
	}
	
	public void setPharmacyEmployee(PharmacyEmployee pharmacyEmployee) {
		this.pharmacyEmployee = pharmacyEmployee;
	}

}