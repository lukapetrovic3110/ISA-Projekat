package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.Date;

public class CreateWorkSchedulePharmacistDTO {
	
	private Long pharmacistId;
	
	private Long pharmacyId;
	
	private Date startValidFor;
	
	private Date endValidFor;
	
	private String shift;
	
	public CreateWorkSchedulePharmacistDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public CreateWorkSchedulePharmacistDTO(Long pharmacistId, Long pharmacyId, Date startValidFor, Date endValidFor,
			String shift) {
		super();
		this.pharmacistId = pharmacistId;
		this.pharmacyId = pharmacyId;
		this.startValidFor = startValidFor;
		this.endValidFor = endValidFor;
		this.shift = shift;
	}


	public Long getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(Long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Date getStartValidFor() {
		return startValidFor;
	}

	public void setStartValidFor(Date startValidFor) {
		this.startValidFor = startValidFor;
	}

	public Date getEndValidFor() {
		return endValidFor;
	}

	public void setEndValidFor(Date endValidFor) {
		this.endValidFor = endValidFor;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

}
