package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestDeclineDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;

public interface IDermatologistVacationService {
	
	List<DermatologistVacationDTO> findAllDermatologistVacation();
	
	List<DermatologistVacationDTO> findAllDermatologistVacationWithStatusWaiting();
	
	DermatologistVacation accept(Long vacationId);
	
	DermatologistVacation decline(RequestDeclineDTO requestDeclineDTO);

	DermatologistVacation requestForVacationDermatologist(DermatologistVacation dv);
	
	List<ResponseVacationDTO> isOnVacation(Long dermatologistId);

	List<DermatologistVacation> findAllDermatologistVacationWithStatusWaitingByDermatologistId(Long dermatologistId);
}
