package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;
import java.util.Set;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;

public interface IDermatologistService {
	
	List<DermatologistDTO> getAllDermatologist();

	List<DermatologistDTO> searchDermatologist(String firstName, String lastName);
	
	List<DermatologistDTO> searchDermatologistsByFirstName(String firstName);
	
	List<DermatologistDTO> searchDermatologistsByLastName(String lastName);
	
	List<DermatologistDTO> filterDermatologistByRating(Double minRating, Double maxRating);
	
	Set<Dermatologist> getAllDermatologistByPharmacyId(Long pharmacyId);

	void updateRating(Long userId, Double newRating);

	List<PharmacyDermatologistDTO> findDermatologistsByPharmacy();

	Dermatologist createDermatologistByPharmacyAdmin(CreateDermatologistDTO createDermatologistDTO);

	Boolean deleteDermatologist(Long deleteDermatologistId);
}
