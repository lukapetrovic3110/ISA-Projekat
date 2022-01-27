package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.LoyaltyScaleDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.ILoyaltyScaleService;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCard;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCategory;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyScale;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.IConsultationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IExaminationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ILoyaltyCardRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ILoyaltyScaleRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPatientRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class LoyaltyScaleService implements ILoyaltyScaleService{

	@Autowired
	public IPatientRepository _patientRepository;
	
	@Autowired
	public IExaminationRepository _examinationRepository;
	
	@Autowired
	public IConsultationRepository _consultationRepository;
	
	@Autowired
	public IReservationRepository _reservationRepository;
	
	@Autowired
	public ILoyaltyScaleRepository _loyaltyScaleRepository;
	
	@Autowired
	public ILoyaltyCardRepository _loyaltyCardRepository;
	
	@Override
	public LoyaltyScale saveLoyaltyScale() {
		LoyaltyScale loyaltyScale = new LoyaltyScale();
		int points = 0;
		
			if (points < 20) {
				loyaltyScale.setFrom(0);
				loyaltyScale.setTo(20);
				loyaltyScale.setLoyaltyCategory(LoyaltyCategory.REGULAR);
			}else if(points > 20 && points < 50) {
				loyaltyScale.setFrom(20);
				loyaltyScale.setTo(50);
				loyaltyScale.setLoyaltyCategory(LoyaltyCategory.SILVER);
			}else{
				loyaltyScale.setFrom(50);
				loyaltyScale.setTo(100);
				loyaltyScale.setLoyaltyCategory(LoyaltyCategory.GOLD);
			}		
		return _loyaltyScaleRepository.save(loyaltyScale);
	}

	@Override
	public void calculateLoyaltyCategoryForPatients() {

		ArrayList<Patient> patients = (ArrayList<Patient>) _patientRepository.findAll();
		ArrayList<Examination> examinations = (ArrayList<Examination>) _examinationRepository.findAll();
		ArrayList<Consultation> consultations = (ArrayList<Consultation>) _consultationRepository.findAll();
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) _reservationRepository.findAll();
		
		for(Patient p: patients) {
			int sum = 0;
			for(Examination e: examinations) {
				if (e.getPatient() != null) {
					if(e.getPatient().getUserId().equals(p.getUserId()) && e.getAppointmentStatus().equals(AppointmentStatus.FINISHED)) {
						sum += e.getPoints();
					}
				}
			}
			
			for(Consultation c: consultations) {
				if(c.getPatient().getUserId().equals(p.getUserId()) && c.getAppointmentStatus().equals(AppointmentStatus.FINISHED)) {
					sum += c.getPoints();
				}
			}
			
			for(Reservation r: reservations) {
				sum += r.getMedicineItem().getMedicine().getLoyaltyPoints(); 
			}
			
			LoyaltyCard loyaltyCard = new LoyaltyCard();
			loyaltyCard = getLoyaltyCardForPatient(p);
			loyaltyCard.setPoints(sum);
			loyaltyCard.setPatient(p);
			
			loyaltyCard.setLoyaltyCategory(checkCategory(sum));
			_loyaltyCardRepository.save(loyaltyCard);
		}
		
	}
	
	public LoyaltyCategory checkCategory(int points) {
		
		ArrayList<LoyaltyScale> loyaltyScales = (ArrayList<LoyaltyScale>) _loyaltyScaleRepository.findAll();
		
		for(LoyaltyScale loyaltyScale : loyaltyScales) {
			if(loyaltyScale.getFrom() <= points && loyaltyScale.getTo() >= points) {
				return loyaltyScale.getLoyaltyCategory();
			}
		}
		return LoyaltyCategory.GOLD;
	}
	
	public LoyaltyCard getLoyaltyCardForPatient(Patient patient) {
		ArrayList<LoyaltyCard> loyaltyCards = (ArrayList<LoyaltyCard>) _loyaltyCardRepository.findAll();
		
		for(LoyaltyCard lc: loyaltyCards) {
			if(lc.getPatient().getUserId().equals(patient.getUserId())) {
				return lc;
			}
		}
		
		return null;
		
	}

	@Override
	public LoyaltyScale createLoyaltyScale(LoyaltyScaleDTO loyaltyScaleDTO) {
		
		LoyaltyScale loyaltyScale = new LoyaltyScale();
		loyaltyScale.setFrom(loyaltyScaleDTO.getFrom());
		loyaltyScale.setTo(loyaltyScaleDTO.getTo());
		loyaltyScale.setLoyaltyCategory(loyaltyScaleDTO.getLoyaltyCategory());
		return _loyaltyScaleRepository.save(loyaltyScale);
	}
}
