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


@Entity
public class AnswerEmployee {
   	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answEmpId;
	
	@Column(name = "textAnswer", nullable = false)
	private String textAnswer;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public ComplaintEmployee complaintEmployee;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public SystemAdministrator systemAdministrator;
	
   
    public AnswerEmployee(String textAnswer, Long answEmpId, ComplaintEmployee complaintEmployee, SystemAdministrator systemAdministrator) {
		super();
		this.textAnswer = textAnswer;
		this.answEmpId = answEmpId;
		this.complaintEmployee = complaintEmployee;
		this.systemAdministrator = systemAdministrator;
	}
    
    public AnswerEmployee()
    {
    	
    }
	
	public String getTextEmp() {
			return textAnswer;
		}
	
	public void setTextEmp(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public Long getAnswEmpId() {
		return answEmpId;
	}

	public void setAnswEmpId(Long answEmpId) {
		this.answEmpId = answEmpId;
	}

	public ComplaintEmployee getComplaintEmployee() {
		return complaintEmployee;
	}

	public void setComplaintEmployee(ComplaintEmployee complaintEmployee) {
		this.complaintEmployee = complaintEmployee;
	}

	public SystemAdministrator getSystemAdministrator() {
		return systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}
	
}