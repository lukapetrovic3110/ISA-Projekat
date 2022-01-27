package rs.ac.uns.ftn.informatika.jpa.dto;

public class PharmacyDermatologistDTO {
	
	private Long dermatologistId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String street;
	private String homeNumber;
	private String city;
	private double rating;
	private String pharmacyNames;
	
	public PharmacyDermatologistDTO() {
		
	}
	

	public PharmacyDermatologistDTO(Long dermatologistId, String firstName, String lastName, String phoneNumber,
			String email, String street, String homeNumber, String city, double rating, String pharmacyNames) {
		super();
		this.dermatologistId = dermatologistId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.street = street;
		this.homeNumber = homeNumber;
		this.city = city;
		this.rating = rating;
		this.pharmacyNames = pharmacyNames;
	}




	public Long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(Long dermatologistId) {
		this.dermatologistId = dermatologistId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPharmacyNames() {
		return pharmacyNames;
	}

	public void setPharmacyNames(String pharmacyNames) {
		this.pharmacyNames = pharmacyNames;
	}
	
	
}
