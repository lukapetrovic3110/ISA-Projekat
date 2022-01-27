package rs.ac.uns.ftn.informatika.jpa.dto;

public class ComplaintEmployeeDTO {

	private String textEmp;
	
	private Long pharmacyEmployeeId;
		
	private Long patientId;

	public ComplaintEmployeeDTO() {}
	

	public ComplaintEmployeeDTO(String textEmp, Long pharmacyEmployeeId, Long patientId) {
		super();
		this.textEmp = textEmp;
		this.pharmacyEmployeeId = pharmacyEmployeeId;
		this.patientId = patientId;
	}



	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public String getTextEmp() {
		return textEmp;
	}

	public void setTextEmp(String textEmp) {
		this.textEmp = textEmp;
	}


	public Long getPharmacyEmployeeId() {
		return pharmacyEmployeeId;
	}


	public void setPharmacyEmployeeId(Long pharmacyEmployeeId) {
		this.pharmacyEmployeeId = pharmacyEmployeeId;
	}

}
