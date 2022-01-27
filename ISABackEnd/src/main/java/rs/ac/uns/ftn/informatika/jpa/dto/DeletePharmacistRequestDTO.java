package rs.ac.uns.ftn.informatika.jpa.dto;

public class DeletePharmacistRequestDTO {
	
	private Long deletePharmacistId;

	public DeletePharmacistRequestDTO() {
		// TODO Auto-generated constructor stub
	}	
	
	public DeletePharmacistRequestDTO(Long deletePharmacistId) {
		super();
		this.deletePharmacistId = deletePharmacistId;
	}

	public Long getDeletePharmacistId() {
		return deletePharmacistId;
	}

	public void setDeletePharmacistId(Long deletePharmacistId) {
		this.deletePharmacistId = deletePharmacistId;
	}

}
