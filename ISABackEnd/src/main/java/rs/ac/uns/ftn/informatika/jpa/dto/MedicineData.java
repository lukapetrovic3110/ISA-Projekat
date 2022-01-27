package rs.ac.uns.ftn.informatika.jpa.dto;

public class MedicineData {
	
	private Long medicineId;
	private int quantity;
	
	public MedicineData() {
		// TODO Auto-generated constructor stub
	}
	

	public MedicineData(Long medicineId, int quantity) {
		super();
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
