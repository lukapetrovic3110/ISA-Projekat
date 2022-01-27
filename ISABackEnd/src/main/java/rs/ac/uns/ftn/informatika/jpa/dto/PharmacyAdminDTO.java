package rs.ac.uns.ftn.informatika.jpa.dto;

public class PharmacyAdminDTO {
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private String pharmacyName;
	
	public PharmacyAdminDTO() {
		
		
	}
	
	public PharmacyAdminDTO(String email, String firstName, String lastName, String phoneNumber, String pharmacyName)
	{
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.pharmacyName = pharmacyName;
	}
	
	public PharmacyAdminDTO(String email, String firstName, String lastName, String phoneNumber, String street,
			String streetNumber, String city, String country, String pharmacyName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.pharmacyName = pharmacyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	@Override
	public String toString() {
		return "PharmacyAdminDTO [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", street=" + street + ", streetNumber=" + streetNumber + ", city="
				+ city + ", country=" + country + ", pharmacyName=" + pharmacyName + "]";
	}
	
}
