package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineSpecificationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineSpecification;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PriceTag;

public interface IMedicineService {

	public ArrayList<Medicine> findAllMedicine();
	public ArrayList<Medicine> findAllMedicineForAllergies(Allergy allergy);
	public ArrayList<Medicine> findMedicineByName(String name);
	public Medicine addNewMedicine(MedicineRegistrationDTO medicineRegistration);	 
	public List<MedicineAvailableInPharmacyDTO> findPharmacyForMedicineItem(String name);
	PriceTag getCurrentPriceForMedicine(Pharmacy p, String med);
	void updateRating(Long medicineId, Double newRating);
	public MedicineSpecification addMedicineSpecification(MedicineSpecificationDTO medicineSpecificationDTO);
}
