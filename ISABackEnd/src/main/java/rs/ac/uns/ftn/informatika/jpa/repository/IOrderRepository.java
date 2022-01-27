package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
	
	@Query(value = "select medicine_item_id from public.order_medicine_item orm left join medicine_item mi \r\n"
			+ "	on orm.medicine_item_medicine_item_id = mi.medicine_item_id where orm.order_order_id = :orderId", nativeQuery = true)
	List<Long> findMedicineItemIdsByOrderId(Long orderId);
}
