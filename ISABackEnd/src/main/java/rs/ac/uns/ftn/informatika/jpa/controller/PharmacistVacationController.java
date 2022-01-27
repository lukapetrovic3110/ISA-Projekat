package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacistVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestAcceptDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestDeclineDTO;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistVacationService;

@CrossOrigin(origins= "http://localhost:8080")
@RestController
@RequestMapping(value = "/pharmacistVacation", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacistVacationController {
	
	private PharmacistVacationService _pharmacistVacationService;
	
	@Autowired
	public PharmacistVacationController(PharmacistVacationService pharmacistVacationService) {
		this._pharmacistVacationService = pharmacistVacationService;
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/all")
	public ResponseEntity<List<PharmacistVacation>> findAllPharmacistVacation() {
		List<PharmacistVacation> listVacationDTO = _pharmacistVacationService.findAllPharmacistVacation();
		return new ResponseEntity<List<PharmacistVacation>>(listVacationDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/allInMyPharmacy")
	public ResponseEntity<List<PharmacistVacationDTO>> findAllPharmacistVacationInMyPharmacy() {
		List<PharmacistVacationDTO> listVacationDTO = _pharmacistVacationService.findAllPharmacistVacationInMyPharmacy();
		return new ResponseEntity<List<PharmacistVacationDTO>>(listVacationDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/allWithStatusWaiting")
	public ResponseEntity<List<PharmacistVacationDTO>> findAllPharmacistVacationWithStatusWaiting() {
		List<PharmacistVacationDTO> listVacationDTO = _pharmacistVacationService.findAllPharmacistVacationWithStatusWaiting();
		return new ResponseEntity<List<PharmacistVacationDTO>>(listVacationDTO, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/accept") 
	public ResponseEntity<PharmacistVacation> accept(@RequestBody RequestAcceptDTO requestAcceptDTO) {
		try {
			return new ResponseEntity<PharmacistVacation>(_pharmacistVacationService.accept(requestAcceptDTO.getVacationId()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PharmacistVacation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/decline") 
	public ResponseEntity<PharmacistVacation> decline(@RequestBody RequestDeclineDTO requestDeclineDTO) { 
		try {
			return new ResponseEntity<PharmacistVacation>(_pharmacistVacationService.decline(requestDeclineDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PharmacistVacation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PostMapping(value = "/requestVacationPharmacist")
	public ResponseEntity<Boolean> requestForVacationPharmacist(@RequestBody PharmacistVacation pv) {
		if(_pharmacistVacationService.requestForVacationPharmacist(pv) != null)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
}
