package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyRegisterDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;

public interface IPharmacyService {

	Pharmacy findById(Long id);

	Pharmacy createPharmacy(PharmacyRegisterDTO pharmacy);
	
	PharmacyDTO getPharmacyById(Long pharmacyId);
	
	ArrayList<Pharmacy> findAllPharmacy();
	
	ArrayList<Pharmacy> getPharmacyByNameOrAddress(String parametar);

	List<Pharmacy> getSubscribedPharmacyForPatient(Long patient_id);
	
	void updateRating(Long pharmacyId, Double newRating);

	ArrayList<Pharmacy> getAllByDermatologist(Long id);
	
	ArrayList<Pharmacy> filtratePharmaciesByRating(Long rating, ArrayList<Pharmacy> phamacies);
}
