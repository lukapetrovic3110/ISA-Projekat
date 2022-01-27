
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Consultation{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	
	@Column(name = "dateAndTime", nullable = false)
    private Date dateAndTime;
	
	@Column(name = "duration", nullable = false)
    private int duration;
	
	@Column(name = "price", nullable = false)
    private Double price;
    
	@Column(name = "cancelled", nullable = false)
	private Boolean cancelled;
	
	@Column(name = "points", nullable = false)
    private int points;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	public Patient patient;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	public Pharmacy pharmacy;
	
	@Enumerated(EnumType.STRING)
    public AppointmentStatus appointmentStatus;
   
   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
   public Pharmacist pharmacist;

 /*  public Consultation(Date dateAndTime, int duration, Double price, Long appointmentId, Boolean cancelled, int points,
		Patient patient, Shift shift, AppointmentStatus appointmentStatus, Pharmacist pharmacist) {
	super(dateAndTime, duration, price, appointmentId, cancelled, points, patient, shift, appointmentStatus);
	this.pharmacist = pharmacist;
    }*/
   
   
   public Consultation()
   {
	   
   }

	public Consultation(Long appointmentId, Date dateAndTime, int duration, Double price, Boolean cancelled, int points,
		Patient patient, Pharmacy pharmacy, AppointmentStatus appointmentStatus, Pharmacist pharmacist) {
	super();
	this.appointmentId = appointmentId;
	this.dateAndTime = dateAndTime;
	this.duration = duration;
	this.price = price;
	this.cancelled = cancelled;
	this.points = points;
	this.patient = patient;
	this.pharmacy = pharmacy;
	this.appointmentStatus = appointmentStatus;
	this.pharmacist = pharmacist;
}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}
	
	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	public Date getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Boolean getCancelled() {
		return cancelled;
	}
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
   
    
}