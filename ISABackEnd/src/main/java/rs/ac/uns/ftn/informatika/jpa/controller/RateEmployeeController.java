package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.RateEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.DermatologistService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistService;
import rs.ac.uns.ftn.informatika.jpa.service.RateEmployeeService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/rateEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
public class RateEmployeeController {

	@Autowired
	private RateEmployeeService _rateEmployeeService;
	
	@Autowired
	private PharmacistService _pharmacistService;
	
	@Autowired
	private DermatologistService _dermatologistService;
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PostMapping(value = "/ratePharmacist")
	public ResponseEntity<?> rateEmployeePharmacist(@RequestBody RateEmployee rate) {
		if(rate.getRating()<6 || rate.getRating()>10) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		rate.setPatient((Patient) user);
		Double newRating = _rateEmployeeService.rateEmployee(rate);
		_pharmacistService.updateRating(rate.getPharmacyEmployee().getUserId(), newRating);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PostMapping(value = "/rateDermatologist")
	public  ResponseEntity<?> rateEmployeeDermatologist(@RequestBody RateEmployee rate) {
		if(rate.getRating()<6 || rate.getRating()>10) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		rate.setPatient((Patient) user);
		Double newRating = _rateEmployeeService.rateEmployee(rate);
		_dermatologistService.updateRating(rate.getPharmacyEmployee().getUserId(), newRating);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
