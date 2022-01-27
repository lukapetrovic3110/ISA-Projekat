package rs.ac.uns.ftn.informatika.jpa.dto;

public class AnswerOnComplaintDTO {

	public String textAnswer;
	public Long complaintEmployeeId;
	public Long systemAdminId;
	
	public AnswerOnComplaintDTO() {}
	
	public AnswerOnComplaintDTO(String textAnswer, Long complaintEmployeeId, Long systemAdminId) {
		super();
		this.textAnswer = textAnswer;
		this.complaintEmployeeId = complaintEmployeeId;
		this.systemAdminId = systemAdminId;
	}
	

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public Long getComplaintEmployeeId() {
		return complaintEmployeeId;
	}

	public void setComplaintEmployeeId(Long complaintEmployeeId) {
		this.complaintEmployeeId = complaintEmployeeId;
	}

	public Long getSystemAdminId() {
		return systemAdminId;
	}

	public void setSystemAdminId(Long systemAdminId) {
		this.systemAdminId = systemAdminId;
	}
	
}
