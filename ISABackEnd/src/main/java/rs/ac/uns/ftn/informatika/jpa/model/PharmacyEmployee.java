package rs.ac.uns.ftn.informatika.jpa.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PharmacyEmployee extends User {
	
	private static final long serialVersionUID = 583030615322985967L;
	
	@Column(name = "rating")
	protected Double rating;

	public PharmacyEmployee(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, Long userId, UserType userType, Address address, Boolean enabled, Date lastResetPasswordDate, Boolean firstLogin) {
		super(firstName, lastName, password, email, phoneNumber, enabled, userId, userType, address, lastResetPasswordDate, firstLogin);
	}

	public PharmacyEmployee(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, UserType userType, Address address, Double rating) {
		super(firstName, lastName, userName, password, email, phoneNumber, userType, address);
		this.rating = rating;
	}


	
	public PharmacyEmployee()
	{
		
	}
	
	public Double getRating() {
		return rating;
	}
	
	public void setRating(Double rating) {
		this.rating = rating;
	}

}