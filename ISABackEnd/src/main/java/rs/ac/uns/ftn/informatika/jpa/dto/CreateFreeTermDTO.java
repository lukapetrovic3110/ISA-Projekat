package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class CreateFreeTermDTO {
	private Dermatologist dermatologist;
	private Pharmacy pharmacy;
	private Date dateAndTimeExamination;
	private String duration;
	private String price;

	public CreateFreeTermDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CreateFreeTermDTO(Dermatologist dermatologist, Pharmacy pharmacy, Date dateAndTimeExamination,
			String duration, String price) {
		super();
		this.dermatologist = dermatologist;
		this.pharmacy = pharmacy;
		this.dateAndTimeExamination = dateAndTimeExamination;
		this.duration = duration;
		this.price = price;
	}



	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Date getDateAndTimeExamination() {
		return dateAndTimeExamination;
	}

	public void setDateAndTimeExamination(Date dateAndTimeExamination) {
		this.dateAndTimeExamination = dateAndTimeExamination;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
