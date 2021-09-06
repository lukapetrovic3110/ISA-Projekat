package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateWorkSchedulePharmacistDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.WorkSchedulePharmacist;

public interface IWorkSchedulePharmacistService {
	ArrayList<Pharmacy> getAvailablePharmacies(Date date, ArrayList<PharmacistVacation> vacations);
	boolean checkVacation(Long startConsulatation, Long endConsulatation, ArrayList<PharmacistVacation> vacations,
			WorkSchedulePharmacist workSchedule);
	ArrayList<Pharmacist> getAvailablePharmacistsInPharmacy(Date date, Long pharmacyId,
			ArrayList<PharmacistVacation> vacations);

	void addNewConsultationToWorkSchedule(Consultation c, ArrayList<PharmacistVacation> vacations);
	Boolean addConsToWorkSchedule(Consultation c, ArrayList<PharmacistVacation> vacations);

	WorkSchedulePharmacist createPharmacistWorkSchedule(
			CreateWorkSchedulePharmacistDTO createWorkSchedulePharmacistDTO);

}
