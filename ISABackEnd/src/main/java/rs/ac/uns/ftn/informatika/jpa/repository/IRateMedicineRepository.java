package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.RateMedicine;

@Repository
public interface IRateMedicineRepository extends JpaRepository<RateMedicine, Long>{

}
