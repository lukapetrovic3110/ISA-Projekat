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
public class RateMedicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rateMedicineId;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Patient patient;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Medicine medicine;
   
   public RateMedicine(Long rateMedicineId, int rating, Patient patient, Medicine medicine) {
		super();
		this.rateMedicineId = rateMedicineId;
		this.rating = rating;
		this.patient = patient;
		this.medicine = medicine;
   }
   
   public RateMedicine()
   {
   }

	public Long getRateMedicineId() {
		return rateMedicineId;
	}
	
	public void setRateMedicineId(Long rateMedicineId) {
		this.rateMedicineId = rateMedicineId;
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
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}