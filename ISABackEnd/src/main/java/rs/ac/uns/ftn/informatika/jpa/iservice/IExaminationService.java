package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ExaminationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseFreeTermDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.TimeInterval;

public interface IExaminationService {
	public ArrayList<ExaminationDTO> getByPharmacy(Long pharmacyId);
	public Examination scheduleExamination(ExaminationDTO examination);
	public ArrayList<ExaminationDTO> getByPatient(Long patientId);
	public boolean cancelExamination(ExaminationDTO examination);
	public boolean isSoonerThan24hours(Examination examination);
	ArrayList<Examination> findAllExamination();
	ArrayList<ExaminationDTO> getByDermatologist(Long dermatologistId);
	Examination save(ExaminationDTO dto);
	ArrayList<Examination> getExaminationsByPatient(Long patientId);
	Examination saveExamination(Examination e);
	Examination startExamination(Date date);
	Examination endExamination(Long id);
	Examination findById(Long id);
	void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result);
	ArrayList<Dermatologist> getAllDermatologistByPatient(Long patientId);
	ArrayList<ExaminationDTO> getPreviousExaminations(Long patientId);
	List<Examination> getByDermatologist(Long id, TimeInterval timeInterval);
	List<ExaminationDTO> findAllScheduledExaminationInPharmacyByDermatologist(Long dermatologistId);
	ResponseFreeTermDTO createFreeTermExaminationForDermatologist(CreateFreeTermDTO createFreeTermDTO);
	List<PharmacyFreeTermDTO> findAllFreeTermExamination();
}
