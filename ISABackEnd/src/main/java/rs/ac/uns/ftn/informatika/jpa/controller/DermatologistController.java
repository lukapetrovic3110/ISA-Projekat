package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;
import java.util.Set;

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

import rs.ac.uns.ftn.informatika.jpa.dto.CreateDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.DeleteDermatologistRequestDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.service.DermatologistService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistController {
	
	private DermatologistService _dermatologistService;
	
	@Autowired
	public DermatologistController(DermatologistService dermatologistService) {
		this._dermatologistService = dermatologistService;
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/createDermatologistByPharmacyAdmin")
	public ResponseEntity<Dermatologist> createDermatologistByPharmacyAdmin(@RequestBody CreateDermatologistDTO createDermatologistDTO) {
		try {
			return new ResponseEntity<Dermatologist>(_dermatologistService.createDermatologistByPharmacyAdmin(createDermatologistDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Dermatologist>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(path = "/all")
	public List<DermatologistDTO> getAllDermatologist(){
		return _dermatologistService.getAllDermatologist();
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/searchDermatologistsByFirstName/{firstName}")
	public List<DermatologistDTO> searchDermatologistsByFirstName(@PathVariable String firstName){
		 return _dermatologistService.searchDermatologistsByFirstName(firstName);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/searchDermatologistsByLastName/{lastName}")
	public List<DermatologistDTO> searchDermatologistsByLastName(@PathVariable String lastName){
		 return _dermatologistService.searchDermatologistsByLastName(lastName);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/searchDermatologists/{firstName}/{lastName}")
	public List<DermatologistDTO> searchDermatologist(@PathVariable String firstName, @PathVariable String lastName){
		 return _dermatologistService.searchDermatologist(firstName, lastName);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/filterDermatologistByRating/{minRating}/{maxRating}")
	public List<DermatologistDTO> filterDermatologistByRating(@PathVariable Double minRating, @PathVariable Double maxRating)
	{
		return _dermatologistService.filterDermatologistByRating(minRating, maxRating);
	}
	
	@GetMapping(path = "/allDermatologistByPharmacyId/{pharmacyId}")
	public Set<Dermatologist> getAllDermatologistByPharmacyId(@PathVariable Long pharmacyId){
		return _dermatologistService.getAllDermatologistByPharmacyId(pharmacyId);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/dermatologistsByPharmacy")
	public ResponseEntity<List<PharmacyDermatologistDTO>> findDermatologistsByPharmacyId() {
		return new ResponseEntity<List<PharmacyDermatologistDTO>>(_dermatologistService.findDermatologistsByPharmacy(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/deleteDermatologist")
	public ResponseEntity<Boolean> deleteDermatologist(@RequestBody DeleteDermatologistRequestDTO deleteDermatologistRequestDTO) {
		return new ResponseEntity<Boolean>(_dermatologistService.deleteDermatologist(deleteDermatologistRequestDTO.getDeleteDermatologistId()), HttpStatus.OK);
	}
}

