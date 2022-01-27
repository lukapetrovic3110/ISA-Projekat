package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.controller.ExaminationContoller.DataForAppointment;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ExaminationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.ConsultationService;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistVacationService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkSchedulePharmacistService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/consultation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultationController {

	@Autowired
	private ConsultationService _consultationService;
	

	@Autowired
	private PharmacistVacationService _pharmacistVacationService;

	@Autowired
	private UserService _userService;
	
	@Autowired
	private EmailService _emailService;
	
	@Autowired
	private WorkSchedulePharmacistService _workSchedulePharmacist;
	
	static class Request{
		public ConsultationDTO dto;
		public Long patientId;
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PostMapping("/create")
	public ResponseEntity<?> createNewConsultation(@RequestBody Request r) {
		User user = _userService.findById(r.patientId);
		if(!_userService.checkPenalties(r.patientId)) {
			System.out.println("Unable to schedule consultation because of penalties");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Consultation c = this._consultationService.save(r.dto, (Patient) user);
		ArrayList<PharmacistVacation> vacations = (ArrayList<PharmacistVacation>) _pharmacistVacationService.findAllAcceptedVacations();
		this._workSchedulePharmacist.addConsToWorkSchedule(c, vacations);
		this._emailService.sendConsultationConfirmation(c);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST')")
	@GetMapping(value = "/getByPatientId/{patientId}")
	public ResponseEntity<ArrayList<ConsultationViewDTO>> getByPatient(@PathVariable Long patientId){
		ArrayList<ConsultationViewDTO> c = _consultationService.getByPatient(patientId);
		return new ResponseEntity<ArrayList<ConsultationViewDTO>>(c, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getPreviousConsultations/{patientId}")
	public ResponseEntity<ArrayList<ConsultationViewDTO>> getPreviousConsultations(@PathVariable Long patientId){
		ArrayList<ConsultationViewDTO> c = _consultationService.getPreviousConsultations(patientId);
		return new ResponseEntity<ArrayList<ConsultationViewDTO>>(c, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping(value = "/cancel")
	public ResponseEntity<Boolean> cancelConsultation(@RequestBody ConsultationViewDTO consultation) {
		Boolean cancelled =  _consultationService.cancelConsultation(consultation);
		return new ResponseEntity<Boolean>(cancelled, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PutMapping(value = "/createNewConsultation")
	public ResponseEntity<Boolean> createNewExamination(@RequestBody Consultation consultation) {
		
		Calendar examS = Calendar.getInstance();
		examS.setTime(consultation.getDateAndTime());
		examS.add(Calendar.HOUR, -1);
		
		Calendar endExam = Calendar.getInstance(); // creates calendar
		endExam.setTime(examS.getTime());               // sets calendar time/date
		endExam.add(Calendar.MINUTE, 30);
		boolean available = true;
	
		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		Long startDate = start.getTimeInMillis(); 
		
		Long examStart = examS.getTimeInMillis();
		Long examEnd = endExam.getTimeInMillis(); 
		
		ArrayList<Consultation> byPatient = _consultationService.getConsultationsByPatient(consultation.getPatient().getUserId());
		for (Consultation e: byPatient) {
			
			Calendar pS = Calendar.getInstance();
			pS.setTime(e.getDateAndTime());
			
			Calendar pEn = Calendar.getInstance(); // creates calendar
			pEn.setTime(e.getDateAndTime()); // sets calendar time/date
			pEn.add(Calendar.MINUTE, 30);
			
			Long pStart = pS.getTimeInMillis();
			Long pEnd = pEn.getTimeInMillis();
				
			if(examStart <= pStart && examEnd >= pStart) {
				available = false;
				break;
			} else if (examStart <= pEnd && examEnd >= pEnd || startDate > examStart) {
				available = false;
				break;
			}
		}
		if(available) {
			Calendar startConsultation = Calendar.getInstance(); // creates calendar
			startConsultation.setTime(consultation.getDateAndTime());               // sets calendar time/date
			startConsultation.add(Calendar.HOUR, -1);
			
			consultation.setDateAndTime(new Date(startConsultation.getTime().getTime()));
			
			Consultation c = this._consultationService.saveConsultation(consultation);
			ArrayList<PharmacistVacation> vacations = (ArrayList<PharmacistVacation>) _pharmacistVacationService.findAllAcceptedVacations();
			if(!this._workSchedulePharmacist.addConsToWorkSchedule(c, vacations)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
			this._emailService.sendConsultationConfirmation(c);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PutMapping(value = "/allForPharmacist/{id}")
	public ResponseEntity<List<Consultation>> getByPharmacist(@PathVariable Long id, @RequestBody TimeInterval timeInterval) {
		List<Consultation> c = _consultationService.getByPharmacist(id, timeInterval);
		return new ResponseEntity<List<Consultation>>(c, HttpStatus.OK);
	}
	

	static class DataForAppointment{
		public Date dateAndTime;
		public Long pharmId;
		public Long patientId;
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PutMapping(value = "/findCurrentTerm")
	public ResponseEntity<Consultation> findCurrentTerm(@RequestBody DataForAppointment dfa) {
		
		Consultation e = _consultationService.startConsultation(dfa.dateAndTime);
		if(e.getPharmacist() != null && e.getPatient() != null) {
			if(dfa.patientId.equals(e.getPatient().getUserId())
					&& dfa.pharmId.equals(e.getPharmacist().getUserId())) {
					return new ResponseEntity<Consultation>(e, HttpStatus.OK);
			}
		else
			return new ResponseEntity<Consultation>(new Consultation(), HttpStatus.OK);
		}
		
		else return new ResponseEntity<Consultation>(new Consultation(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getAllPharmacistsByPatient/{patientId}")
	public ResponseEntity<ArrayList<Pharmacist>> getAllPharmacistByPatient(@PathVariable Long patientId){
		ArrayList<Pharmacist> p =  _consultationService.getAllPharmacistForPatient(patientId);
		return new ResponseEntity<ArrayList<Pharmacist>>(p, HttpStatus.OK);
	}

}
