package rs.ac.uns.ftn.informatika.jpa.dto;

public class PharmacistDTO {
	
	private String firstName;
	private String lastName;
	private double rating;
	private String pharmacyName;
	
	public PharmacistDTO() {}

	public PharmacistDTO(String firstName, String lastName, double rating, String pharmacyName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.pharmacyName = pharmacyName;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	

}
