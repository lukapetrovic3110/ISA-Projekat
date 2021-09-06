package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateWorkSchedulePharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IWorkSchedulePharmacistService;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;
import rs.ac.uns.ftn.informatika.jpa.model.WorkSchedulePharmacist;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacistRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IWorkSchedulePharmacistRepository;

@Service
public class WorkSchedulePharmacistService implements IWorkSchedulePharmacistService{

	@Autowired
	private IWorkSchedulePharmacistRepository _workScheduleRepository;
	
	@Autowired
	private IPharmacistRepository _pharmacistRepository;
	
	@Override
	public ArrayList<Pharmacy> getAvailablePharmacies(Date date, ArrayList<PharmacistVacation> vacations) {
		
		ArrayList<WorkSchedulePharmacist> all = (ArrayList<WorkSchedulePharmacist>) _workScheduleRepository.findAll();
		ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		System.out.println(date.toString());
		Calendar examS = Calendar.getInstance();
		examS.setTime(date);
		System.out.println(examS.toString());
		
		Calendar endExam = Calendar.getInstance(); // creates calendar
		endExam.setTime(examS.getTime());               // sets calendar time/date vraca vrijeme mjesec dana ranije
		endExam.add(Calendar.MINUTE, 30);
		System.out.println("end" + endExam.toString());
		
		Long examStart = examS.getTimeInMillis();
		Long examEnd = endExam.getTimeInMillis(); 
		System.out.println(examStart + " " + examEnd);
		
		int startTime = date.getHours()*60 + date.getMinutes();
		int endTime = startTime + 30;
			
		for (WorkSchedulePharmacist workSchedule : all) {
			boolean notAvailable = checkVacation(examStart, examEnd, vacations, workSchedule);
			if(notAvailable == false) {
				Calendar validS = Calendar.getInstance();
				validS.setTime(workSchedule.getValidFor().getStartDate());
				System.out.println(validS.toString());
				
				Calendar validE = Calendar.getInstance(); // creates calendar
				validE.setTime(workSchedule.getValidFor().getEndDate());               // sets calendar time/date
				System.out.println(validE.toString());
				
				int shiftStart = workSchedule.getShift().getStartDate().getHours()*60 + workSchedule.getShift().getStartDate().getMinutes();
				int shiftEnd = workSchedule.getShift().getEndDate().getHours()*60 + workSchedule.getShift().getEndDate().getMinutes();
			
				Long validStart = validS.getTimeInMillis();
				Long validEnd = validE.getTimeInMillis(); 		
				
				Set<Consultation> consultations = workSchedule.getScheduledConsultations();
				
				if(validStart <= examStart && validEnd >= examEnd) {
					System.out.println("Dobar period");
					if(shiftStart <= startTime && shiftEnd >= endTime) {
						System.out.println("Dobra smjena");
						for (Consultation c : consultations) {
							Calendar startCons = Calendar.getInstance(); 
							startCons.setTime(c.getDateAndTime());               
							
							Calendar endCons = Calendar.getInstance();
							endCons.setTime(c.getDateAndTime());              
							endCons.add(Calendar.MINUTE, c.getDuration());
							
							if(c.getCancelled() == true) {
									System.out.println("cancelled");
								}else if(examS.before(startCons) && endExam.after(startCons)) {
									System.out.println("notAvailable");
									notAvailable = true;
									break;
								}else if(examS.before(endCons) && endExam.after(endCons)){
									System.out.println("notAVailable");
									notAvailable = true;
									break;
								}else if(examS.equals(startCons)) {
									System.out.println("notAVailable");
									notAvailable = true;
									break;
								}
								
							}
								if(notAvailable) {
									notAvailable = false;
								}else if (!pharmacies.contains(workSchedule.getPharmacy())){
									System.out.println("dodaj");
									pharmacies.add(workSchedule.getPharmacy());
								}
						}
					}
			}
		}
			
		return pharmacies;
}

	@Override
	public boolean checkVacation(Long startConsulatation, Long endConsulatation, ArrayList<PharmacistVacation> vacations, WorkSchedulePharmacist workSchedule) {
		
		for (PharmacistVacation v : vacations) {
			Calendar vStart = Calendar.getInstance(); // creates calendar
			vStart.setTime(v.getStartDate());

			Calendar vEnd = Calendar.getInstance(); // creates calendar
			vEnd.setTime(v.getEndDate());
			
			Long vacationStart = vStart.getTimeInMillis();
			Long vacationEnd = vEnd.getTimeInMillis(); 
			if(v.getPharmacist().getUserId() == workSchedule.getPharmacist().getUserId()) {
				System.out.println("farmaceut isti");
				if(vacationStart <= startConsulatation && vacationEnd >= endConsulatation) {
					System.out.println("pregled je u vacation");
					return true;
					
				}
			}
		}
		return false;
		
	}
	
	@Override
	public ArrayList<Pharmacist> getAvailablePharmacistsInPharmacy(Date date, Long pharmacyId, ArrayList<PharmacistVacation> vacations) {
		
		ArrayList<WorkSchedulePharmacist> all = (ArrayList<WorkSchedulePharmacist>) _workScheduleRepository.findAll();
		ArrayList<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
	
		Calendar examS = Calendar.getInstance();
		examS.setTime(date);
		System.out.println(examS.toString());
		
		Calendar endExam = Calendar.getInstance(); // creates calendar
		endExam.setTime(examS.getTime());               // sets calendar time/date vraca vrijeme mjesec dana ranije
		endExam.add(Calendar.MINUTE, 30);
		System.out.println("end" + endExam.toString());
		
		Long examStart = examS.getTimeInMillis();
		Long examEnd = endExam.getTimeInMillis(); 
		System.out.println(examStart + " " + examEnd);
		
		int startTime = date.getHours()*60 + date.getMinutes();
		int endTime = startTime + 30;
			
		for (WorkSchedulePharmacist workSchedule : all) {
			boolean notAvailable = checkVacation(examStart, examEnd, vacations, workSchedule);
			if(notAvailable == false) {
				Calendar validS = Calendar.getInstance();
				validS.setTime(workSchedule.getValidFor().getStartDate());
				System.out.println(validS.toString());
				
				Calendar validE = Calendar.getInstance(); // creates calendar
				validE.setTime(workSchedule.getValidFor().getEndDate());               // sets calendar time/date
				System.out.println(validE.toString());
				
				int shiftStart = workSchedule.getShift().getStartDate().getHours()*60 + workSchedule.getShift().getStartDate().getMinutes();
				int shiftEnd = workSchedule.getShift().getEndDate().getHours()*60 + workSchedule.getShift().getEndDate().getMinutes();
			
				Long validStart = validS.getTimeInMillis();
				Long validEnd = validE.getTimeInMillis(); 		
				
				Set<Consultation> consultations = workSchedule.getScheduledConsultations();
			
				if(validStart <= examStart && validEnd >= examEnd) {
					System.out.println("Dobar period");
					if(shiftStart <= startTime && shiftEnd >= endTime) {
						System.out.println("Dobra smjena");
						for (Consultation c : consultations) {
							Calendar startCons = Calendar.getInstance(); 
							startCons.setTime(c.getDateAndTime());               
							
							Calendar endCons = Calendar.getInstance();
							endCons.setTime(c.getDateAndTime());              
							endCons.add(Calendar.MINUTE, c.getDuration());
							
							if(c.getCancelled() == true) {
									System.out.println("cancelled");
								}else if(examS.before(startCons) && endExam.after(startCons)) {
									System.out.println("notAvailable");
									notAvailable = true;
									break;
								}else if(examS.before(endCons) && endExam.after(endCons)){
									System.out.println("notAVailable");
									notAvailable = true;
									break;
								}else if(examS.equals(startCons)) {
									System.out.println("notAVailable");
									notAvailable = true;
									break;
								}
								
							}
								if(notAvailable) {
									notAvailable = false;
								}else if (!pharmacists.contains(workSchedule.getPharmacist())){
									System.out.println("dodaj");
									pharmacists.add(workSchedule.getPharmacist());
								}
						}
					}
				}
		}
			
		return pharmacists;
	}
	
	public WorkSchedulePharmacist findWorkScheduleForPharmacistInPeriod(Consultation consultation, ArrayList<PharmacistVacation> vacations) {
		ArrayList<WorkSchedulePharmacist> all = (ArrayList<WorkSchedulePharmacist>) _workScheduleRepository.findAll();

		Calendar examS = Calendar.getInstance();
		examS.setTime(consultation.getDateAndTime());
		
		Calendar endExam = Calendar.getInstance(); // creates calendar
		endExam.setTime(examS.getTime());               // sets calendar time/date
		endExam.add(Calendar.MINUTE, 30);
		
		Long examStart = examS.getTimeInMillis();
		Long examEnd = endExam.getTimeInMillis(); 
		System.out.println(examStart + " " + examEnd);
		
		int startTime = consultation.getDateAndTime().getHours()*60 + consultation.getDateAndTime().getMinutes();
		int endTime = startTime + 30;
			
		for (WorkSchedulePharmacist workSchedule : all) {
			
			if(workSchedule.getPharmacist().getUserId() == consultation.getPharmacist().getUserId()) {
				System.out.println("Pronasao je za farmaceuta");
				boolean notAvailable = checkVacation(examStart, examEnd, vacations, workSchedule);
				if(notAvailable == false) {
				
				Calendar validS = Calendar.getInstance();
				validS.setTime(workSchedule.getValidFor().getStartDate());
				
				Calendar validE = Calendar.getInstance(); // creates calendar
				validE.setTime(workSchedule.getValidFor().getEndDate());               // sets calendar time/date
				
				int shiftStart = workSchedule.getShift().getStartDate().getHours()*60 + workSchedule.getShift().getStartDate().getMinutes();
				int shiftEnd = workSchedule.getShift().getEndDate().getHours()*60 + workSchedule.getShift().getEndDate().getMinutes();              // sets calendar time/date
			
				Long validStart = validS.getTimeInMillis();
				Long validEnd = validE.getTimeInMillis(); 		
					
				System.out.println(shiftStart + " " + shiftEnd);
				
				if(validStart <= examStart && validEnd >= examEnd) {
					System.out.println("Pronasao je period");
				if(shiftStart <= startTime) {
					System.out.println("Pronasao je smjenu ");
					if(shiftEnd > endTime ) {
						System.out.println("Nije");
						return workSchedule;
					}
					}
				}
			}
			}
		}
		return null;
	}

	@Override
	public void addNewConsultationToWorkSchedule(Consultation c,  ArrayList<PharmacistVacation> vacations) {
		WorkSchedulePharmacist workSchedule = findWorkScheduleForPharmacistInPeriod(c, vacations);
		if(workSchedule == null) {
			return;
		}
		workSchedule.getScheduledConsultations().add(c);
		_workScheduleRepository.save(workSchedule);
		
	}
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Override
	public Boolean addConsToWorkSchedule(Consultation c,  ArrayList<PharmacistVacation> vacations) {
		WorkSchedulePharmacist workSchedule = findWorkScheduleForPharmacistInPeriod(c, vacations);
		if(workSchedule == null) {
			return false;
		}
		workSchedule.getScheduledConsultations().add(c);
		_workScheduleRepository.save(workSchedule);
		return true;
	}
	
	@Override
	public WorkSchedulePharmacist createPharmacistWorkSchedule(
			CreateWorkSchedulePharmacistDTO createWorkSchedulePharmacistDTO) {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		Pharmacy pharmacy = pAdmin.getPharmacy();
		Pharmacist pharmacist = _pharmacistRepository.getOne(createWorkSchedulePharmacistDTO.getPharmacistId());
		Date startValidFor = createWorkSchedulePharmacistDTO.getStartValidFor();
		Date endValidFor = createWorkSchedulePharmacistDTO.getEndValidFor();
		TimeInterval validFor = new TimeInterval(startValidFor, endValidFor);
		String shift = createWorkSchedulePharmacistDTO.getShift();
		String[] shiftPom = shift.split("-",2);
		String[] shiftHours = shiftPom[1].split(":", 2);
		String startShiftStr = shiftHours[0];
		String endShiftStr = shiftHours[1];
		Calendar startShift = Calendar.getInstance();
		startShift.setTime(startValidFor);
		startShift.add(Calendar.HOUR, Integer.parseInt(startShiftStr));
		Calendar endShift = Calendar.getInstance();
		endShift.setTime(endValidFor);
		endShift.add(Calendar.HOUR, Integer.parseInt(endShiftStr));
		TimeInterval shiftInterval = new TimeInterval(startShift.getTime(), endShift.getTime());
		return new WorkSchedulePharmacist(validFor, shiftInterval, pharmacist, pharmacy);
	}

}
