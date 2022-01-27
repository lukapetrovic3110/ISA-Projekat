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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineId;
   
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private String medicineCode;
	
	@Column(name = "type", nullable = false)
    private String type;
	
	@Column(name = "manufacturer", nullable = false)
    private String manufacturer;
		
	@Column(name = "precautions", nullable = false)
    private String precautions;
	
	@Column(name = "loyaltyPoints", nullable = false)
    private int loyaltyPoints;

	@Column(name = "rating")
    private Double rating;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public MedicineSpecification medicineSpecification;
    
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Medicine> replacementMedicine;
    
	@Enumerated(EnumType.STRING)
	public MedicineForm medicineForm;
    
	@Enumerated(EnumType.STRING)
    public PrescriptionType prescriptionType;
	   
	public Medicine(Long medicineId, String name, String medicineCode, String type, String manufacturer, String precautions, int loyaltyPoints,
			MedicineSpecification medicineSpecification, MedicineForm medicineForm, 
			PrescriptionType prescriptionType, Double rating) {
		super();
		this.medicineId = medicineId;
		this.name = name;
		this.type = type;
		this.manufacturer = manufacturer;
		this.precautions = precautions;
		this.loyaltyPoints = loyaltyPoints;
		this.medicineSpecification = medicineSpecification;
		this.replacementMedicine = new HashSet<Medicine>();
		this.medicineForm = medicineForm;
		this.prescriptionType = prescriptionType;
		this.medicineCode = medicineCode;
		this.rating = rating;
	}
	
	public Medicine()
	{
		
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double newRating) {
		this.rating = newRating;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public Long getMedicineId() {
		return medicineId;
	}
	
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getPrecautions() {
		return precautions;
	}
	
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}
	
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	public MedicineSpecification getMedicineSpecification() {
		return medicineSpecification;
	}
	
	public void setMedicineSpecification(MedicineSpecification medicineSpecification) {
		this.medicineSpecification = medicineSpecification;
	}
	
	public Set<Medicine> getReplacementMedicine() {
		return replacementMedicine;
	}
	
	public void setReplacementMedicine(Set<Medicine> replacementMedicine) {
		this.replacementMedicine = replacementMedicine;
	}
	
	public MedicineForm getMedicineForm() {
		return medicineForm;
	}
	
	public void setMedicineForm(MedicineForm medicineForm) {
		this.medicineForm = medicineForm;
	}
	
	public PrescriptionType getPrescriptionType() {
		return prescriptionType;
	}
	
	public void setPrescriptionType(PrescriptionType prescriptionType) {
		this.prescriptionType = prescriptionType;
	}

}