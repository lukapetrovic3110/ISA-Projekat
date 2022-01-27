package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Penalty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long penaltyId;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	@Enumerated(EnumType.STRING)
    public PenaltyType penaltyType;
	
	@Column(name = "deleted")
	private boolean deleted;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	public Pharmacy pharmacy;

	public Penalty(String date, PenaltyType penaltyType, Pharmacy pharmacy) {
		super();
		this.date = date;
		this.penaltyType = penaltyType;
		this.pharmacy = pharmacy;
		this.deleted = false;
	}
	
	public Penalty() {
		
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PenaltyType getPenaltyType() {
		return penaltyType;
	}

	public void setPenaltyType(PenaltyType penaltyType) {
		this.penaltyType = penaltyType;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Long getPenaltyId() {
		return penaltyId;
	}

	public void setPenaltyId(Long penaltyId) {
		this.penaltyId = penaltyId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
