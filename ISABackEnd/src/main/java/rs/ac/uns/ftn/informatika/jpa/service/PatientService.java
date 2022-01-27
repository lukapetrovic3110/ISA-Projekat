package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IPatientService;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.repository.IPatientRepository;

@Service
public class PatientService implements IPatientService{

	private IPatientRepository _patientRepository;
	
	public PatientService(IPatientRepository patientRepository) {
		this._patientRepository = patientRepository;
	}

	@Override
	public List<Patient> findAllSubscribedPatientsByPharmacyId(Long pharmacyId) {
		return _patientRepository.findAllSubscribedPatientsByPharmacyId(pharmacyId);
	}

}
