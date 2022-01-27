package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Therapy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long therapyId;
    
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Medicine> medicine;
	
	@OneToOne(fetch = FetchType.LAZY)
    public TimeInterval timeInterval;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Examination examination;
   
    public Therapy(Long therapyId, TimeInterval timeInterval, Examination examination) {
		super();
		this.therapyId = therapyId;
		this.medicine = new HashSet<Medicine>();
		this.timeInterval = timeInterval;
		this.examination = examination;
   }
    
    public Therapy()
    {
    	
    }

   public Long getTherapyId() {
	   return therapyId;
   }

   public void setTherapyId(Long therapyId) {
	   this.therapyId = therapyId;
   }

   public Collection<Medicine> getMedicine() {
	   if (medicine == null)
		   medicine = new HashSet<Medicine>();
	   	return medicine;
   	}
   
   public Iterator getIteratorMedicine() {
      if (medicine == null)
         medicine = new HashSet<Medicine>();
      return medicine.iterator();
   }
   
   public void setMedicine(Set<Medicine> newMedicine) {
      removeAllMedicine();
      for (Iterator iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((Medicine)iter.next());
   }
   
   public void addMedicine(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicine == null)
         this.medicine = new java.util.HashSet<Medicine>();
      if (!this.medicine.contains(newMedicine))
         this.medicine.add(newMedicine);
   }
   
   public void removeMedicine(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicine != null)
         if (this.medicine.contains(oldMedicine))
            this.medicine.remove(oldMedicine);
   }

   public void removeAllMedicine() {
      if (medicine != null)
         medicine.clear();
   }

	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	
	public void setTimeInterval(TimeInterval timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	public Examination getExamination() {
		return examination;
	}
	
	public void setExamination(Examination examination) {
		this.examination = examination;
	}

}