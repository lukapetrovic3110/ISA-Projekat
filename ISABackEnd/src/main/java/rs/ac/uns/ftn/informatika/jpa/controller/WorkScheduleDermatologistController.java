package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleDermatologistService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/workScheduleDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkScheduleDermatologistController {

	private WorkScheduleDermatologistService _workScheduleDermatologistService;

	@Autowired
	public WorkScheduleDermatologistController(WorkScheduleDermatologistService workScheduleDermatologistService) {
		this._workScheduleDermatologistService = workScheduleDermatologistService;
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/findShiftByDermatologistId/{dermatologistId}")
	public ResponseEntity<String> getShiftByDermatologistId(@PathVariable Long dermatologistId) {
		
		return new ResponseEntity<String>(_workScheduleDermatologistService.getShiftByDermatologistId(dermatologistId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/findValidForByDermatologistId/{dermatologistId}")
	public ResponseEntity<String> getValidForByDermatologistId(@PathVariable Long dermatologistId) {
		
		return new ResponseEntity<String>(_workScheduleDermatologistService.getValidForByDermatologistId(dermatologistId), HttpStatus.OK);
	}
	
}
