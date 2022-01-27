package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class PharmacistVacation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vacation_id", unique = true, nullable = false)
    private Long vacationId;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
   
	@Column(name = "status", nullable = false)
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    public Pharmacist pharmacist;
	
	public PharmacistVacation() {
		
	}

	public PharmacistVacation(Long vacationId, Date startDate, Date endDate, Status status, Pharmacist pharmacist) {
		super();
		this.vacationId = vacationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.pharmacist = pharmacist;
	}

	public Long getVacationId() {
		return vacationId;
	}

	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	@Override
	public String toString() {
		return "PharmacistVacation [vacationId=" + vacationId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", pharmacist=" + pharmacist + "]";
	}
	
}