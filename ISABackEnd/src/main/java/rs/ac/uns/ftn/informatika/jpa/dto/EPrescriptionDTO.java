package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescriptionStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class EPrescriptionDTO {
	private String date;
	private Pharmacy pharmacy;
	private EPrescriptionStatus status;
	
	public EPrescriptionDTO(String date, Pharmacy pharmacy, EPrescriptionStatus status) {
		super();
		this.date = date;
		this.pharmacy = pharmacy;
		this.status = status;
	}
	
	public EPrescriptionDTO() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public EPrescriptionStatus getStatus() {
		return status;
	}

	public void setStatus(EPrescriptionStatus status) {
		this.status = status;
	}

}
