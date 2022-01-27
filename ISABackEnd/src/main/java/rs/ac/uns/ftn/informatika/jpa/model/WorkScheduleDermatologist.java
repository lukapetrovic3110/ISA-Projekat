package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class WorkScheduleDermatologist {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long workScheduleId;
			
		@OneToOne(fetch = FetchType.EAGER)
	    private TimeInterval validFor;
		
		@OneToOne(fetch = FetchType.EAGER)
	    private TimeInterval shift;
		
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private Set<Examination> scheduledExaminations;
	    
		@OneToOne(fetch = FetchType.LAZY)
	    public Dermatologist dermatologist;
		
		@OneToOne(fetch = FetchType.LAZY)
	    public Pharmacy pharmacy;
	   
	   public WorkScheduleDermatologist()
	    {
	    	
	    }

	public WorkScheduleDermatologist(Long workScheduleId, TimeInterval validFor, TimeInterval shift,
			Set<Examination> scheduledExaminations, Dermatologist dermatologist, Pharmacy pharmacy) {
		super();
		this.workScheduleId = workScheduleId;
		this.validFor = validFor;
		this.shift = shift;
		this.scheduledExaminations = scheduledExaminations;
		this.dermatologist = dermatologist;
		this.pharmacy = pharmacy;
	}

	public Long getWorkScheduleId() {
		return workScheduleId;
	}

	public void setWorkScheduleId(Long workScheduleId) {
		this.workScheduleId = workScheduleId;
	}

	public TimeInterval getValidFor() {
		return validFor;
	}

	public void setValidFor(TimeInterval validFor) {
		this.validFor = validFor;
	}

	public TimeInterval getShift() {
		return shift;
	}

	public void setShift(TimeInterval shift) {
		this.shift = shift;
	}

	public Set<Examination> getScheduledExaminations() {
		return scheduledExaminations;
	}

	public void setScheduledExaminations(Set<Examination> scheduledExaminations) {
		this.scheduledExaminations = scheduledExaminations;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
		
}
