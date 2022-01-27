package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Supplier extends User {

	private static final long serialVersionUID = -546832308000747395L;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<MedicineItem> medicineItem;
	
	public Supplier() {
		
	}

	public Supplier(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, Long userId, UserType userType, Address address, Set<MedicineItem> medItem, Boolean enabled, Date lastResetPasswordDate, Boolean firstLogin) {
		super(firstName, lastName, password, email, phoneNumber, enabled, userId, userType, address, lastResetPasswordDate, firstLogin);
		this.medicineItem = new HashSet<MedicineItem>();
		
	}
	
	public Supplier(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, UserType userType, Address address) {
		super(firstName, lastName, userName, password, email, phoneNumber, userType, address);
		// TODO Auto-generated constructor stub
	}

	public Set<MedicineItem> getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(Set<MedicineItem> medicineItem) {
		this.medicineItem = medicineItem;
	}
	
}