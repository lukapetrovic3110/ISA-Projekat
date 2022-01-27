package rs.ac.uns.ftn.informatika.jpa.iservice;


import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;

public interface IReservationService {
	
	public Reservation findReservationByCode(String code);

	public Reservation updateReservation(Reservation r);

	Reservation createReservation(ReservationDTO dto, Patient user);

	ArrayList<ReservationViewDTO> getByPatient(Long patientId);

	boolean cancelReservation(ReservationViewDTO reservation);

	boolean isSoonerThan24hours(Reservation r);

	ArrayList<Penalty> checkForPenatlies(Long patientId);

	void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result);

	void getMedicineForRating(Long patientId, ArrayList<Medicine> result);
}