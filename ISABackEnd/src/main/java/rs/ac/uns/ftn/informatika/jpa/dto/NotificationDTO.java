package rs.ac.uns.ftn.informatika.jpa.dto;

public class NotificationDTO {
	
	private String content;
	private Long pharmacyId;
	
	public NotificationDTO() {}
	
	public NotificationDTO(String content, Long pharmacyId) {
		super();
		this.content = content;
		this.pharmacyId = pharmacyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

}
