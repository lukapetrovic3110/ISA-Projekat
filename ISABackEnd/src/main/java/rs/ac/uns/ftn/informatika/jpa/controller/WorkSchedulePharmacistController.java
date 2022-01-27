package rs.ac.uns.ftn.informatika.jpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AppointmentDateAndTimeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.CreateWorkSchedulePharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.WorkSchedulePharmacist;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacistVacationService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkSchedulePharmacistService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/workSchedulePharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkSchedulePharmacistController {
	@Autowired
	private WorkSchedulePharmacistService _workSchedulePharmacist;
	
	@Autowired
	private PharmacistVacationService _pharmacistVacationService;
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping("/getAvailablePharmacies")
	public ResponseEntity<ArrayList<Pharmacy>> getAvailablePharmacies(@RequestBody AppointmentDateAndTimeDTO dto) {
		String d = dto.getDate() + " " +  dto.getTime() + ":00";
	    Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			System.out.println(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ArrayList<Pharmacy> p = new ArrayList<Pharmacy>();
			return new ResponseEntity<ArrayList<Pharmacy>>(p, HttpStatus.OK);
		}  
		
		ArrayList<PharmacistVacation> vacations = (ArrayList<PharmacistVacation>) _pharmacistVacationService.findAllAcceptedVacations();
		ArrayList<Pharmacy> p =  _workSchedulePharmacist.getAvailablePharmacies(date, vacations);
		return new ResponseEntity<ArrayList<Pharmacy>>(p, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping("/checkDate")
	public ResponseEntity<Boolean> checkDate(@RequestBody AppointmentDateAndTimeDTO dto) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); 
		String d = dto.getDate() + " " +  dto.getTime() + ":00";
	    Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			System.out.println(date.toString());
			if(date.before(cal.getTime())) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			
		}  
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping("/getAvailablePharmacistsInPharmacy")
	public ResponseEntity<ArrayList<Pharmacist>> getAvalibalePharmacistsInPharmacy(@RequestBody ConsultationDTO dto){
		String d = dto.getDate() + " " +  dto.getTime() + ":00";
	    Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			System.out.println(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ArrayList<Pharmacist> p = new ArrayList<Pharmacist>();
			return new ResponseEntity<ArrayList<Pharmacist>>(p, HttpStatus.OK);
		}  
		long id = Long.parseLong(dto.getPharmacyId());
		ArrayList<PharmacistVacation> vacations = (ArrayList<PharmacistVacation>) _pharmacistVacationService.findAllAcceptedVacations();
		ArrayList<Pharmacist> p =  _workSchedulePharmacist.getAvailablePharmacistsInPharmacy(date, id, vacations);
		return new ResponseEntity<ArrayList<Pharmacist>>(p, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/createPharmacistWorkSchedule")
	public ResponseEntity<WorkSchedulePharmacist> createPharmacistWorkSchedule(@RequestBody CreateWorkSchedulePharmacistDTO createWorkSchedulePharmacistDTO) {
		return new ResponseEntity<WorkSchedulePharmacist>(_workSchedulePharmacist.createPharmacistWorkSchedule(createWorkSchedulePharmacistDTO), HttpStatus.CREATED);
	}

}
