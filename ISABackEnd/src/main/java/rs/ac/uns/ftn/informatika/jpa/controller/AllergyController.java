package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.service.AllergyService;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/allergy")
public class AllergyController {
	@Autowired
	private AllergyService _allergyService ;
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping("/{id}/add")
	public ResponseEntity<Set<Medicine>> addMedicineToAllergy(@PathVariable Long id,@RequestBody Medicine medicine) {
		Set<Medicine> m = _allergyService.addMedicineToAllergy(medicine, id);
		return new ResponseEntity<Set<Medicine>>(m, HttpStatus.OK);
	}

}
