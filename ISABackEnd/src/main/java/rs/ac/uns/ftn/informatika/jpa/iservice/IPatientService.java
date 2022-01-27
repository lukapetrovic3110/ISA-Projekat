package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.model.Patient;

public interface IPatientService {
	
	List<Patient> findAllSubscribedPatientsByPharmacyId(Long pharmacyId);
}
