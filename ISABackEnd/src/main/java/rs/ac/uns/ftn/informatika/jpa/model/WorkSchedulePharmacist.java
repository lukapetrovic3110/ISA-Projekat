package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
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
public class WorkSchedulePharmacist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workScheduleId;
		
	@OneToOne(fetch = FetchType.EAGER)
    private TimeInterval validFor;
	
	@OneToOne(fetch = FetchType.EAGER)
    private TimeInterval shift;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Consultation> scheduledConsultations;
    
	@OneToOne(fetch = FetchType.EAGER)
    public Pharmacist pharmacist;
	
	@OneToOne(fetch = FetchType.EAGER)
    public Pharmacy pharmacy;
   
   public WorkSchedulePharmacist()
    {
    	
    }

	public WorkSchedulePharmacist(Long workScheduleId, TimeInterval validFor, TimeInterval shift,
			Set<Consultation> scheduledConsultations, Pharmacist pharmacist, Pharmacy pharmacy) {
		super();
		this.workScheduleId = workScheduleId;
		this.validFor = validFor;
		this.shift = shift;
		this.scheduledConsultations = scheduledConsultations;
		this.pharmacist = pharmacist;
		this.pharmacy = pharmacy;
	}
	
	public WorkSchedulePharmacist(TimeInterval validFor, TimeInterval shift, Pharmacist pharmacist, Pharmacy pharmacy) {
		super();
		this.validFor = validFor;
		this.shift = shift;
		this.pharmacist = pharmacist;
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

	public Set<Consultation> getScheduledConsultations() {
		if (scheduledConsultations == null)
	         scheduledConsultations = new HashSet<Consultation>();
		return scheduledConsultations;
	}

	public void setScheduledConsultations(Set<Consultation> scheduledConsultations) {
		this.scheduledConsultations = scheduledConsultations;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	

}