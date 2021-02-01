package rs.ac.uns.isaprojekat.model;

import java.util.*;

public class Supscription {
   private Long supscriptionId;
   private Boolean cancelled;   
   public Collection<Pharmacy> pharmacy;
   public Patient patient;
   
   public Supscription(Long supscriptionId, Boolean cancelled, Patient patient) {
	super();
	this.supscriptionId = supscriptionId;
	this.cancelled = cancelled;
	this.pharmacy = new ArrayList<Pharmacy>();
	this.patient = patient;
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