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

import rs.ac.uns.ftn.informatika.jpa.dto.CreatePharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.DeletePharmacistRequestDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyPharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/pharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacistController {

	@Autowired
	private PharmacistService _pharmacistSerivce ;
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/all")
	public List<PharmacistDTO> getAllPharmacist() {
		 return _pharmacistSerivce.getAllPharmacist();
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/createPharmacistByPharmacyAdmin")
	public ResponseEntity<Pharmacist> createPharmacistByPharmacyAdmin(@RequestBody CreatePharmacistDTO createPharmacistDTO) {
		try {
			return new ResponseEntity<Pharmacist>(_pharmacistSerivce.createPharmacistByPharmacyAdmin(createPharmacistDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Pharmacist>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/searchPharmacistsByFirstName/{firstName}")
	public List<PharmacistDTO> searchPharmacistsByFirstName(@PathVariable String firstName){
		 return _pharmacistSerivce.searchPharmacistsByFirstName(firstName);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/searchPharmacistsByLastName/{lastName}")
	public List<PharmacistDTO> searchPharmacistsByLastName(@PathVariable String lastName){
		 return _pharmacistSerivce.searchPharmacistsByLastName(lastName);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/searchPharmacists/{firstName}/{lastName}")
	public List<PharmacistDTO> searchPharmacist(@PathVariable String firstName, @PathVariable String lastName){
		 return _pharmacistSerivce.searchPharmacist(firstName, lastName);
	}
	
	@GetMapping(path = "/findPharmacistsByPharmacy/{pharmacyId}")
	public List<PharmacistDTO> findPharmacistsByPharmacy(@PathVariable Long pharmacyId)
	{
		return _pharmacistSerivce.findPharmacistsByPharmacy(pharmacyId);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/filterPharmacistByRating/{minRating}/{maxRating}")
	public List<PharmacistDTO> filterPharmacistByRating(@PathVariable Double minRating, @PathVariable Double maxRating)
	{
		return _pharmacistSerivce.filterPharmacistByRating(minRating, maxRating);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/pharmacistsByPharmacy")
	public ResponseEntity<List<PharmacyPharmacistDTO>> findPharmacistsByPharmacy() {
		return new ResponseEntity<List<PharmacyPharmacistDTO>>(_pharmacistSerivce.getPharmacistsByPharmacy(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/deletePharmacist")
	public ResponseEntity<Boolean> deletePharmacist(@RequestBody DeletePharmacistRequestDTO deletePharmacistRequestDTO) {
		return new ResponseEntity<Boolean>(_pharmacistSerivce.deletePharmacist(deletePharmacistRequestDTO.getDeletePharmacistId()), HttpStatus.OK);
	}
}
