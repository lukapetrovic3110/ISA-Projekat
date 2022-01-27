package rs.ac.uns.ftn.informatika.jpa.dto;

public class RecommendationDTO {
	
	private Long medicineId;
	private Long duration;
	
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public RecommendationDTO(Long medicineId, Long duration) {
		super();
		this.medicineId = medicineId;
		this.duration = duration;
	}

}
