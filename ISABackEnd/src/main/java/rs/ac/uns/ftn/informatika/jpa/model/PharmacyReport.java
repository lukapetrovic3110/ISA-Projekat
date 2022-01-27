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
public class PharmacyReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pharmacyRepId;
	
	@Column(name = "income", nullable = false)
    private Double income;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   
	private Pharmacy pharmacy;
	
    public PharmacyReport(Double income, Long pharmacyRepId) {
		super();
		this.income = income;
		this.pharmacyRepId = pharmacyRepId;
	}
    public PharmacyReport()
    {
    	
    }
    
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Long getPharmacyRepId() {
		return pharmacyRepId;
	}
	public void setPharmacyRepId(Long pharmacyRepId) {
		this.pharmacyRepId = pharmacyRepId;
	}
}