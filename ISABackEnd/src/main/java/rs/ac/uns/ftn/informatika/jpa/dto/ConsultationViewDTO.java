package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class ConsultationViewDTO {

private Long appointmentId;
	
    private String dateAndTime;
	
    private int duration;
	
    private Double price;
	
    private int points;
   
	private Pharmacist pharmacist;
	
	private Patient patient;
	
	private Pharmacy pharmacy;

	public ConsultationViewDTO(Long appointmentId, String dateAndTime, int duration, Double price, int points,
			Pharmacist pharmacist, Patient patient, Pharmacy pharmacy) {
		super();
		this.appointmentId = appointmentId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.points = points;
		this.pharmacist = pharmacist;
		this.patient = patient;
		this.pharmacy = pharmacy;
	}
	
	public ConsultationViewDTO() {
		
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}	
	
}
