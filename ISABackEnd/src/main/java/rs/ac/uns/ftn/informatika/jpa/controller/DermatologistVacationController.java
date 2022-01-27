package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestAcceptDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestDeclineDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.service.DermatologistVacationService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/dermatologistVacation", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistVacationController {
	
	private DermatologistVacationService _dermatologistVacationService;
	
	@Autowired
	public DermatologistVacationController(DermatologistVacationService dermatologistVacationService) {
		this._dermatologistVacationService = dermatologistVacationService;
	}
	
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN')")
	@GetMapping(path = "/all")
	public ResponseEntity<List<DermatologistVacationDTO>> findAllPharmacistVacation() {
		List<DermatologistVacationDTO> listVacationDTO = _dermatologistVacationService.findAllDermatologistVacation();
		return new ResponseEntity<List<DermatologistVacationDTO>>(listVacationDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN')")
	@GetMapping(path = "/allWithStatusWaiting")
	public ResponseEntity<List<DermatologistVacationDTO>> findAllPharmacistVacationWithStatusWaiting() {
		List<DermatologistVacationDTO> listVacationDTO = _dermatologistVacationService.findAllDermatologistVacationWithStatusWaiting();
		return new ResponseEntity<List<DermatologistVacationDTO>>(listVacationDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN')")
	@PostMapping(value = "/accept") 
	public ResponseEntity<DermatologistVacation> accept(@RequestBody RequestAcceptDTO requestAcceptDTO) {
		try {
			return new ResponseEntity<DermatologistVacation>(_dermatologistVacationService.accept(requestAcceptDTO.getVacationId()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DermatologistVacation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN')")
	@PostMapping(value = "/decline") 
	public ResponseEntity<DermatologistVacation> decline(@RequestBody RequestDeclineDTO requestDeclineDTO) { 
		try {
			return new ResponseEntity<DermatologistVacation>(_dermatologistVacationService.decline(requestDeclineDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DermatologistVacation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PostMapping(value = "/requestVacationDermatologist")
	public ResponseEntity<Boolean> requestForVacationDermatologist(@RequestBody DermatologistVacation dv) {
		if(_dermatologistVacationService.requestForVacationDermatologist(dv) != null)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/isOnVacation/{dermatologistId}")
	public ResponseEntity<List<ResponseVacationDTO>> isOnVacation(@PathVariable Long dermatologistId) {
		return new ResponseEntity<List<ResponseVacationDTO>>(_dermatologistVacationService.isOnVacation(dermatologistId), HttpStatus.OK);
	}


}
