package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.NotificationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReportDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.service.ConsultationService;
import rs.ac.uns.ftn.informatika.jpa.service.ExaminationService;
import rs.ac.uns.ftn.informatika.jpa.service.EPrescriptionService;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineItemService;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineService;
import rs.ac.uns.ftn.informatika.jpa.service.NotificationService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacyService;
import rs.ac.uns.ftn.informatika.jpa.service.ReportDermService;
import rs.ac.uns.ftn.informatika.jpa.service.ReportPharmService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/medicine")
public class MedicineController {

	@Autowired
	private MedicineService _medicineService ;
	
	@Autowired 
	private MedicineItemService _medicineItemService;
	
	@Autowired
	private PharmacyService _pharmacyService;
	
	@Autowired
	private NotificationService _notificationService;
	
	@Autowired
	private ReportDermService _reportDermService;
	
	@Autowired
	private ReportPharmService _reportPharmService;
	
	@Autowired
	private ExaminationService _examinationService;
	
	@Autowired
	private ConsultationService _consultationService;

	@Autowired
	private ReservationService _reservationService;
	
	@Autowired
	private EPrescriptionService _ePrescriptionService;
	
	@GetMapping(value = "")
	public ArrayList<Medicine> findAllMedicine(){
		return _medicineService.findAllMedicine();
	}
	@CrossOrigin(origins = "http://localhost:8080")
	
	
	@GetMapping(value = "/getMedicineByName/{name}")
	public ArrayList<Medicine> findMedicineByName(@PathVariable String name){
		return _medicineService.findMedicineByName(name);
	}
	@PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_PHARMACIST', 'ROLE_DERMATOLOGIST')")
	@PutMapping(value = "/forAllergies")
	public ResponseEntity<ArrayList<Medicine>> findAllMedicineForAllergies(@RequestBody Allergy allergy){
		ArrayList<Medicine> m = _medicineService.findAllMedicineForAllergies(allergy);
		return new ResponseEntity<ArrayList<Medicine>>(m,HttpStatus.OK);
	}

	@PostMapping("/addMedicine")
    public ResponseEntity<?> addNewMedicine(@RequestBody MedicineRegistrationDTO medicineRegistration)
    {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		try {
			  return new ResponseEntity<>(this._medicineService.addNewMedicine(medicineRegistration), HttpStatus.CREATED);
			} catch (Exception e) { 
				return new ResponseEntity<>(e.getMessage() + " ovde je propalo", HttpStatus.BAD_REQUEST);
			}
    }
	
	
	static class CheckAvailability{
		public Long pharmacyId;
		public Medicine medicineAvailable;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_DERMATOLOGIST', 'ROLE_PHARMACIST')")
	@PutMapping(value = "/checkAvailability")
	public ResponseEntity<Boolean> checkAvailability(@RequestBody CheckAvailability ca) {
		
		Pharmacy pharmacy = _pharmacyService.findById(ca.pharmacyId);
			
		Set<MedicineItem> medicineItems =  pharmacy.getMedicineItem();
	
		for(MedicineItem m : medicineItems) {
			if (m.getMedicine().getMedicineId() == ca.medicineAvailable.getMedicineId() && m.getQuantity() > 0) {
				this._medicineItemService.saveQuantityMedicineItem(m);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		
		NotificationDTO n = new NotificationDTO();
		n.setContent(ca.medicineAvailable.getName() + " is not available.");
		n.setPharmacyId(pharmacy.getPharmacyId());
		this._notificationService.saveNotification(n);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PostMapping(value = "/addReportDerm/{id}")
	public ResponseEntity<ReportDTO> addReportDerm(@RequestBody ReportDTO reportDTO, @PathVariable Long id) {
		_examinationService.endExamination(id);
		_reportDermService.saveReportDerm(reportDTO);
		return new ResponseEntity<ReportDTO>(reportDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PostMapping(value = "/addReportPharm/{id}")
	public ResponseEntity<ReportDTO> addReportPharm(@RequestBody ReportDTO reportDTO, @PathVariable Long id) {
		_consultationService.endConsultation(id);
		_reportPharmService.saveReportPharm(reportDTO);
		return  new ResponseEntity<ReportDTO>(reportDTO, HttpStatus.OK);
	}
	
	//Sl dvije metode su ako je pregled u toku i zelimo da zakazemo novi, prethodno cuvamo report
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PostMapping(value = "/addReportDermAndSchedule/{id}")
	public ResponseEntity<Examination> addReportDermAndSchedule(@RequestBody ReportDTO reportDTO, @PathVariable Long id) {
		_examinationService.endExamination(id);
		_reportDermService.saveReportDerm(reportDTO);
		
		Examination e = _examinationService.findById(id);
		if(e != null) {
			return new ResponseEntity<Examination>(e, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Examination>(new Examination(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PostMapping(value = "/addReportPharmAndSchedule/{id}")
	public ResponseEntity<Consultation> addReportPharmAndSchedule(@RequestBody ReportDTO reportDTO, @PathVariable Long id) {
		_consultationService.endConsultation(id);
		_reportPharmService.saveReportPharm(reportDTO);
		
		Consultation c = _consultationService.findById(id);
		if(c != null) {
			return new ResponseEntity<Consultation>(c, HttpStatus.OK);
		}
		return new ResponseEntity<Consultation>(new Consultation(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@PostMapping(value = "/endExam/{id}")
	public ResponseEntity<Examination> endExam( @PathVariable Long id) {
		Examination e = _examinationService.findById(id);
		if(e != null) {
			_examinationService.endExamination(id);
			return new ResponseEntity<Examination>(e, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Examination>(new Examination(), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	@PostMapping(value = "/endCons/{id}")
	public ResponseEntity<Consultation> endCons( @PathVariable Long id) {
		Consultation c = _consultationService.findById(id);
		if(c != null) {
			_consultationService.endConsultation(id);
			return new ResponseEntity<Consultation>(c, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Consultation>(new Consultation(), HttpStatus.OK);
		
	}
	
	static class SubstitutesWithoutAllergy{
		public Medicine oldMedicine;
		public HashSet<Medicine> medicinesWithoutAllergy;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_DERMATOLOGIST', 'ROLE_PHARMACIST')")
	@PutMapping(value = "/substituteMedicine")
	public ResponseEntity<HashSet<Medicine>>findAllSubstituteMedicine(@RequestBody SubstitutesWithoutAllergy swa){
		
		HashSet<Medicine> substituteMedicines = new HashSet<Medicine>();
		HashSet<Medicine> allSubstitutes = (HashSet<Medicine>) swa.oldMedicine.getReplacementMedicine();
		boolean found = false;
		for (Medicine m : allSubstitutes) {
			for(Medicine m2: swa.medicinesWithoutAllergy) {
				if (m2.getMedicineId() == m.getMedicineId())
					found = true;
				break;
			}		
		
			if(!found)
			{
			substituteMedicines.add(m);
			}
			found = false;
			
		}
		
		return new ResponseEntity<HashSet<Medicine>>(substituteMedicines, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/filtrate/{rating}")
	public List<Medicine> filtrateMedicine(@PathVariable int rating){
		
		List<Medicine> medicines = findAllMedicine();
		
		List<Medicine> filtrateMedicines = new ArrayList<>();

		for(Medicine m : medicines) {
			if(m.getRating() == rating) {
				filtrateMedicines.add(m);				}
		}
		
		return filtrateMedicines; 
	}
	
	@GetMapping(path = "/checkMedicineInPharmacy/{name}")
	public List<MedicineAvailableInPharmacyDTO> findAvailableMedicineInPharmacy(@PathVariable String name){	
		return _medicineService.findPharmacyForMedicineItem(name); 
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(path = "/getMedicineForRating/{patientId}")
	public ResponseEntity<ArrayList<Medicine>> getMedicineForRating(@PathVariable Long patientId){	
		ArrayList<Medicine> result = new ArrayList<Medicine>();
		_reservationService.getMedicineForRating(patientId, result);
		_ePrescriptionService.getMedicineForRating(patientId, result);
		return new ResponseEntity<ArrayList<Medicine>>(result, HttpStatus.OK);
	}
	
}
