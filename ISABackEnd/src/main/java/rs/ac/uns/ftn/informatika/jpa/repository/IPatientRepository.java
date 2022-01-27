package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long>{
	
	// JPQL
	@Query("from Patient p join p.pharmacies pPharmacies where pPharmacies.id=:pharmacyId")
	List<Patient> findAllSubscribedPatientsByPharmacyId(Long pharmacyId);
}
