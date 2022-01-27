package rs.ac.uns.ftn.informatika.jpa.model;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Pharmacist extends PharmacyEmployee {
	private static final long serialVersionUID = -5330789200370606931L;
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_pharmacy_id", referencedColumnName = "pharmacy_id")
	public Pharmacy pharmacy;
	
	
	public Pharmacist(){}
	
	
	public Pharmacist(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, Long userId, UserType userType, Address address, Boolean enabled,
			Date lastResetPasswordDate, Boolean firstLogin, Pharmacy pharmacy) {
		super(firstName, lastName, userName, password, email, phoneNumber, userId, userType, address, enabled,
				lastResetPasswordDate, firstLogin);
		this.pharmacy = pharmacy;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
    
}