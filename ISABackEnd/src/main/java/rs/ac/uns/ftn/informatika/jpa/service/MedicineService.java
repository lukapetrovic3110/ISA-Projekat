package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineSpecificationDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IMedicineService;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Contraindication;
import rs.ac.uns.ftn.informatika.jpa.model.Ingridient;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineSpecification;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PriceTag;
import rs.ac.uns.ftn.informatika.jpa.repository.IAllergyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IContraindicationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IIngridientRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineItemRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineSpecificationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;

@Service
public class MedicineService implements IMedicineService{

	@Autowired IMedicineRepository _medicineRepository;
	
	@Autowired
	private IPharmacyRepository _pharmacyRepository;

	@Autowired
	private IMedicineItemRepository _medicineItemRepository;
	
	@Autowired
	private IIngridientRepository _ingridientRepository;
	
	@Autowired
	private IContraindicationRepository _contraidicationRepository;
	
	@Autowired
	private IMedicineSpecificationRepository _medicineSpecificationRepository;
	
	@Override
	public ArrayList<Medicine> findAllMedicine() {
		
		return (ArrayList<Medicine>) _medicineRepository.findAll();
	}
	
	
	@Override
	public ArrayList<Medicine> findAllMedicineForAllergies(Allergy allergy) {
		
		ArrayList<Medicine> medicine = findAllMedicine();
		HashSet<Medicine> medicineInAllergy = (HashSet<Medicine>) allergy.getMedicine();
		ArrayList<Medicine> result = new ArrayList<Medicine>();
		boolean found = false;
		for (Medicine m : medicine) {
			for (Medicine m2 : medicineInAllergy) {
				if(m2.getMedicineId() == m.getMedicineId()) {
					found = true;
				break;
			}		
		}
			if(!found)
			{
			result.add(m);
			}
			found = false;
			
		}
		
		return result;
	}
	

	@Override
	public ArrayList<Medicine> findMedicineByName(String name) {
		ArrayList<Medicine> medicine = findAllMedicine();
		ArrayList<Medicine> result = new ArrayList<Medicine>();
		
		for (Medicine med : medicine) {
			if(med.getName().contains(name))
				result.add(med);
		}
		return result;
	}

	@Override
	public Medicine addNewMedicine(MedicineRegistrationDTO medicineRegistration) {

		Medicine medicine = new Medicine();
		
		medicine.setName(medicineRegistration.getName());
		medicine.setMedicineCode(medicineRegistration.getCode());
		medicine.setLoyaltyPoints(medicineRegistration.getLoyaltyPoints());
		
		medicine.setMedicineForm(medicineRegistration.getMedicineForm());
		medicine.setType(medicineRegistration.getType());
		medicine.setReplacementMedicine(medicineRegistration.getReplacementMedicine());
		medicine.setManufacturer(medicineRegistration.getManufacturer());
		medicine.setPrecautions(medicineRegistration.getPrecautions());
		medicine.setPrescriptionType(medicineRegistration.getPrescriptionType());
		medicine.setRating(0.0);
		medicine.setMedicineSpecification(addMedicineSpecification(medicineRegistration.getMedicineSpecificationDTO()));
		
		return _medicineRepository.save(medicine);
	}
	
	@Override
	public List<MedicineAvailableInPharmacyDTO> findPharmacyForMedicineItem(String name) {
		
		List<Pharmacy> pharmacies = _pharmacyRepository.findAll();
		List<MedicineItem> medicineItems = _medicineItemRepository.findAll();
		List<MedicineAvailableInPharmacyDTO> result = new ArrayList<MedicineAvailableInPharmacyDTO>();
		
		HashSet<MedicineItem> items = new HashSet<>();
		
		for(MedicineItem m: medicineItems) {
			if(m.getMedicine().getName().equals(name)) {
				items.add(m);
			}
		}

		for(Pharmacy p: pharmacies) {
			for(MedicineItem m: items) {
				if(p.getMedicineItem().contains(m) && m.getQuantity() > 0) {
					if(getCurrentPriceForMedicine(p, name)!=null) {
					result.add(new MedicineAvailableInPharmacyDTO(p, getCurrentPriceForMedicine(p, name)));
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public PriceTag getCurrentPriceForMedicine(Pharmacy p, String med) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date   
		for (PriceTag price : p.getPriceTags()) {
			if(price.getMedicine().getName().equals(med)) {
				if(price.getTimeInterval().getStartDate().before(cal.getTime()) && price.getTimeInterval().getEndDate().after(cal.getTime())) {
					return price;
				}
			}
		}
		return null;
	}

	@Override
	public void updateRating(Long medicineId, Double newRating) {
		Medicine existing = _medicineRepository.findById(medicineId).orElse(null);
		if(existing!= null) {
			existing.setRating(newRating);
			_medicineRepository.save(existing);
		}
		
	}


	@Override
	public MedicineSpecification addMedicineSpecification(MedicineSpecificationDTO medicineSpecificationDTO) {
		MedicineSpecification med_spec = new MedicineSpecification();
		
		med_spec.setDosage(medicineSpecificationDTO.getDosage());
		
		Ingridient ingridient = new Ingridient();
		ingridient.setName(medicineSpecificationDTO.getIngridientName());
		_ingridientRepository.save(ingridient);
		
		HashSet<Ingridient> ingridients = new HashSet<Ingridient>();
		ingridients.add(ingridient);
		med_spec.setIngridient(ingridients);
		
		Contraindication contraindication = new Contraindication();
		contraindication.setDescription(medicineSpecificationDTO.getDescription());
		_contraidicationRepository.save(contraindication);

		HashSet<Contraindication> contraindications = new HashSet<Contraindication>();
		contraindications.add(contraindication);
		med_spec.setContraindication(contraindications);
		
		return _medicineSpecificationRepository.save(med_spec);
	}

}
