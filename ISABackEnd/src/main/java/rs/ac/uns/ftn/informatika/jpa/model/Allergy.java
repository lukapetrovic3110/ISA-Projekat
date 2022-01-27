package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Allergy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Medicine> medicine = new HashSet<Medicine>();
   
    public Allergy(Long allergyId) {
    	super();
    	this.allergyId = allergyId;
    }
	
    public Allergy()
    {
    	
    }
	public Long getAllergyId() {
		return allergyId;
	}
	
	public void setAllergyId(Long allergyId) {
		this.allergyId = allergyId;
	}
	   
	   public Set<Medicine> getMedicine() {
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
	         this.medicine = new HashSet<Medicine>();
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

}