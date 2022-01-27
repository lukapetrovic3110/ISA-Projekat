package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Status;

@Repository
public interface IDermatologistVacationRepository extends JpaRepository<DermatologistVacation, Long>{
	
	List<DermatologistVacation> findAllByStatus(Status status);

}
