package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PatientAppointmentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.model.PenaltyType;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;
import rs.ac.uns.ftn.informatika.jpa.service.PenaltyService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@Configuration
@EnableScheduling
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService _userService ;
	

	@Autowired
	private PenaltyService _penaltyService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findUser(@PathVariable Long id) {
		User user =(User) _userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/currentUser")
	public ResponseEntity<User> getCurrentLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

 
	@GetMapping(value = "/email/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return _userService.findByEmail(email);
	}	
	

	@GetMapping(value = "/email/{email}/{password}")
	public User findUser(@PathVariable String email, @PathVariable String password) {
		return _userService.findByEmailAndPassword(email, password);
	}	
	
	@GetMapping(path = "/findAllByUserType/{userType}")
	public List<UserDTO> findAllByPharmacyIdAndUserType(@PathVariable UserType userType) {
		return _userService.findUserByUserType(userType);
	}	
	

	@GetMapping(path = "/getAllpatients")
	public List<UserDTO> getAllPatients() {
		 return _userService.getAllUsers();
	}
	
	static class NameAndEmployee{
		public String fullName;
		public Long employeeId;
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_DERMATOLOGIST', 'ROLE_PHARMACIST')")
    @PutMapping(value = "/searchUser")
    public ResponseEntity<List<PatientAppointmentDTO>> searchUser(@RequestBody NameAndEmployee ne) {
    	return new ResponseEntity<List<PatientAppointmentDTO>>(_userService.getPatientsByName(ne.fullName, ne.employeeId), HttpStatus.OK);
	}
	

	@PostMapping(value = "/update")
	public void updateUser(@RequestBody User user) throws Exception {
		
		System.out.println(user.getUserId());
		
		_userService.update(user);
		
	}
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PostMapping(value = "/increasePenaltyExamination")
	public ResponseEntity<?> increasePenaltyExamination(@RequestBody Examination e) throws Exception {
		Penalty p = new Penalty(e.getDateAndTime().toString(), PenaltyType.EXAMINATION_MISSED, e.getPharmacy());
		Penalty newPenalty = _penaltyService.save(p);
		_userService.increasePenalty(e.getPatient().getUserId(),newPenalty);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PostMapping(value = "/increasePenaltyConsultation")
	public ResponseEntity<?> increasePenaltyConsultation(@RequestBody Consultation c) throws Exception {
		Penalty p = new Penalty(c.getDateAndTime().toString(), PenaltyType.CONSULTATION_MISSED, c.getPharmacy());
		Penalty newPenalty = _penaltyService.save(p);
		_userService.increasePenalty(c.getPatient().getUserId(), newPenalty);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/subscriptions/{patientId}")
	public ResponseEntity<?> getSubscriptions(@PathVariable Long patientId){
		User user = _userService.findById(patientId);
		Patient p = (Patient) user;
		List<Pharmacy> pharmacies = p.getPharmacies();
		return new ResponseEntity<List<Pharmacy>>(pharmacies, HttpStatus.OK);
	}
	
	//penali se brisu prvog u mjesecu u 00:00
	@Scheduled(cron = "0 0 0 1 * *")
	public void deletePenalties() {
		System.out.println("+++++++++++++++++++++++++++");
		ArrayList<User> allPatients = (ArrayList<User>) _userService.getAllPatients();
		for (User user : allPatients) {
		_userService.deletePenalties(user.getUserId());
		}
	}

}	
