package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrderDTO {
	private String pharmacyAdminId;
	private Long pharmacyId;
	private Date offerDeadline;
	// medicineIds - keys1
	private List<Long> keys1 = new ArrayList<Long>();
	// quantity - values1
	private List<Integer> values1 = new ArrayList<Integer>();
	// newMedicineIds - keys2
	private List<Long> keys2 = new ArrayList<Long>();
	// quantity - values2
	private List<Integer> values2 = new ArrayList<Integer>();
	
	public CreateOrderDTO() {
		
	}

	public CreateOrderDTO(String pharmacyAdminId, Long pharmacyId, Date offerDeadline, List<Long> keys1,
			List<Integer> values1, List<Long> keys2, List<Integer> values2) {
		super();
		this.pharmacyAdminId = pharmacyAdminId;
		this.pharmacyId = pharmacyId;
		this.offerDeadline = offerDeadline;
		this.keys1 = keys1;
		this.values1 = values1;
		this.keys2 = keys2;
		this.values2 = values2;
	}

	public String getPharmacyAdminId() {
		return pharmacyAdminId;
	}

	public void setPharmacyAdminId(String pharmacyAdminId) {
		this.pharmacyAdminId = pharmacyAdminId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Date getOfferDeadline() {
		return offerDeadline;
	}

	public void setOfferDeadline(Date offerDeadline) {
		this.offerDeadline = offerDeadline;
	}

	public List<Long> getKeys1() {
		return keys1;
	}

	public void setKeys1(List<Long> keys1) {
		this.keys1 = keys1;
	}

	public List<Integer> getValues1() {
		return values1;
	}

	public void setValues1(List<Integer> values1) {
		this.values1 = values1;
	}

	public List<Long> getKeys2() {
		return keys2;
	}

	public void setKeys2(List<Long> keys2) {
		this.keys2 = keys2;
	}

	public List<Integer> getValues2() {
		return values2;
	}

	public void setValues2(List<Integer> values2) {
		this.values2 = values2;
	}
	
}
