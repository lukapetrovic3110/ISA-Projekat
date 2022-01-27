package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintForPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintEmployeeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IComplaintService;
import rs.ac.uns.ftn.informatika.jpa.iservice.IConsultationService;
import rs.ac.uns.ftn.informatika.jpa.model.AnswerEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.AnswerPharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintPharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacist;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.SystemAdministrator;
import rs.ac.uns.ftn.informatika.jpa.repository.IAnswerEmployeeRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IAnswerPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IComplaintEmployeeRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IComplaintPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyEmployeeRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class ComplaintService implements IComplaintService{

	@Autowired
	public IComplaintEmployeeRepository _complaintEmployeeRepository;
	
	@Autowired
	public IPharmacyEmployeeRepository _pharmacyEmployeeRepository;
	
	@Autowired
	public IUserRepository _userRepository;
	
	@Autowired
	public IPharmacyRepository _pharmacyRepository;
	
	@Autowired
	public IComplaintPharmacyRepository _complaintPharmacyRepository;
	
	@Autowired
	public IConsultationService _consultationService;

	@Autowired
	public IAnswerEmployeeRepository _answerEmployeeRepository;
	
	@Autowired
	public IAnswerPharmacyRepository _answerPharmacyRepository;
	
	@Override
	public ComplaintEmployee createComplaint(ComplaintEmployeeDTO complaintEmployeeDTO) {
		
		ComplaintEmployee complaintEmployee = new ComplaintEmployee();

		PharmacyEmployee pharmacist = _pharmacyEmployeeRepository.findById(complaintEmployeeDTO.getPharmacyEmployeeId()).orElse(null);
		Patient patient = (Patient) _userRepository.findById(complaintEmployeeDTO.getPatientId()).orElse(null);
		
		complaintEmployee.setTextEmp(complaintEmployeeDTO.getTextEmp());
		complaintEmployee.setPharmacyEmployee(pharmacist);	
		complaintEmployee.setPatient(patient);
		return _complaintEmployeeRepository.save(complaintEmployee);
	}

	@Override
	public ComplaintPharmacy createComplaintForPharmacy(ComplaintPharmacyDTO complaintPharmacyDTO) {
		
		ComplaintPharmacy complaintPharmacy = new ComplaintPharmacy();
		
		Pharmacy pharmacy = _pharmacyRepository.findById(complaintPharmacyDTO.getPharmacyId()).orElse(null);
		Patient patient = (Patient) _userRepository.findById(complaintPharmacyDTO.getPatientId()).orElse(null);

		complaintPharmacy.setTextPh(complaintPharmacyDTO.getTextEmp());
		complaintPharmacy.setPatient(patient);
		complaintPharmacy.setPharmacy(pharmacy);
		
		return _complaintPharmacyRepository.save(complaintPharmacy);
	}

	@Override
	public ArrayList<ComplaintEmployee> getComplaintsEmployee() {
		return (ArrayList<ComplaintEmployee>) _complaintEmployeeRepository.findAll();
	}

	@Override
	public ArrayList<ComplaintPharmacy> getComplaintsPharmacy() {
		return (ArrayList<ComplaintPharmacy>) _complaintPharmacyRepository.findAll();
	}

	@Override
	public AnswerEmployee answerOnComplaintForEmployee(AnswerOnComplaintDTO answerOnComplaintDTO) {
		
		AnswerEmployee answerEmployee = new AnswerEmployee();
		
		ComplaintEmployee complaintEmployee = _complaintEmployeeRepository.findById(answerOnComplaintDTO.getComplaintEmployeeId()).orElse(null);
		SystemAdministrator systemAdministrator = (SystemAdministrator) _userRepository.findById(answerOnComplaintDTO.getSystemAdminId()).orElse(null);
		
		answerEmployee.setTextEmp(answerOnComplaintDTO.getTextAnswer());
		answerEmployee.setComplaintEmployee(complaintEmployee);
		answerEmployee.setSystemAdministrator(systemAdministrator);
		
		return _answerEmployeeRepository.save(answerEmployee);
	}

	@Override
	public AnswerPharmacy answerOnComplaintForPharmacy(AnswerOnComplaintForPharmacyDTO answerOnComplaintDTO) {
		
		AnswerPharmacy answerPharmacy = new AnswerPharmacy();
		
		ComplaintPharmacy complaintPharmacy = _complaintPharmacyRepository.findById(answerOnComplaintDTO.getComplaintPharmacyId()).orElse(null);
		SystemAdministrator systemAdministrator = (SystemAdministrator) _userRepository.findById(answerOnComplaintDTO.getSystemAdminId()).orElse(null);
		
		answerPharmacy.setTextAnswer(answerOnComplaintDTO.getTextAnswer());
		System.out.println(answerOnComplaintDTO.getTextAnswer());
		answerPharmacy.setComplaintPharmacy(complaintPharmacy);
		answerPharmacy.setSystemAdministrator(systemAdministrator);
		
		return _answerPharmacyRepository.save(answerPharmacy);
	}


}
