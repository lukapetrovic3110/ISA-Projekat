package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public class ReservationViewDTO {

	private Long reservationId;
	private MedicineItem medicineItem;
	private Pharmacy pharmacy;
	private String date;
	
	public ReservationViewDTO(MedicineItem medicineItem, Pharmacy pharmacy, String date, Long reservationId) {
		super();
		this.medicineItem = medicineItem;
		this.pharmacy = pharmacy;
		this.date = date;
		this.reservationId = reservationId;
	}
	
	public ReservationViewDTO() {
		
	}

	public MedicineItem getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(MedicineItem medicineItem) {
		this.medicineItem = medicineItem;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
}
