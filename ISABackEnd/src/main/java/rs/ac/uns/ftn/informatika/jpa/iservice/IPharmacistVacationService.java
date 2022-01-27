package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacistVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestDeclineDTO;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;

public interface IPharmacistVacationService {
	
	List<PharmacistVacation> findAllPharmacistVacation();
	
	List<PharmacistVacationDTO> findAllPharmacistVacationInMyPharmacy();
	
	List<PharmacistVacationDTO> findAllPharmacistVacationWithStatusWaiting();
	
	PharmacistVacation accept(Long vacationId);
	
	PharmacistVacation decline(RequestDeclineDTO requestDeclineDTO);

	PharmacistVacation requestForVacationPharmacist(PharmacistVacation pv);

	List<PharmacistVacation> findAllAcceptedVacations();

}
