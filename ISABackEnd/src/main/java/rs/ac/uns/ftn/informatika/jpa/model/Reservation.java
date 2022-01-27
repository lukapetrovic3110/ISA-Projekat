package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
	
	@Version
	private Long version;
	
	@Column(name = "reservationCode", nullable = false)
    private String reservationCode;
	
	@Column(name = "recieved", nullable = false)
    private Boolean recieved;
	
	@OneToOne(fetch = FetchType.EAGER)
    public Patient patient;
    
	@ManyToOne(fetch = FetchType.EAGER)
    public MedicineItem medicineItem;
    
	@Column(name = "deadline", nullable = false)
    private Date deadline;
	
	@Column(name = "cancelled", nullable = false)
    private Boolean cancelled;
	
	@Column(name = "penalty", nullable = false)
    private Boolean penalty;
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.DETACH)
	@JoinColumn(name = "pharmacy_pharmacy_id", referencedColumnName = "pharmacy_id")
	public Pharmacy pharmacy;
   
    public Reservation(Date deadline, String reservationCode, Boolean recieved, Patient patient,
    		MedicineItem item, Pharmacy pharmacy, Boolean cancelled) {
		super();
		this.deadline = deadline;
		this.reservationCode = reservationCode;
		this.recieved = recieved;
		this.patient = patient;
		this.medicineItem = item;
		this.pharmacy = pharmacy;
		this.cancelled = cancelled;
		this.penalty = false;
		this.version = (long) 0;
	}
    
    public Reservation() 
    {

	}

	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public Long getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
	public Boolean getRecieved() {
		return recieved;
	}
	
	public void setRecieved(Boolean recieved) {
		this.recieved = recieved;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public MedicineItem getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(MedicineItem medicineItem) {
		this.medicineItem = medicineItem;
	}

	public String getReservationCode() {
		return reservationCode;
	}
	

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Boolean getPenalty() {
		return penalty;
	}

	public void setPenalty(Boolean penalty) {
		this.penalty = penalty;
	}
	
}