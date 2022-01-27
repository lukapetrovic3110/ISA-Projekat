package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;


@Entity
public class ComplaintEmployee {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long compEmpId;

	@Version
	private Long version;
	
	
	@Column(name = "textEmployee", nullable = false)
	private String textEmp;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Patient patient;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public PharmacyEmployee pharmacyEmployee;
	   
	public ComplaintEmployee(String textEmp, Long compEmpId, Patient patient, PharmacyEmployee pharmacyEmployee) {
		super();
		this.textEmp = textEmp;
		this.compEmpId = compEmpId;
		this.patient = patient;
		this.pharmacyEmployee = pharmacyEmployee;
	}
	
	public ComplaintEmployee()
	{
		
	}
	
	public String getTextEmp() {
		return textEmp;
	}
	
	public void setTextEmp(String textEmp) {
		this.textEmp = textEmp;
	}
	
	public Long getCompEmpId() {
		return compEmpId;
	}
	
	public void setCompEmpId(Long compEmpId) {
		this.compEmpId = compEmpId;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public PharmacyEmployee getPharmacyEmployee() {
		return pharmacyEmployee;
	}
	
	public void setPharmacyEmployee(PharmacyEmployee pharmacyEmployee) {
		this.pharmacyEmployee = pharmacyEmployee;
	}
	   

}