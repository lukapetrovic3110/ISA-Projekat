package rs.ac.uns.ftn.informatika.jpa.service;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IReservationService;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.model.PenaltyType;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineItemRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReservationRepository;

@Service
public class ReservationService implements IReservationService{

	@Autowired
	private IReservationRepository _reservationRepository;
	
	@Autowired
	private IMedicineItemRepository _medicineItemRepository;
	
	@Override
	public Reservation updateReservation(@Valid Reservation r) {
		
		Reservation reservation = _reservationRepository.findById(r.getReservationId()).orElse(null);
		reservation.setRecieved(true);
		this._reservationRepository.save(reservation);
		return reservation;
	}
	
	@Override
	public Reservation findReservationByCode(String code) {
		System.out.println("**********************************");
		List<Reservation> reservations = _reservationRepository.findAll();
		
		for (Reservation r: reservations) {
			if (r.getReservationCode().equals(code)) {
				System.out.println(code);
				return r;
			}
		}	
		return null;
	}

	@Override
	public Reservation createReservation(ReservationDTO dto, Patient p) {
		String d = dto.getDate();
	    Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			System.out.println(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    String generatedString = givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect();
	    MedicineItem item = new MedicineItem(1, dto.getDto().getPriceTag().getMedicine());
		Reservation reservation = new Reservation(date, generatedString, false, p, item, dto.getDto().getPharmacy(), false);
		_medicineItemRepository.save(item);
	
		
		return _reservationRepository.save(reservation);
	
		
	}
	
	public String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}

	@Override
	public ArrayList<ReservationViewDTO> getByPatient(Long patientId) {
		ArrayList<Reservation> allReservations = (ArrayList<Reservation>) _reservationRepository.findAll();
		ArrayList<ReservationViewDTO> result = new ArrayList<ReservationViewDTO>();
		
		for (Reservation r : allReservations) { 
			if(r.getDeadline().after(new Date()) && r.getPatient().getUserId() == patientId && r.getRecieved() == false && r.getCancelled() == false) {
				result.add(new ReservationViewDTO(r.getMedicineItem(), r.getPharmacy(), r.getDeadline().toString(), r.getReservationId()));
			}
		}
		return result;	
	}
	
	@Override
	public boolean cancelReservation(ReservationViewDTO reservation) {
		Optional<Reservation> oldReservation = _reservationRepository.findById(reservation.getReservationId());
		Reservation r = oldReservation.get();
		if(isSoonerThan24hours(r)) {
			return false;
		}
		r.setCancelled(true);
		_reservationRepository.save(r);
		return true;
	}
	
	@Override
	public boolean isSoonerThan24hours(Reservation r) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 24);      
		
		if(r.getDeadline().before(cal.getTime())) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Penalty> checkForPenatlies(Long patientId) {
		ArrayList<Reservation> allReservations = (ArrayList<Reservation>) _reservationRepository.findAll();
		ArrayList<Penalty> penalties = new ArrayList<Penalty>();
		for (Reservation reservation : allReservations) {
			if(reservation.getPatient().getUserId() == patientId && reservation.getCancelled() == false
					&& reservation.getPenalty() == false && reservation.getRecieved() == false
					&& reservation.getDeadline().before(new Date())) {
				reservation.setPenalty(true);
				_reservationRepository.save(reservation);
				penalties.add(new Penalty(reservation.getDeadline().toString(), PenaltyType.RESERVATION_MISSED, reservation.getPharmacy()));
			}
		}
		
		return penalties;
	}

	@Override
	public void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result) {
		ArrayList<Reservation> allReservations = (ArrayList<Reservation>) _reservationRepository.findAll();
		for (Reservation reservation : allReservations) {
			if(reservation.getPatient().getUserId() == patientId && reservation.getRecieved() == true) {
				if(!result.contains(reservation.getPharmacy())) {
					result.add(reservation.getPharmacy());
				}
			}
		}
	}
	
	@Override
	public void getMedicineForRating(Long patientId, ArrayList<Medicine> result) {
		ArrayList<Reservation> allReservations = (ArrayList<Reservation>) _reservationRepository.findAll();
		for (Reservation reservation : allReservations) {
			if(reservation.getPatient().getUserId() == patientId && reservation.getRecieved() == true) {
				if(!result.contains(reservation.getMedicineItem().getMedicine())) {
					result.add(reservation.getMedicineItem().getMedicine());
				}
			}
		}
		
	}
}
