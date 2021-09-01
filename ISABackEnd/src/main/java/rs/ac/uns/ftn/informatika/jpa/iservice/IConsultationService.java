package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ConsultationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;

@Service
public interface IConsultationService {
	 Consultation save(ConsultationDTO dto, Patient p);
	 ArrayList<Consultation> findAllConsultation();
	 Consultation saveConsultation(Consultation consultation);
	 ArrayList<Consultation> getConsultationsByPatient(Long patientId);
	 ArrayList<ConsultationViewDTO> getByPatient(Long patientId);
	boolean cancelConsultation(ConsultationViewDTO consultation);
	boolean isSoonerThan24hours(Consultation c);
	List<Consultation> getByPharmacist(Long id);
	Consultation startConsultation(Date date);
	Consultation endConsultation(Long id);
	Consultation findById(Long id);
}
