package rs.ac.uns.ftn.informatika.jpa.dto;

public class DeleteDermatologistRequestDTO {
	
	private Long deleteDermatologistId;
	
	public DeleteDermatologistRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DeleteDermatologistRequestDTO(Long deleteDermatologistId) {
		super();
		this.deleteDermatologistId = deleteDermatologistId;
	}

	public Long getDeleteDermatologistId() {
		return deleteDermatologistId;
	}

	public void setDeleteDermatologistId(Long deleteDermatologistId) {
		this.deleteDermatologistId = deleteDermatologistId;
	}
	
}
