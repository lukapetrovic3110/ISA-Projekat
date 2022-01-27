package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class RegistrationRequest {

	private Long id;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Address address;
	
	private String phoneNumber;
	
	private Pharmacy pharmacy;
	
	private Double rating;
	
	private Allergy allergy;
	
	public RegistrationRequest() {}
	
	public RegistrationRequest(Long id, String email, String password, String firstName, String lastName,
			String phoneNumber, Address address) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new Address(address.getCity(), address.getCountry(), address.getStreet(), address.getStreetNumber(), null, null);
		this.phoneNumber = phoneNumber;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Allergy getAllergy() {
		return allergy;
	}

	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}
	
	
}
