package rs.ac.uns.ftn.informatika.jpa.dto;

public class RequestDeclineDTO {
	
	private Long vacationId;
	private String explanation;
	
	public RequestDeclineDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getVacationId() {
		return vacationId;
	}

	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
}
