package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.Promotion;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, Long>{
	
	@Query(value="INSERT INTO promotion_notification (pharmacy_id, patient_id) values (:pharmacyId, :patientId)", nativeQuery = true)
	void newPharmacySubscription(Long pharmacyId, Long patientId);
}
