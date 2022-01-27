package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pharmacy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pharmacy_id", unique = true, nullable = false)
	private Long pharmacyId;

	@Column(name = "name")
	protected String name;

	@Column(name = "rating")
	protected Double rating;

	@Column(name = "description")
	protected String description;
	
	@Column(name = "consultation_price")
	protected Double consultationPrice;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	public Set<MedicineItem> medicineItem;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Dermatologist> dermatologist;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public Set<PharmacyAdministrator> phAdministrators;
	
	@OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Promotion> promotions;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PriceTag> priceTags;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "promotion_notification", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "pharmacy_id"),
	inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "user_id"))
	private List<Patient> patients;
	
	public Pharmacy() {}

	public Pharmacy(Long pharmacyId, String name, Double rating) {
		super();
		this.pharmacyId = pharmacyId;
		this.name = name;
		this.rating = rating;
		this.phAdministrators = new HashSet<PharmacyAdministrator>();
		this.medicineItem = new HashSet<MedicineItem>();
	}
	

	public Pharmacy(Long pharmacyId, String name, Double rating, String description, Double consultationPrice,
			Set<MedicineItem> medicineItem, Set<Dermatologist> dermatologist,
			Set<PharmacyAdministrator> phAdministrators, List<Promotion> promotions, Address address,
			List<Patient> patients) {
		super();
		this.pharmacyId = pharmacyId;
		this.name = name;
		this.rating = rating;
		this.description = description;
		this.consultationPrice = consultationPrice;
		this.medicineItem = medicineItem;
		this.dermatologist = dermatologist;
		this.phAdministrators = phAdministrators;
		this.promotions = promotions;
		this.address = address;
		this.patients = patients;
	}

	public Pharmacy(String name, Double rating) {
		super();
		this.name = name;
		this.rating = rating;
		this.phAdministrators = new HashSet<PharmacyAdministrator>();
		this.medicineItem = new HashSet<MedicineItem>();
	}

	public Set<PharmacyAdministrator> getPhAdministrators() {
		return phAdministrators;
	}

	public void setPhAdministrators(Set<PharmacyAdministrator> phAdministrators) {
		this.phAdministrators = phAdministrators;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<MedicineItem> getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(Set<MedicineItem> medicineItem) {
		this.medicineItem = medicineItem;
	}

	public Double getConsultationPrice() {
		return consultationPrice;
	}

	public void setConsultationPrice(Double consultationPrice) {
		this.consultationPrice = consultationPrice;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Set<Dermatologist> getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Set<Dermatologist> dermatologist) {
		this.dermatologist = dermatologist;
	}

	public List<PriceTag> getPriceTags() {
		return priceTags;
	}

	public void setPriceTags(List<PriceTag> priceTags) {
		this.priceTags = priceTags;
	}
}