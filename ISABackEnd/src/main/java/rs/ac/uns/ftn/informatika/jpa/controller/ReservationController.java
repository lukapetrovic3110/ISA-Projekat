package rs.ac.uns.ftn.informatika.jpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AppointmentDateAndTimeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ExaminationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineItemService;
import rs.ac.uns.ftn.informatika.jpa.service.PenaltyService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacyService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

	@Autowired
	private ReservationService _reservationService;

	@Autowired
	private PharmacyService _pharmacyService;
	
	@Autowired
	private EmailService _emailService;
	
	@Autowired
	private UserService _userService;
	
	@Autowired
	private PenaltyService _penaltyService;
	
	@Autowired
	private MedicineItemService _medicineItemService;
	
	static class ResChecker {
		public String deadlineDate;
		public String resCode;
		public Long pharmacyId;
	}
	
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PostMapping("create")
	@Transactional
	public ResponseEntity<?> createReservation(@RequestBody ReservationDTO dto) {
		User user = _userService.findById(dto.getUserId());
		if(!_userService.checkPenalties(dto.getUserId())) {
			System.out.println("Unable to make a reservation because of penalties");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Reservation reservation = _reservationService.createReservation(dto,(Patient) user);
		_medicineItemService.findMedicineItmeAndChangeQuantity(dto.getDto());
		_emailService.sendReservationMadeEmail(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getByPatientId/{patientId}")
	public ResponseEntity<ArrayList<ReservationViewDTO>> getByPatient(@PathVariable Long patientId){
		ArrayList<ReservationViewDTO> r = _reservationService.getByPatient(patientId);
		return new ResponseEntity<ArrayList<ReservationViewDTO>>(r, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PutMapping("/findReservation")
	@Transactional
	public ResponseEntity<Reservation> findReservationByCode(@RequestBody ResChecker resChecker) throws ParseException {
		
		Boolean reservationInPharmacy = false;
		Pharmacy pharmacy = new Pharmacy();
		Reservation r =  _reservationService.findReservationByCode(resChecker.resCode);
		
		if(r != null) {
		
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 24); 
		
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resChecker.deadlineDate);
		
		if(!date.before(cal.getTime())) {
			reservationInPharmacy = false;	
		}
				
		else {
			pharmacy = _pharmacyService.findById(resChecker.pharmacyId);
			if(r.getPharmacy().getPharmacyId().equals(pharmacy.getPharmacyId())) {
				reservationInPharmacy = true;				
			}
		}
		
		if (r != null && reservationInPharmacy) {
			System.out.println(r);
			if(r.getRecieved() != true) {
				
				_emailService.sendRecievedMedicineEmail(r.getReservationCode(), pharmacy, r.getPatient());
				return new ResponseEntity<Reservation>(_reservationService.updateReservation(r), HttpStatus.OK);
			}
			return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
		}
		}
		return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping(value = "/cancel")
	@Transactional
	public ResponseEntity<Boolean> cancelReservation(@RequestBody ReservationViewDTO reservation) {
		_medicineItemService.findMedicineItemAndIncreaseQuantity(reservation);
		Boolean cancelled = _reservationService.cancelReservation(reservation);
		return new ResponseEntity<Boolean>(cancelled,HttpStatus.OK);
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void checkForPenalties() {
		ArrayList<User> allPatients = (ArrayList<User>) _userService.getAllPatients();
		for (User user : allPatients) {
		ArrayList<Penalty> penalties = _reservationService.checkForPenatlies(user.getUserId());
		for (Penalty penalty : penalties) {
			Penalty p = _penaltyService.save(penalty);
			_userService.increasePenalty(user.getUserId(), p);
			
		}
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping(value="/checkDate")
	public ResponseEntity<Boolean> checkDate(@RequestBody ReservationDTO dto) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDate());
		Calendar now = Calendar.getInstance(); // creates calendar
		now.setTime(new Date()); 
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		
			if(d.before(now)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}  
	}

}
