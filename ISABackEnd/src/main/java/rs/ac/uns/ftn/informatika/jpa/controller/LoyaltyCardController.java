package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.service.LoyaltyCardDTO;
import rs.ac.uns.ftn.informatika.jpa.service.LoyaltyCardService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/loyaltyCard")
public class LoyaltyCardController {

	@Autowired
	private LoyaltyCardService _loyaltyCardService;
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<LoyaltyCardDTO> findLoyaltyCardByUserId(@PathVariable Long id) {
		LoyaltyCardDTO loyalty = _loyaltyCardService.findLoyaltyCardByUserId(id);
		return new ResponseEntity<LoyaltyCardDTO>(loyalty, HttpStatus.OK);
	}
}
