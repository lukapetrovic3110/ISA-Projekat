package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IAllergyService;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.repository.IAllergyRepository;

@Service
public class AllergyService implements IAllergyService{
	
	@Autowired
	private IAllergyRepository _allergyRepository;

	@Override
	public Set<Medicine> addMedicineToAllergy(Medicine medicine, Long id) {
		Allergy allergy = _allergyRepository.findById(id).orElse(null);
		Set medicineInAllergy = allergy.getMedicine();
		allergy.addMedicine(medicine);
        _allergyRepository.save(allergy);
		return allergy.getMedicine();
	}


}
