package rs.ac.uns.ftn.informatika.jpa.controller;

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
import org.springframework.web.util.UriComponentsBuilder;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacyAdminService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/pharmacyAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyAdminController {
	
	private PharmacyAdminService _pharmacyAdminService;
	
	@Autowired
	public PharmacyAdminController(PharmacyAdminService pharmacyAdminService) {
		this._pharmacyAdminService = pharmacyAdminService;
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/{id}")
	public PharmacyAdminDTO findPharmacyAdminById(@PathVariable Long id) {
		return _pharmacyAdminService.getPharmacyAdminById(id);
	}
	
	@PostMapping("/createPhAdmin")
	public PharmacyAdministrator createPhAdmin(@RequestBody PharmacyAdminRegistrationDTO userRequest, UriComponentsBuilder ucBuilder) {
		  return _pharmacyAdminService.savePharmacyAdmin(userRequest);
		
	}
	
}
