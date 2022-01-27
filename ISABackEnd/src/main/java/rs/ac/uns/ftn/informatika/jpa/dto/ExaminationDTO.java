package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class ExaminationDTO {
	
	private Long appointmentId;
	
    private String dateAndTime;
	
    private int duration;
	
    private Double price;
	
    private int points;
   
	public Dermatologist dermatologist;
	
	public Patient patient;
	
	public Pharmacy pharmacy;


	public ExaminationDTO(Long appointmentId, String dateAndTime, int duration, Double price, int points,
			Dermatologist dermatologist, Patient patient, Pharmacy pharmacy) {
		super();
		this.appointmentId = appointmentId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.points = points;
		this.dermatologist = dermatologist;
		this.patient = patient;
		this.pharmacy = pharmacy;
	}
	
	public ExaminationDTO() {}

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

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
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
