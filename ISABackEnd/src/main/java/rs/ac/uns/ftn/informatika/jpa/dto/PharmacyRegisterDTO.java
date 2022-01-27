package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class PharmacyRegisterDTO {
	
	private String name;
	
	private Double rating;
	
	private Address address;
		
	private String description;
	
	private Pharmacy pharmacy;
	
	private Double consultationPrice;
	
	public PharmacyRegisterDTO() {}
	
	public PharmacyRegisterDTO(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
	public PharmacyRegisterDTO(String name, Double rating, Address address, String description, Double consultationPrice) {
		super();
		this.name = name;
		this.rating = rating;
		this.address = new Address(address.getCity(), address.getCountry(), address.getStreet(), address.getStreetNumber(), null, null);
		this.description = description;
		this.consultationPrice = consultationPrice;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Double getConsultationPrice() {
		return consultationPrice;
	}

	public void setConsultationPrice(Double consultationPrice) {
		this.consultationPrice = consultationPrice;
	}
		
}
