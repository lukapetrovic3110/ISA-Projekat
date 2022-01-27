package rs.ac.uns.ftn.informatika.jpa.dto;

public class AnswerOnComplaintForPharmacyDTO {

	public String textAnswer;
	public Long complaintPharmacyId;
	public Long systemAdminId;
	
	public AnswerOnComplaintForPharmacyDTO(){}
	
	public AnswerOnComplaintForPharmacyDTO(String textAnswer, Long complaintPharmacyId, Long systemAdminId) {
		super();
		this.textAnswer = textAnswer;
		this.complaintPharmacyId = complaintPharmacyId;
		this.systemAdminId = systemAdminId;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public Long getComplaintPharmacyId() {
		return complaintPharmacyId;
	}

	public void setComplaintPharmacyId(Long complaintPharmacyId) {
		this.complaintPharmacyId = complaintPharmacyId;
	}

	public Long getSystemAdminId() {
		return systemAdminId;
	}

	public void setSystemAdminId(Long systemAdminId) {
		this.systemAdminId = systemAdminId;
	}
	
	
	
}
