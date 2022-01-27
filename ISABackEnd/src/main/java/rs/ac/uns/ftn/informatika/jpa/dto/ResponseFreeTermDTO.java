package rs.ac.uns.ftn.informatika.jpa.dto;

public class ResponseFreeTermDTO {
	
	private Boolean status;
	private String answer;
	
	public ResponseFreeTermDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ResponseFreeTermDTO(Boolean status, String answer) {
		super();
		this.status = status;
		this.answer = answer;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
