package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;

public class ConsultationDTO {
	
	private String pharmacyId;
	private String date;
	private String time;
	private Pharmacist pharmacist;
	
	public ConsultationDTO(String pharmacyId, String date, String time, Pharmacist pharmacist) {
		super();
		this.pharmacyId = pharmacyId;
		this.date = date;
		this.time = time;
		this.pharmacist = pharmacist;
	}
	
	public ConsultationDTO(String pharmacyId, String date, String time) {
		super();
		this.pharmacyId = pharmacyId;
		this.date = date;
		this.time = time;
	}
	
	public ConsultationDTO() {
		
	}

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	
	
}
