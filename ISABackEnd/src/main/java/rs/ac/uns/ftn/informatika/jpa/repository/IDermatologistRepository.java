package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
@Repository
public interface IDermatologistRepository extends JpaRepository<Dermatologist, Long>{
	
	List<Dermatologist> findAllByFirstNameAndLastName(String firstName, String lastName);
	
	List<Dermatologist> findAllByFirstName(String firstName);
	
	List<Dermatologist> findAllByLastName(String lastName);
	
	@Query(value = "SELECT pharmacy_pharmacy_id FROM public.pharmacy_dermatologist\r\n"
			+ "WHERE dermatologist_user_id = :dermatologistId", nativeQuery = true)
	List<Long> findAllPharmacyIdsByDermatologistId(Long dermatologistId);
}
