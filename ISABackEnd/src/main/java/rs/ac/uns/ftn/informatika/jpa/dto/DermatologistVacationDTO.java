package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Status;

public class DermatologistVacationDTO {

	private Long vacationId;
	private Long dermatologistId;
	private String firstName;
	private String lastName;
	private String email;
	private String startDate;
	private String endDate;
	private Status status;

	public DermatologistVacationDTO() {
	}
	
	

	public DermatologistVacationDTO(Long vacationId, Long dermatologistId, String firstName, String lastName,
			String email, String startDate, String endDate, Status status) {
		super();
		this.vacationId = vacationId;
		this.dermatologistId = dermatologistId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}



	public Long getVacationId() {
		return vacationId;
	}

	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}

	public Long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(Long dermatologistId) {
		this.dermatologistId = dermatologistId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}