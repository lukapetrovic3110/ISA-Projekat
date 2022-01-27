package rs.ac.uns.ftn.informatika.jpa.dto;


public class PatientAppointmentDTO {

	private Long patientId;
	private String firstName;
	private String lastName;
	private String dateAndTime;
	
	public PatientAppointmentDTO() {}

	public PatientAppointmentDTO(Long patientId, String firstName, String lastName, String dateAndTime) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateAndTime = dateAndTime;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
}