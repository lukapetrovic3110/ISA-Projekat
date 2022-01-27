package rs.ac.uns.ftn.informatika.jpa.dto;

public class PharmacyDTO {
	private String name;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private Double longitude;
	private Double latitude;
	private double rating;
	private String description;
	
	public PharmacyDTO() {}
	
	public PharmacyDTO(String name, String street, String streetNumber, String city, String country, Double longitude,
			Double latitude, double rating, String description) {
		super();
		this.name = name;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rating = rating;
		this.description = description;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PharmacyDTO [name=" + name + ", street=" + street + ", streetNumber=" + streetNumber + ", city=" + city
				+ ", country=" + country + ", longitude=" + longitude + ", latitude=" + latitude + ", rating=" + rating
				+ ", description=" + description + "]";
	}
	
}