package rs.ac.uns.ftn.informatika.jpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ExaminationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;
import rs.ac.uns.ftn.informatika.jpa.service.DermatologistVacationService;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.ExaminationService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistVacationService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleDermatologistService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/examination")
public class ExaminationContoller {
	
	@Autowired
	ExaminationService _examinationService;
	
	@Autowired
	private EmailService _emailService;
	

	@Autowired
	private DermatologistVacationService _dermatologistVacationService;
	
	@Autowired
	private UserService _userService;

	@Autowired
	private WorkScheduleDermatologistService _workScheduleDermatologist;
	
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_DERMATOLOGIST')")
	@GetMapping(value = "/getByPharmacy/{pharmacyId}")
	public ResponseEntity<ArrayList<ExaminationDTO>> getByPharmacy(@PathVariable Long pharmacyId){
		ArrayList<ExaminationDTO> e = _examinationService.getByPharmacy(pharmacyId);
		return new ResponseEntity<ArrayList<ExaminationDTO>>(e, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getPreviousExaminations/{patientId}")
	public ResponseEntity<ArrayList<ExaminationDTO>> getPreviousExaminations(@PathVariable Long patientId){
		ArrayList<ExaminationDTO> e = _examinationService.getPreviousExaminations(patientId);
		return new ResponseEntity<ArrayList<ExaminationDTO>>(e, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getByPatientId/{patientId}")
	public ResponseEntity<ArrayList<ExaminationDTO>> getByPatient(@PathVariable Long patientId){
		ArrayList<ExaminationDTO> e = _examinationService.getByPatient(patientId);
		return new ResponseEntity<ArrayList<ExaminationDTO>>(e, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_DERMATOLOGIST', 'ROLE_PHARMACIST')")
	@PutMapping(value = "/schedule")
	@Transactional
	public ResponseEntity<?> scheduleExamination(@RequestBody ExaminationDTO examination) {
		if(!_userService.checkPenalties(examination.getPatient().getUserId())) {
			System.out.println("Unable to schedule examination because of penalties");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Examination e = _examinationService.scheduleExamination(examination);
		_emailService.sendExaminationConfirmation(e);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping(value = "/cancel")
	public boolean cancelExamination(@RequestBody ExaminationDTO examination) {
		return _examinationService.cancelExamination(examination);
	}
	
	static class DataForTerm{
		public Long pharmacyId;
		public Long dermatologistId;
		public Long patientId;
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")	
	@PutMapping(value = "/findValidNextTerm")
	public ResponseEntity<ArrayList<ExaminationDTO>> findValidNextTerm(@RequestBody DataForTerm dataForTerm){
			
		boolean available = true;
		ArrayList<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
		ArrayList<ExaminationDTO> byPharmacy = _examinationService.getByPharmacy(dataForTerm.pharmacyId);
		ArrayList<ExaminationDTO> byDermatologist = _examinationService.getByDermatologist(dataForTerm.dermatologistId);
		ArrayList<ExaminationDTO> byPatient = _examinationService.getByPatient(dataForTerm.patientId);
		
		for(ExaminationDTO e1: byPharmacy) {
			for(ExaminationDTO e2: byDermatologist) {
				for(ExaminationDTO e3: byPatient) {
					if(e1.getAppointmentId().equals(e2.getAppointmentId())){
						
						String d2 = e2.getDateAndTime() + ":00";
					    Date date2 = new Date();
						try {
							date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d2);
							System.out.println(date2.toString());
						} catch (ParseException e) {
							e.printStackTrace();
						}  
						
						String d3 = e3.getDateAndTime() + ":00";
					    Date date3 = new Date();
						try {
							date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d3);
							System.out.println(date3.toString());
						} catch (ParseException e) {
							e.printStackTrace();
						}  
						
						Calendar examS = Calendar.getInstance();
						examS.setTime(date2);
						
						Calendar endExam = Calendar.getInstance(); // creates calendar
						endExam.setTime(examS.getTime());               // sets calendar time/date
						endExam.add(Calendar.MINUTE, 30);
						
						Long examStart = examS.getTimeInMillis();
						Long examEnd = endExam.getTimeInMillis(); 
						
						Calendar pS = Calendar.getInstance();
						pS.setTime(date3);
						
						Calendar pEn = Calendar.getInstance(); // creates calendar
						pEn.setTime(pS.getTime()); // sets calendar time/date
						pEn.add(Calendar.MINUTE, 30);
						
						Long pStart = pS.getTimeInMillis();
						Long pEnd = pEn.getTimeInMillis();
						
					
						if(examStart <= pStart && examEnd >= pStart) {
							available = false;
							break;
						} else if (examStart <= pEnd && examEnd >= pEnd) {
							available = false;
							break;
						}
					
						if(available) {
							result.add(e1);
						}	
					}
				}
			}
		}
		return new ResponseEntity<ArrayList<ExaminationDTO>>(result, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PutMapping(value = "/createNewExamination")
	public ResponseEntity<Boolean> createNewExamination(@RequestBody Examination examination) {
		
		Calendar examS = Calendar.getInstance();
		examS.setTime(examination.getDateAndTime());
		
		Calendar endExam = Calendar.getInstance(); // creates calendar
		endExam.setTime(examS.getTime());               // sets calendar time/date
		endExam.add(Calendar.MINUTE, 30);
		boolean available = true;
		
		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		Long startDate = start.getTimeInMillis(); 
		
		System.out.println(examS.getTime().toString());
		
		Long examStart = examS.getTimeInMillis();
		Long examEnd = endExam.getTimeInMillis(); 
		
		ArrayList<Examination> byPatient = _examinationService.getExaminationsByPatient(examination.getPatient().getUserId());
		for (Examination e: byPatient) {
			
			Calendar pS = Calendar.getInstance();
			pS.setTime(e.getDateAndTime());
			
			Calendar pEn = Calendar.getInstance(); // creates calendar
			pEn.setTime(e.getDateAndTime()); // sets calendar time/date
			pEn.add(Calendar.MINUTE, 30);
			
			Long pStart = pS.getTimeInMillis();
			Long pEnd = pEn.getTimeInMillis();
			
			System.out.println(pS.getTime().toString());
			System.out.println(pEn.getTime().toString());
			System.out.println(examStart + " " +  examEnd + " " + pStart + " " + pEnd);
			
			if(examStart <= pStart && examEnd >= pStart || startDate > examStart) {
				available = false;
				break;
			} else if (examStart <= pEnd && examEnd >= pEnd) {
				available = false;
				break;
			}
		}
		if(available) {
			Calendar startExamination = Calendar.getInstance(); // creates calendar
			startExamination.setTime(examination.getDateAndTime());               // sets calendar time/date
			startExamination.add(Calendar.HOUR, -1);
			
			examination.setDateAndTime(new Date(startExamination.getTime().getTime()));
			ArrayList<DermatologistVacation> vacations = (ArrayList<DermatologistVacation>) _dermatologistVacationService.findAllAcceptedVacations();
			if(!this._workScheduleDermatologist.addNewExaminationToWorkSchedule(examination, vacations)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
			this._emailService.sendExaminationConfirmation(examination);
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	static class DataForAppointment{
		public Date dateAndTime;
		public Long dermId;
		public Long patientId;
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PutMapping(value = "/findCurrentTerm")
	public ResponseEntity<Examination> findCurrentTerm(@RequestBody DataForAppointment dfa) {
		
		Examination e = _examinationService.startExamination(dfa.dateAndTime);
		if(e.getDermatologist() != null && e.getPatient() != null) {
			if(dfa.patientId.equals(e.getPatient().getUserId())
					&& dfa.dermId.equals(e.getDermatologist().getUserId())) {
					return new ResponseEntity<Examination>(e, HttpStatus.OK);
			}
		else
			return new ResponseEntity<Examination>(e, HttpStatus.OK);
		}
		
		else return new ResponseEntity<Examination>(new Examination(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	@GetMapping(value = "/getAllDermatologistByPatient/{patientId}")
	public ArrayList<Dermatologist> getAllDermatologistByPatient(@PathVariable Long patientId){
		return _examinationService.getAllDermatologistByPatient(patientId);
	}
	
	static class DataForPharmacies{
		public TimeInterval timeInterval;
		public Long pharmacyId;
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PutMapping(value = "/allForDermatologist/{id}")
	public ResponseEntity<List<Examination>> getByDermatologist(@PathVariable Long id, @RequestBody DataForPharmacies dfp) {
		 List<Examination> allExam = _examinationService.getByDermatologist(id, dfp.timeInterval);
		 List<Examination> result = new ArrayList<Examination>();
		 
		 for(Examination e: allExam) {
			 if(dfp.pharmacyId.equals(e.getPharmacy().getPharmacyId())) {
				 result.add(e);
			 }
		 }
		 
		 return new ResponseEntity<List<Examination>>(result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/findAllScheduledExaminationInPharmacyByDermatologist/{dermatologistId}")
	public ResponseEntity<List<ExaminationDTO>> findAllScheduledExaminationInPharmacyByDermatologist(@PathVariable Long dermatologistId) {
		return new ResponseEntity<List<ExaminationDTO>>(_examinationService.findAllScheduledExaminationInPharmacyByDermatologist(dermatologistId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/createFreeTermExamination")
	public ResponseEntity<ResponseFreeTermDTO> createFreeTermExaminationForDermatologist(@RequestBody CreateFreeTermDTO createFreeTermDTO) {
		try {
			return new ResponseEntity<ResponseFreeTermDTO>(_examinationService.createFreeTermExaminationForDermatologist(createFreeTermDTO), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<ResponseFreeTermDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path = "/findAllFreeTerm")
	public ResponseEntity<List<PharmacyFreeTermDTO>> findAllFreeTermExamination() {
		return new ResponseEntity<List<PharmacyFreeTermDTO>>(_examinationService.findAllFreeTermExamination(), HttpStatus.OK);
	}
	
}
