package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class MedicineSpecification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medSpecId;
	
	private Double dosage;
	
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Contraindication> contraindication;
	
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Ingridient> ingridient;
   
    public MedicineSpecification(Double dosage, Long medSpecId) {
		super();
		this.dosage = dosage;
		this.medSpecId = medSpecId;
		this.contraindication = new HashSet<Contraindication>();
		this.ingridient = new HashSet<Ingridient>();
    }
    
    public MedicineSpecification()
    {
    	
    }
	
	public Double getDosage() {
		return dosage;
	}
	
	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}
	
	public Long getMedSpecId() {
		return medSpecId;
	}
	
	public void setMedSpecId(Long medSpecId) {
		this.medSpecId = medSpecId;
	}

	public Set<Contraindication> getContraindication() {
		return contraindication;
	}

	public void setContraindication(Set<Contraindication> contraindication) {
		this.contraindication = contraindication;
	}

	public Set<Ingridient> getIngridient() {
		return ingridient;
	}

	public void setIngridient(Set<Ingridient> ingridient) {
		this.ingridient = ingridient;
	}
	
}