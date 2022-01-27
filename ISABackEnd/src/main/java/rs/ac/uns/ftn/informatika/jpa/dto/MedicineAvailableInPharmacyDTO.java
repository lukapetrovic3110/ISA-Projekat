package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PriceTag;

public class MedicineAvailableInPharmacyDTO {

	private Pharmacy pharmacy;
	private PriceTag priceTag;
	
	public MedicineAvailableInPharmacyDTO(Pharmacy pharmacy,PriceTag priceTag) {
		super();
		this.pharmacy = pharmacy;
		this.priceTag = priceTag;
	}
	
	public MedicineAvailableInPharmacyDTO() {
		
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public PriceTag getPriceTag() {
		return priceTag;
	}

	public void setPriceTag(PriceTag priceTag) {
		this.priceTag = priceTag;
	}
	
	
}
