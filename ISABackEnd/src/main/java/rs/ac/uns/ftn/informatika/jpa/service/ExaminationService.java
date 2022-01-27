package rs.ac.uns.ftn.informatika.jpa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ExaminationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IExaminationService;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;
import rs.ac.uns.ftn.informatika.jpa.model.WorkScheduleDermatologist;
import rs.ac.uns.ftn.informatika.jpa.repository.IExaminationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IWorkScheduleDermatologistRepository;

@Service
public class ExaminationService implements IExaminationService{
	
	private IExaminationRepository _examinationRepository;
	private IWorkScheduleDermatologistRepository _workScheduleDermatologistRepository;
	private DermatologistVacationService _dermatologistVacationService;
	@Autowired
	public ExaminationService(IExaminationRepository examinationRepository, IWorkScheduleDermatologistRepository workScheduleDermatologistRepository, DermatologistVacationService dermatologistVacationService) {
		this._examinationRepository = examinationRepository;
		this._workScheduleDermatologistRepository = workScheduleDermatologistRepository;
		this._dermatologistVacationService = dermatologistVacationService;
	}
	
	@Override
	public ArrayList<ExaminationDTO> getByPharmacy(Long pharmacyId) {
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
		
		for (Examination examination : allExaminations) { 
			if(examination.getDateAndTime().after(new Date()) && examination.getPharmacy().getPharmacyId() == pharmacyId) {
				if(examination.getPatient() == null || examination.getCancelled() == true) {
			result.add(new ExaminationDTO(examination.getAppointmentId(),examination.getDateAndTime().toString(),
					examination.getDuration(),examination.getPrice(),examination.getPoints(),examination.getDermatologist(),
					null, examination.getPharmacy()));
				}
			}
		}
		return result;
	}

	@Override
	public Examination scheduleExamination(ExaminationDTO examination) {
		Optional<Examination> oldExamination = _examinationRepository.findById(examination.getAppointmentId());
		Examination e = oldExamination.get();
		e.setPatient(examination.patient);
		e.setCancelled(false);
		return _examinationRepository.save(e);
		
	}

	//metoda koja dobavalja zakazane preglede koji se jos nisu odrzali
	@Override
	public ArrayList<ExaminationDTO> getByPatient(Long patientId) {
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
		
		for (Examination examination : allExaminations) { 
			if(examination.getDateAndTime().after(new Date()) && examination.getPatient() != null) {
				if(examination.getPatient().getUserId() == patientId && examination.getCancelled() == false) {
			result.add(new ExaminationDTO(examination.getAppointmentId(),examination.getDateAndTime().toString(),
					examination.getDuration(),examination.getPrice(),examination.getPoints(),examination.getDermatologist(),
					examination.getPatient(), examination.getPharmacy()));
				}
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<Examination> getExaminationsByPatient(Long patientId) {
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<Examination> result = new ArrayList<Examination>();
		
		for (Examination examination : allExaminations) { 
			if(examination.getDateAndTime().after(new Date()) && examination.getPatient() != null) {
				if(examination.getPatient().getUserId() == patientId && examination.getCancelled() == false) {
			result.add(examination);
				}
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<ExaminationDTO> getByDermatologist(Long dermatologistId){
		
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
		
		for (Examination examination : allExaminations) { 
			if(examination.getDateAndTime().after(new Date()) &&
			   examination.getDermatologist().getUserId().equals(dermatologistId)) {
				if(examination.getPatient() == null || examination.getCancelled() == true) {
					result.add(new ExaminationDTO(examination.getAppointmentId(),examination.getDateAndTime().toString(),
							examination.getDuration(),examination.getPrice(),examination.getPoints(),examination.getDermatologist(),
							null, examination.getPharmacy()));
				}
			}
		}
		return result;	
	}

	@Override
	public boolean cancelExamination(ExaminationDTO examination) {
		Optional<Examination> oldExamination = _examinationRepository.findById(examination.getAppointmentId());
		Examination e = oldExamination.get();
		if(isSoonerThan24hours(e)) {
			return false;
		}
		e.setCancelled(true);
		_examinationRepository.save(e);
		return true;
	}

	@Override
	public boolean isSoonerThan24hours(Examination examination) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 24);      
		
		if(examination.getDateAndTime().before(cal.getTime())) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Examination> findAllExamination(){
		return (ArrayList<Examination>) _examinationRepository.findAll();
	}
	
	
	@Override
	public Examination save(ExaminationDTO dto) {
		String d = dto.getDateAndTime() + ":00";
	    Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			System.out.println(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Examination examination = new Examination();
		examination.setAppointmentStatus(AppointmentStatus.NONE);
		examination.setCancelled(false);
		examination.setDateAndTime(date);
		examination.setDermatologist(dto.getDermatologist());
		examination.setPatient(dto.getPatient());
		examination.setPrice(dto.getPrice());
		examination.setPharmacy(dto.getPharmacy());
		examination.setDuration(30);
		return this._examinationRepository.save(examination);
		
	}
	
	@Override
	public Examination saveExamination(Examination e) {
		
		Examination examination = new Examination();
		examination.setAppointmentStatus(AppointmentStatus.NONE);
		examination.setCancelled(false);
		examination.setDateAndTime(e.getDateAndTime());
		examination.setDermatologist(e.getDermatologist());
		examination.setPatient(e.getPatient());
		examination.setPrice(e.getPrice());
		examination.setPharmacy(e.getPharmacy());
		examination.setDuration(30);
		return this._examinationRepository.save(examination);
		
	}

	@Override
	public void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result) {
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		for (Examination examination : allExaminations) {
			if(examination.getPatient() != null) {
				if(examination.getPatient().getUserId() == patientId && examination.getAppointmentStatus() == AppointmentStatus.FINISHED) {
					if(!result.contains(examination.getPharmacy())) {
						result.add(examination.getPharmacy());
					}
				}
			}
		}
	}
	
	@Override
	public Examination startExamination(Date date) {
		
		Calendar eS = Calendar.getInstance();
		eS.setTime(date);
		eS.add(Calendar.MINUTE, -15);
		
		Calendar eE = Calendar.getInstance(); // creates calendar
		eE.setTime(eS.getTime());               // sets calendar time/date
		eE.add(Calendar.MINUTE, 30);
		
		Long eStart = eS.getTimeInMillis();
		Long eEnd = eE.getTimeInMillis(); 
		
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		for(Examination e: allExaminations) {
			Calendar examS = Calendar.getInstance();
			examS.setTime(e.getDateAndTime());
			
			Long examStart = examS.getTimeInMillis();
			
			if(examStart >= eStart && examStart < eEnd && !e.getAppointmentStatus().equals(AppointmentStatus.FINISHED)) {
				e.setAppointmentStatus(AppointmentStatus.STARTED);
				this._examinationRepository.save(e);
				return e;
			}
		}
		return new Examination();
	}
	
	@Override
	public Examination endExamination(Long id) {
		
		Examination e = findById(id);
		
			if(e.getAppointmentStatus().equals(AppointmentStatus.STARTED)){
				e.setAppointmentStatus(AppointmentStatus.FINISHED);
				this._examinationRepository.save(e);
				return e;
		
		}
		return null;
	}

	@Override
	public Examination findById(Long id) {
		return _examinationRepository.findById(id).orElse(null);
	}
	
	public ArrayList<Dermatologist> getAllDermatologistByPatient(Long patientId) {
		
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<Dermatologist> result = new ArrayList<Dermatologist>();
		
		for(Examination examination: allExaminations) {
			if(examination.getPatient() != null) {
				if(examination.getPatient().getUserId() == patientId && examination.getAppointmentStatus() == AppointmentStatus.FINISHED) {
					if(!result.contains(examination.getDermatologist())) {
						result.add(examination.getDermatologist());
					}	
				}
			}
		}
		return result;	
	}

	@Override
	public ArrayList<ExaminationDTO> getPreviousExaminations(Long patientId) {
		ArrayList<Examination> allExaminations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
		
		for (Examination examination : allExaminations) {
			if(examination.getPatient() != null) {
				if(examination.getPatient().getUserId() == patientId && examination.getAppointmentStatus() == AppointmentStatus.FINISHED) {
					result.add(new ExaminationDTO(examination.getAppointmentId(),examination.getDateAndTime().toString(),
							examination.getDuration(),examination.getPrice(),examination.getPoints(),examination.getDermatologist(),
							null, examination.getPharmacy()));
				}
			}
		}
		
		return result;
	}
	
	@Override
	public List<Examination> getByDermatologist(Long id, TimeInterval timeInterval){
		
		List<Examination> all = _examinationRepository.findAll();
		List<Examination> result = new ArrayList<>();
		
		
		for(Examination e: all) {
			if(e.getDermatologist() != null) {
				if(id.equals(e.getDermatologist().getUserId())) {
					if(e.getDateAndTime().after(timeInterval.getStartDate()) && e.getDateAndTime().before(timeInterval.getEndDate())) {
						result.add(e);
					}
				}
			}
		}
		
		return result;
	}
	
	public List<ExaminationDTO> findAllScheduledExaminationInPharmacyByDermatologist(Long dermatologistId) {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Examination> allExamination = _examinationRepository.findAll();
		List<ExaminationDTO> list = new ArrayList<ExaminationDTO>();
	
		for(Examination e : allExamination) {
			if (e.getDermatologist().getUserId() == dermatologistId
					&& e.getPharmacy().getPharmacyId() == pAdmin.getPharmacy().getPharmacyId()) {
				ExaminationDTO examinationDTO = new ExaminationDTO(e.getAppointmentId(),e.getDateAndTime().toString(), e.getDuration(), e.getPrice(), e.getPoints(), e.getDermatologist(), e.getPatient(), e.getPharmacy());
				list.add(examinationDTO);
			}
		}
	
	return list;
	}
	
	@Override
	public ResponseFreeTermDTO createFreeTermExaminationForDermatologist(CreateFreeTermDTO createFreeTermDTO) {
		
		List<DermatologistVacation> allDermatologistAcceptedVacation = _dermatologistVacationService.findAllDermatologistVacationWithStatusWaitingByDermatologistId(createFreeTermDTO.getDermatologist().getUserId());
		
		if (allDermatologistAcceptedVacation.size() > 0)
			for (DermatologistVacation dVacation : allDermatologistAcceptedVacation) 
				if (dVacation.getStartDate().before(createFreeTermDTO.getDateAndTimeExamination())
						&& dVacation.getEndDate().after(createFreeTermDTO.getDateAndTimeExamination())) {
							return new ResponseFreeTermDTO(false, "Try to create another term, the dermatologist is on vacation during this term!");
				}
		
		List<Examination> allExaminations = _examinationRepository.findAll();

		for (Examination e : allExaminations) {
			
			if (e.getDermatologist().getUserId() == createFreeTermDTO.getDermatologist().getUserId()
					&& e.getPharmacy().getPharmacyId() == createFreeTermDTO.getPharmacy().getPharmacyId()) {
				
				Calendar startExamination = Calendar.getInstance();
				startExamination.setTime(e.getDateAndTime());
				Long examinationStart = startExamination.getTimeInMillis();
				
				Calendar endExamination = Calendar.getInstance();
				endExamination.setTime(e.getDateAndTime());
				endExamination.add(Calendar.MINUTE, e.getDuration());
				Long examinationEnd = endExamination.getTimeInMillis();
				
				
				Calendar startfreeTerm = Calendar.getInstance();
				startfreeTerm.setTime(createFreeTermDTO.getDateAndTimeExamination());		
				Long freeTermStart = startfreeTerm.getTimeInMillis();
				
				
				Calendar endfreeTerm = Calendar.getInstance();
				endfreeTerm.setTime(createFreeTermDTO.getDateAndTimeExamination());	
				endfreeTerm.add(Calendar.MINUTE, Integer.parseInt(createFreeTermDTO.getDuration()));
				Long freeTermEnd = startfreeTerm.getTimeInMillis();
				
				
				// I - u sredini trajanja zakazanog pregleda
				if (examinationStart <= freeTermStart && examinationEnd >= freeTermEnd) {
					return new ResponseFreeTermDTO(false, "Try again with another term, the dermatologist has already scheduled an examination for the selected date and time!");
				}
				// II - okruzi vec zakazan pregled
				if (examinationStart >= freeTermStart && examinationEnd <= freeTermEnd) {
					return new ResponseFreeTermDTO(false, "Try again with another term, the dermatologist has already scheduled an examination for the selected date and time!");
				}
				// III
				if (examinationStart >= freeTermStart && examinationStart <= freeTermEnd) {
					return new ResponseFreeTermDTO(false, "Try again with another term, the dermatologist has already scheduled an examination for the selected date and time!");
				} 
				// IV 
				if (freeTermStart <= examinationEnd && examinationEnd <= freeTermEnd) {
					return new ResponseFreeTermDTO(false, "Try again with another term, the dermatologist has already scheduled an examination for the selected date and time!");
				}
				
				
			}
		}
		
		
		List<WorkScheduleDermatologist> allWorkScheduleDermatologists = _workScheduleDermatologistRepository.findAll();
		
		for (WorkScheduleDermatologist workScheduleDermatologist : allWorkScheduleDermatologists) {
			if (workScheduleDermatologist.getDermatologist().getUserId() == createFreeTermDTO.getDermatologist().getUserId()
					&& workScheduleDermatologist.getPharmacy().getPharmacyId() == createFreeTermDTO.getPharmacy().getPharmacyId()) {
				
				TimeInterval shift =  workScheduleDermatologist.getShift();
				Date start = shift.getStartDate();
				Date end = shift.getEndDate();
		
				String shiftStartStr = start.toString();
				String[] shiftStartPom = shiftStartStr.split(" ", 2);
				String shiftStart = (shiftStartPom[1].substring(0, shiftStartPom.length)); // 07 15
				
				String shiftEndStr = end.toString();
				String[] shiftEndPom = shiftEndStr.split(" ", 2);
				String shiftEnd = (shiftEndPom[1].substring(0, shiftEndPom.length)); // 15 20
				
				String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(createFreeTermDTO.getDateAndTimeExamination());
				
			    Date date = new Date();
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Calendar startfreeTerm = Calendar.getInstance();
				startfreeTerm.setTime(createFreeTermDTO.getDateAndTimeExamination());		
				Long freeTermStartValue = startfreeTerm.getTimeInMillis();
				
				
				Calendar endfreeTerm = Calendar.getInstance();
				endfreeTerm.setTime(createFreeTermDTO.getDateAndTimeExamination());	
				endfreeTerm.add(Calendar.MINUTE, Integer.parseInt(createFreeTermDTO.getDuration()));
				Long freeTermEndValue = startfreeTerm.getTimeInMillis();
				
				
				Calendar startShift = Calendar.getInstance();
				startShift.setTime(date);
				startShift.add(Calendar.HOUR, Integer.parseInt(shiftStart));
				Long startShiftValue = startShift.getTimeInMillis();
				
				
				Calendar endShift = Calendar.getInstance();
				endShift.setTime(date);
				endShift.add(Calendar.HOUR, Integer.parseInt(shiftEnd));
				Long endShiftValue = endShift.getTimeInMillis();
				
				if (startShiftValue <= freeTermStartValue && freeTermEndValue <= endShiftValue) {
					System.out.println("OK");
				} else {
					return new ResponseFreeTermDTO(false, "Try again with another term, see in which shift the dermatologist works, since the entered start date and duration are not within that shift.");
				}
				
			}
		}
		
		
		
		Examination e = new Examination();
		e.setAppointmentStatus(AppointmentStatus.NONE);
		e.setCancelled(false);
		e.setDuration(Integer.parseInt(createFreeTermDTO.getDuration()));
		e.setDateAndTime(createFreeTermDTO.getDateAndTimeExamination());
		e.setPharmacy(createFreeTermDTO.getPharmacy());
		e.setPrice(Double.parseDouble(createFreeTermDTO.getPrice()));
		e.setDermatologist(createFreeTermDTO.getDermatologist());
		e.setPoints(5); // default
		// workschedule 
		
		for (WorkScheduleDermatologist workScheduleDermatologist : allWorkScheduleDermatologists) {
			if (workScheduleDermatologist.getDermatologist().getUserId() == createFreeTermDTO.getDermatologist().getUserId()
					&& workScheduleDermatologist.getPharmacy().getPharmacyId() == createFreeTermDTO.getPharmacy().getPharmacyId()) {
				workScheduleDermatologist.getScheduledExaminations().add(_examinationRepository.save(e));
				_workScheduleDermatologistRepository.save(workScheduleDermatologist);	
				return new ResponseFreeTermDTO(true, "Successfully created free term with dermatologist!");
			
			}
		}
		return new ResponseFreeTermDTO(false, "Server is busy - STOPED!");
		
	}
	
	@Override
	public List<PharmacyFreeTermDTO> findAllFreeTermExamination() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Examination> allExamination = _examinationRepository.findAll();
		List<PharmacyFreeTermDTO> list = new ArrayList<PharmacyFreeTermDTO>();
	
		for(Examination e : allExamination) {
			if (e.getPharmacy().getPharmacyId() == pAdmin.getPharmacy().getPharmacyId() && e.getPatient() == null) {
				StringBuilder dermatologistName = new StringBuilder();
				dermatologistName.append(e.getDermatologist().getFirstName());
				dermatologistName.append(" ");
				dermatologistName.append(e.getDermatologist().getLastName());
				PharmacyFreeTermDTO freeTermDTO = new PharmacyFreeTermDTO(e.getAppointmentId(), e.getDateAndTime().toString(), e.getDuration(), e.getPrice(), e.getDermatologist().getUserId(), dermatologistName.toString(), e.getDermatologist().getEmail(), e.getDermatologist().getPhoneNumber());
				list.add(freeTermDTO);
			
			}
		}
		
		return list;
	}
}
