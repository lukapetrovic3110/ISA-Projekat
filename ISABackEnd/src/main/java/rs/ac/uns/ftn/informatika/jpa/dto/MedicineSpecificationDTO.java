package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.HashSet;
import java.util.Set;

import rs.ac.uns.ftn.informatika.jpa.model.Contraindication;
import rs.ac.uns.ftn.informatika.jpa.model.Ingridient;

public class MedicineSpecificationDTO {

	private Long id;
	
	private Double dosage;
	
	public String ingridientName;

	public String description;
	
	public MedicineSpecificationDTO() {}
	
	public MedicineSpecificationDTO(Long id, Double dosage, String ingridientName, String description) {
		super();
		this.id = id;
		this.dosage = dosage;
		this.ingridientName = ingridientName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDosage() {
		return dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}

	public String getIngridientName() {
		return ingridientName;
	}

	public void setIngridientName(String ingridientName) {
		this.ingridientName = ingridientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
