package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.CreatePharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyPharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;

public interface IPharmacistService {
	
    List<PharmacistDTO> getAllPharmacist();
	
    List<PharmacistDTO> searchPharmacist(String firstName, String lastName);

	List<PharmacistDTO> findPharmacistsByPharmacy(Long pharmacyId);

	List<PharmacistDTO> searchPharmacistsByLastName(String lastName);

	List<PharmacistDTO> searchPharmacistsByFirstName(String firstName);

	List<PharmacistDTO> filterPharmacistByRating(Double minRating, Double maxRating);

	void updateRating(Long userId, Double newRating);

	List<PharmacyPharmacistDTO> getPharmacistsByPharmacy();
	
	Pharmacist createPharmacistByPharmacyAdmin(CreatePharmacistDTO createPharmacistDTO);

	Boolean deletePharmacist(Long deletePharmacistId);
}
