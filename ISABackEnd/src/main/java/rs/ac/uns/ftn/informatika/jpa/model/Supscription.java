package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Supscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supscriptionId;
	
	@Column(name = "cancelled", nullable = false)
    private Boolean cancelled;   
    
	@OneToMany(fetch = FetchType.LAZY)
	public Set<Pharmacy> pharmacy;

	@ManyToOne(fetch = FetchType.LAZY)
	public Patient patient;
   
    public Supscription(Long supscriptionId, Boolean cancelled, Patient patient) {
		super();
		this.supscriptionId = supscriptionId;
		this.cancelled = cancelled;
		this.pharmacy = new HashSet<Pharmacy>();
		this.patient = patient;
	}
    
    public Supscription()
    {
    	
    }

	public Long getSupscriptionId() {
		return supscriptionId;
	}
	
	public void setSupscriptionId(Long supscriptionId) {
		this.supscriptionId = supscriptionId;
	}
	
	public Boolean getCancelled() {
		return cancelled;
	}
	
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Collection<Pharmacy> getPharmacy() {
		if (pharmacy == null)
			pharmacy = new HashSet<Pharmacy>();
		return pharmacy;
	}
   
   public Iterator getIteratorPharmacy() {
      if (pharmacy == null)
         pharmacy = new HashSet<Pharmacy>();
      return pharmacy.iterator();
   }
   
   public void setPharmacy(Collection<Pharmacy> newPharmacy) {
      removeAllPharmacy();
      for (Iterator iter = newPharmacy.iterator(); iter.hasNext();)
         addPharmacy((Pharmacy)iter.next());
   }
   
   public void addPharmacy(Pharmacy newPharmacy) {
      if (newPharmacy == null)
         return;
      if (this.pharmacy == null)
         this.pharmacy = new HashSet<Pharmacy>();
      if (!this.pharmacy.contains(newPharmacy))
         this.pharmacy.add(newPharmacy);
   }
   
   public void removePharmacy(Pharmacy oldPharmacy) {
      if (oldPharmacy == null)
         return;
      if (this.pharmacy != null)
         if (this.pharmacy.contains(oldPharmacy))
            this.pharmacy.remove(oldPharmacy);
   }
   
   public void removeAllPharmacy() {
      if (pharmacy != null)
         pharmacy.clear();
   }

	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	   
}