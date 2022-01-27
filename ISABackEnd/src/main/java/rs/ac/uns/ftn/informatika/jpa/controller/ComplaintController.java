package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

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
import org.springframework.web.util.UriComponentsBuilder;

import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintForPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintEmployeeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintPharmacy;
import rs.ac.uns.ftn.informatika.jpa.service.ComplaintService;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {

	@Autowired
	public ComplaintService _complaintService;
	
	@Autowired
	public EmailService _emailService;
	
	
	@PostMapping("/createComplaint")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<?> createComplaint(@RequestBody ComplaintEmployeeDTO complaintEmployeeDTO, UriComponentsBuilder ucBuilder) {
		try {
			return new ResponseEntity<>(this._complaintService.createComplaint(complaintEmployeeDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() + " ovde je propalo", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/createComplaintForPharmacy")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<?> createComplaintForPharmacy(@RequestBody ComplaintPharmacyDTO complaintPharmacyDTO, UriComponentsBuilder ucBuilder) {
		try {
			return new ResponseEntity<>(this._complaintService.createComplaintForPharmacy(complaintPharmacyDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getComplaintsEmployee")
	@PreAuthorize("hasAnyRole('ROLE_SYS_ADMIN', 'ROLE_PATIENT')")
	public ArrayList<ComplaintEmployee> getComplaintsEmployee(){
		return _complaintService.getComplaintsEmployee();
	}
	
	@GetMapping(value = "/getComplaintsPharmacy")
	@PreAuthorize("hasAnyRole('ROLE_SYS_ADMIN', 'ROLE_PATIENT')")
	public ArrayList<ComplaintPharmacy> getComplaintsPharmacy(){
		return _complaintService.getComplaintsPharmacy();
	}
	
	@PostMapping("/answerOnComplaintForEmployee")
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN)')")
	public ResponseEntity<?> answerOnComplaintForEmployee(@RequestBody AnswerOnComplaintDTO answerOnComplaintDTO, UriComponentsBuilder ucBuilder) {
		try {
			_emailService.sendAnswerOnComplaintEmailAsync(answerOnComplaintDTO);
			return new ResponseEntity<>(this._complaintService.answerOnComplaintForEmployee(answerOnComplaintDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/answerOnComplaintForPharmacy")
	@PreAuthorize("hasRole('ROLE_SYS_ADMIN)')")
	public ResponseEntity<?> answerOnComplaintForPharmacy(@RequestBody AnswerOnComplaintForPharmacyDTO answerOnComplaintDTO, UriComponentsBuilder ucBuilder) {
		try {
			_emailService.sendAnswerOnComplaintPharmacyEmailAsync(answerOnComplaintDTO);
			return new ResponseEntity<>(this._complaintService.answerOnComplaintForPharmacy(answerOnComplaintDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
