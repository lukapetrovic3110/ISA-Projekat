package rs.ac.uns.ftn.informatika.jpa.dto;

public class DeleteMedicineItemRequestDTO {
	
	private Long deleteMedicineItemId;
	
	public DeleteMedicineItemRequestDTO() {
		
	}
		
	public DeleteMedicineItemRequestDTO(Long deleteMedicineItemId) {
		super();
		this.deleteMedicineItemId = deleteMedicineItemId;
	}

	public Long getDeleteMedicineItemId() {
		return deleteMedicineItemId;
	}

	public void setDeleteMedicineItemId(Long deleteMedicineItemId) {
		this.deleteMedicineItemId = deleteMedicineItemId;
	}

}
