package rs.ac.uns.ftn.informatika.jpa.dto;

public class ResponseVacationDTO {
	
	private String dermatologistName;
	private String email;
	private String description;
	
	public ResponseVacationDTO() {
	
	}
		
	
	public ResponseVacationDTO(String dermatologistName, String email, String description) {
		super();
		this.dermatologistName = dermatologistName;
		this.email = email;
		this.description = description;
	}


	public String getDermatologistName() {
		return dermatologistName;
	}


	public void setDermatologistName(String dermatologistName) {
		this.dermatologistName = dermatologistName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
}
