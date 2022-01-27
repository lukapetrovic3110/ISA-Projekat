package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Dermatologist extends PharmacyEmployee {

	private static final long serialVersionUID = -2667848795545490514L;

	@ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	public Set<Pharmacy> pharmacy;

	public Dermatologist(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, UserType userType, Address address, Double rating) {
		super(firstName, lastName, userName, password, email, phoneNumber, userType, address, rating);
		this.pharmacy = new HashSet<Pharmacy>();
	}

	public Dermatologist() {

	}

	public Set<Pharmacy> getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Set<Pharmacy> pharmacy) {
		this.pharmacy = pharmacy;
	}

}