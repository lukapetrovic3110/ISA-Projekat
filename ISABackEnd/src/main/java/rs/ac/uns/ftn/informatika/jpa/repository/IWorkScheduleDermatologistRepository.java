package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.WorkScheduleDermatologist;

@Repository
public interface IWorkScheduleDermatologistRepository extends JpaRepository<WorkScheduleDermatologist, Long>  {

}
