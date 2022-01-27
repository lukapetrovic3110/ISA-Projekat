package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.HashSet;
import java.util.Set;

import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineForm;
import rs.ac.uns.ftn.informatika.jpa.model.PrescriptionType;

public class MedicineRegistrationDTO {

	private Long id;	
	private String name;
	private String code;
	private int loyaltyPoints;
	private MedicineForm medicineForm;
	private String type;
	public HashSet<Medicine> replacementMedicine;
	private PrescriptionType prescriptionType;
	private String manufacturer;
	private String precautions;
	private MedicineSpecificationDTO medicineSpecificationDTO;
	
	public MedicineRegistrationDTO() {}

	public MedicineRegistrationDTO(Long id, String name, String code, int loyaltyPoints, MedicineForm medicineForm,
			String type, HashSet<Medicine> replacementMedicine, PrescriptionType prescriptionType, String manufacturer,
			String precautions, MedicineSpecificationDTO medicineSpecificationDTO) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.loyaltyPoints = loyaltyPoints;
		this.medicineForm = medicineForm;
		this.type = type;
		this.replacementMedicine = replacementMedicine;
		this.prescriptionType = prescriptionType;
		this.manufacturer = manufacturer;
		this.precautions = precautions;
		this.medicineSpecificationDTO = medicineSpecificationDTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public MedicineForm getMedicineForm() {
		return medicineForm;
	}



	public void setMedicineForm(MedicineForm medicineForm) {
		this.medicineForm = medicineForm;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public Set<Medicine> getReplacementMedicine() {
		return replacementMedicine;
	}



	public void setReplacementMedicine(HashSet<Medicine> replacementMedicine) {
		this.replacementMedicine = replacementMedicine;
	}



	public MedicineSpecificationDTO getMedicineSpecificationDTO() {
		return medicineSpecificationDTO;
	}



	public void setMedicineSpecificationDTO(MedicineSpecificationDTO medicineSpecificationDTO) {
		this.medicineSpecificationDTO = medicineSpecificationDTO;
	}



	public PrescriptionType getPrescriptionType() {
		return prescriptionType;
	}



	public void setPrescriptionType(PrescriptionType prescriptionType) {
		this.prescriptionType = prescriptionType;
	}



	public MedicineSpecificationDTO getMedicineSpecification() {
		return medicineSpecificationDTO;
	}



	public void setMedicineSpecification(MedicineSpecificationDTO medicineSpecification) {
		this.medicineSpecificationDTO = medicineSpecification;
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
	
	
}
