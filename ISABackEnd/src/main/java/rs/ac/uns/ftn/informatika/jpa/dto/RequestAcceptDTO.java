package rs.ac.uns.ftn.informatika.jpa.dto;

public class RequestAcceptDTO {
	private Long vacationId;
	
	public RequestAcceptDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestAcceptDTO(Long vacationId) {
		super();
		this.vacationId = vacationId;
	}

	public Long getVacationId() {
		return vacationId;
	}
	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}
	
}
