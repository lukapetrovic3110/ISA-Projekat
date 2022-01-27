package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IConsultationService;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.repository.IConsultationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class ConsultationService implements IConsultationService{

	@Autowired 
	private IConsultationRepository _consultationRepository;

	@Autowired 
	private IUserRepository _userRepository;
	
	@Override
	public ArrayList<Consultation> findAllConsultation() {
		return (ArrayList<Consultation>) _consultationRepository.findAll();
	}

	@Override
	public ArrayList<Consultation> getConsultationsByPatient(Long patientId) {
		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		ArrayList<Consultation> result = new ArrayList<Consultation>();
		
		for (Consultation consultation : allConsultations) { 
			if(consultation.getDateAndTime().after(new Date()) && consultation.getPatient() != null) {
				if(consultation.getPatient().getUserId() == patientId && consultation.getCancelled() == false) {
			result.add(consultation);
				}
			}
		}
		return result;
	}

	@Override
	public Consultation save(ConsultationDTO dto, Patient p) {
		String d = dto.getDate() + " " +  dto.getTime() + ":00";
	    Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			System.out.println(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Consultation consultation = new Consultation();
		consultation.setAppointmentStatus(AppointmentStatus.NONE);
		consultation.setCancelled(false);
		consultation.setDateAndTime(date);
		consultation.setPharmacist(dto.getPharmacist());
		consultation.setPatient(p);
		consultation.setPrice(dto.getPharmacist().getPharmacy().getConsultationPrice());
		consultation.setPharmacy(dto.getPharmacist().getPharmacy());
		consultation.setDuration(30);
		return this._consultationRepository.save(consultation);
		
	}
	
	@Override
	public Consultation saveConsultation(Consultation c) {
		
		Consultation consultation = new Consultation();
		consultation.setAppointmentStatus(AppointmentStatus.NONE);
		consultation.setCancelled(false);
		consultation.setDateAndTime(c.getDateAndTime());
		consultation.setPharmacist(c.getPharmacist());
		consultation.setPatient(c.getPatient());
		consultation.setPrice(c.getPharmacist().getPharmacy().getConsultationPrice());
		consultation.setPharmacy(c.getPharmacist().getPharmacy());
		consultation.setDuration(30);
		return this._consultationRepository.save(consultation);

	}
	
	//konsultacije za pacijenta koje se jos nisu odrzale
	@Override
	public ArrayList<ConsultationViewDTO> getByPatient(Long patientId) {
		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		ArrayList<ConsultationViewDTO> result = new ArrayList<ConsultationViewDTO>();
		
		for (Consultation consultation : allConsultations) { 
			if(consultation.getDateAndTime().after(new Date()) && consultation.getPatient() != null) {
				if(consultation.getPatient().getUserId() == patientId && consultation.getCancelled() == false) {
			result.add(new ConsultationViewDTO(consultation.getAppointmentId(),consultation.getDateAndTime().toString(),
					consultation.getDuration(),consultation.getPrice(),consultation.getPoints(),consultation.getPharmacist(),
					consultation.getPatient(), consultation.getPharmacy()));
				}
			}
		}
		return result;	}

	@Override
	public boolean cancelConsultation(ConsultationViewDTO consultation) {
		Optional<Consultation> oldConsultation = _consultationRepository.findById(consultation.getAppointmentId());
		Consultation c = oldConsultation.get();
		if(isSoonerThan24hours(c)) {
			return false;
		}
		c.setCancelled(true);
		_consultationRepository.save(c);
		return true;
	}
	
	@Override
	public boolean isSoonerThan24hours(Consultation c) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 24);      
		
		if(c.getDateAndTime().before(cal.getTime())) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Consultation> getByPharmacist(Long id, TimeInterval timeInterval){
		
		List<Consultation> all = _consultationRepository.findAll();
		List<Consultation> result = new ArrayList<>();
		
		
		for(Consultation c: all) {
			if(c.getPharmacist() != null) {
				if(id.equals(c.getPharmacist().getUserId())) {
					if(c.getDateAndTime().after(timeInterval.getStartDate()) && c.getDateAndTime().before(timeInterval.getEndDate())) {
						result.add(c);
					}
				}
			}
		}
		
		
		return result;
	}
	@Override
	public Consultation startConsultation(Date date) {
		
		Calendar eS = Calendar.getInstance();
		eS.setTime(date);
		eS.add(Calendar.MINUTE, -15);
		
		Calendar eE = Calendar.getInstance(); // creates calendar
		eE.setTime(eS.getTime());               // sets calendar time/date
		eE.add(Calendar.MINUTE, 30);
		
		Long eStart = eS.getTimeInMillis();
		Long eEnd = eE.getTimeInMillis(); 
		
		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		for(Consultation e: allConsultations) {
			Calendar examS = Calendar.getInstance();
			examS.setTime(e.getDateAndTime());
			
			Long examStart = examS.getTimeInMillis();
			
			if(examStart >= eStart && examStart < eEnd && !e.getAppointmentStatus().equals(AppointmentStatus.FINISHED)) {
				e.setAppointmentStatus(AppointmentStatus.STARTED);
				this._consultationRepository.save(e);
				return e;
			}
		}
		return new Consultation();
	}
	
	@Override
	public Consultation endConsultation(Long id) {
		
		Consultation e = findById(id);
		
			if(e.getAppointmentStatus().equals(AppointmentStatus.STARTED)){
				e.setAppointmentStatus(AppointmentStatus.FINISHED);
				this._consultationRepository.save(e);
				return e;
		
		}
		return null;
	}

	@Override
	public Consultation findById(Long id) {
		return _consultationRepository.findById(id).orElse(null);
	}
	
	@Override
	public void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result) {
		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		for (Consultation consultation : allConsultations) {
			if(consultation.getPatient().getUserId() == patientId && consultation.getAppointmentStatus() == AppointmentStatus.FINISHED) {
				if(!result.contains(consultation.getPharmacy())) {
					result.add(consultation.getPharmacy());
				}
			}
		}
	}
	
	@Override	
	public ArrayList<Pharmacist> getAllPharmacistForPatient(Long patientId) {

		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		ArrayList<Pharmacist> result = new ArrayList<Pharmacist>();

		for(Consultation consultation: allConsultations) {
			if(consultation.getPharmacist() != null) {
				if(consultation.getPatient().getUserId() == patientId && consultation.getAppointmentStatus() == AppointmentStatus.FINISHED) {
					if(!result.contains(consultation.getPharmacist())) {
						result.add(consultation.getPharmacist());
					}	
				}
			}
		}
		return result;
	}

	@Override
	public ArrayList<ConsultationViewDTO> getPreviousConsultations(Long patientId) {
		ArrayList<Consultation> allConsultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		ArrayList<ConsultationViewDTO> result = new ArrayList<ConsultationViewDTO>();
		
		for (Consultation c : allConsultations) {
			if(c.getPatient() != null) {
				if(c.getPatient().getUserId() == patientId && c.getAppointmentStatus() == AppointmentStatus.FINISHED) {
					result.add(new ConsultationViewDTO(c.getAppointmentId(),c.getDateAndTime().toString(),
							c.getDuration(),c.getPrice(),c.getPoints(),c.getPharmacist(),
							null, c.getPharmacy()));
				}
			}
		}
		
		return result;
	}
}
