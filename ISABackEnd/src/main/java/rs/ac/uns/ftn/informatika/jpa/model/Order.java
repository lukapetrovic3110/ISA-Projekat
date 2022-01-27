package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
	@Column(name = "offerDeadline", nullable = false)
	private Date offerDeadline;
	
	@Enumerated(EnumType.STRING)
	public OrderStatus orderStatus;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   
	public Set<MedicineItem> medicineItem;   

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   
    public Pharmacy pharmacy;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public PharmacyAdministrator pharmacyAdministrator;
	   
	public Order(Date offerDeadline, Long orderId, OrderStatus orderStatus, Pharmacy pharmacy,
			PharmacyAdministrator pharmacyAdministrator) {
		super();
		this.offerDeadline = offerDeadline;
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.pharmacy = pharmacy;
		this.pharmacyAdministrator = pharmacyAdministrator;
		this.medicineItem = new HashSet<MedicineItem>();
	}
	
	public Order()
	{
		
	}
	
	public Set<MedicineItem> getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(Set<MedicineItem> medicineItem) {
		this.medicineItem = medicineItem;
	}
	
	public Date getOfferDeadline() {
		return offerDeadline;
	}
	
	public void setOfferDeadline(Date offerDeadline) {
		this.offerDeadline = offerDeadline;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public PharmacyAdministrator getPharmacyAdministrator() {
		return pharmacyAdministrator;
	}
	
	public void setPharmacyAdministrator(PharmacyAdministrator pharmacyAdministrator) {
		this.pharmacyAdministrator = pharmacyAdministrator;
	}
	  
}