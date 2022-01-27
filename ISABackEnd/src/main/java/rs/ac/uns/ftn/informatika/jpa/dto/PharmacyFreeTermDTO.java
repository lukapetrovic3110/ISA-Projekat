package rs.ac.uns.ftn.informatika.jpa.dto;

public class PharmacyFreeTermDTO {
	
	private Long appointmentId;
	
    private String dateAndTime;
	
    private int duration;
	
    private Double price;
    
    private Long dermatologistId;
   
    private String dermatologistName;
	
	private String dermatologistEmail;
	
	private String dermatologistPhone;
	
	public PharmacyFreeTermDTO() {
		
	}

	public PharmacyFreeTermDTO(Long appointmentId, String dateAndTime, int duration, Double price, Long dermatologistId,
			String dermatologistName, String dermatologistEmail, String dermatologistPhone) {
		super();
		this.appointmentId = appointmentId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.dermatologistId = dermatologistId;
		this.dermatologistName = dermatologistName;
		this.dermatologistEmail = dermatologistEmail;
		this.dermatologistPhone = dermatologistPhone;
	}



	public String getDermatologistPhone() {
		return dermatologistPhone;
	}



	public void setDermatologistPhone(String dermatologistPhone) {
		this.dermatologistPhone = dermatologistPhone;
	}



	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(Long dermatologistId) {
		this.dermatologistId = dermatologistId;
	}

	public String getDermatologistName() {
		return dermatologistName;
	}

	public void setDermatologistName(String dermatologistName) {
		this.dermatologistName = dermatologistName;
	}

	public String getDermatologistEmail() {
		return dermatologistEmail;
	}

	public void setDermatologistEmail(String dermatologistEmail) {
		this.dermatologistEmail = dermatologistEmail;
	}
	
}
