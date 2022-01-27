package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;

@Repository
public interface IEPrescriptionRepository extends JpaRepository<EPrescription, Long>{

	EPrescription findByCode(String code);
	
}
