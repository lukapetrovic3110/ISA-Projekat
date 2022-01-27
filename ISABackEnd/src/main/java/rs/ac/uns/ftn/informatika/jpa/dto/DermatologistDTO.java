package rs.ac.uns.ftn.informatika.jpa.dto;

public class DermatologistDTO {
	private String firstName;
	private String lastName;
	private double rating;
	private String pharmacyNames;
	
	public DermatologistDTO() {}
	
	
	public DermatologistDTO(String firstName, String lastName, double rating) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
	}
	
	public DermatologistDTO(String firstName, String lastName, double rating, String pharmacyNames) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.pharmacyNames = pharmacyNames;
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

	public String getPharmacyNames() {
		return pharmacyNames;
	}

	public void setPharmacyNames(String pharmacyNames) {
		this.pharmacyNames = pharmacyNames;
	}
	
}
