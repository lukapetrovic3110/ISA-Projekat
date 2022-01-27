package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class EPrescription {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long prescriptionId;
   
   @Column(name = "code", nullable = false)
   private String code;
   
   @Column(name = "date", nullable = false)
   private Date date;
   @Enumerated(EnumType.STRING)
   private EPrescriptionStatus status;
   
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   public Set<Medicine> medicine;
   
   @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.DETACH)
   @JoinColumn(name = "pharmacy_pharmacy_id", referencedColumnName = "pharmacy_id")
   private Pharmacy pharmacy;
   
   @OneToOne(fetch = FetchType.EAGER)
   private Patient patient;
   
   
   public EPrescription(Long prescriptionId, String code, Date date, Pharmacy pharmacy, Patient patient, EPrescriptionStatus status) {
	super();
	this.prescriptionId = prescriptionId;
	this.code = code;
	this.date = date;
	this.medicine = new HashSet<Medicine>();
	this.pharmacy = pharmacy;
	this.patient = patient;
	this.status = status;
}
   public EPrescription() 
   {
	   
   }
	
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public java.util.Set<Medicine> getMedicine() {
	      if (medicine == null)
	         medicine = new java.util.HashSet<Medicine>();
	      return medicine;
	   }
	   
	   public java.util.Iterator getIteratorMedicine() {
	      if (medicine == null)
	         medicine = new java.util.HashSet<Medicine>();
	      return medicine.iterator();
	   }
	   
	   public void setMedicine(java.util.Collection<Medicine> newMedicine) {
	      removeAllMedicine();
	      for (java.util.Iterator iter = newMedicine.iterator(); iter.hasNext();)
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
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void setMedicine(Set<Medicine> medicine) {
		this.medicine = medicine;
	}
	public EPrescriptionStatus getStatus() {
		return status;
	}
	public void setStatus(EPrescriptionStatus status) {
		this.status = status;
	}

}