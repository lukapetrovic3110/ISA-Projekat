package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class PharmacyAvailabilityDTO {

	private Pharmacy pharmacy;
	private double sumPrice;
	
	public PharmacyAvailabilityDTO() {}
	
	public PharmacyAvailabilityDTO(Pharmacy pharmacy, double sumPrice) {
		super();
		this.pharmacy = pharmacy;
		this.sumPrice = sumPrice;
	}
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	
	
}
