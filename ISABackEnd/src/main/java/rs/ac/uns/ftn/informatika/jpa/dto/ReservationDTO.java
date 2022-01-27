package rs.ac.uns.ftn.informatika.jpa.dto;

public class ReservationDTO {

	private MedicineAvailableInPharmacyDTO dto;
	private String date;
	private Long userId;
	
	
	public ReservationDTO(MedicineAvailableInPharmacyDTO dto, String date, Long userId) {
		super();
		this.dto = dto;
		this.date = date;
		this.userId = userId;
	}
	
	public ReservationDTO() {
		
	}


	public MedicineAvailableInPharmacyDTO getDto() {
		return dto;
	}


	public void setDto(MedicineAvailableInPharmacyDTO dto) {
		this.dto = dto;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
