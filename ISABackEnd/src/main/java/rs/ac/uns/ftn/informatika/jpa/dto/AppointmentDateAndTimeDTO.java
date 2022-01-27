package rs.ac.uns.ftn.informatika.jpa.dto;

public class AppointmentDateAndTimeDTO {

	private String date;
	private String time;
	
	public AppointmentDateAndTimeDTO(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}
	
	public AppointmentDateAndTimeDTO() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
