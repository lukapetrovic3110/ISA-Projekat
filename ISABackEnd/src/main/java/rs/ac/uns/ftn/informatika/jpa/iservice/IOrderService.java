package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineData;
import rs.ac.uns.ftn.informatika.jpa.dto.OrderDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Order;

@Service
public interface IOrderService {

	Order findById(Long id);
	
	List<Order> findAll();

	Order save(Order order);
	
	Order deleteOrder(Long orderId);
	
	List<OrderDTO> findAllOrdersForPharmacy();
	
	List<OrderDTO> findAllOrdersWaitingOfferForPharmacy();
	
	List<OrderDTO> findAllFinishedOrdersForPharmacy();
	
	List<Long> findMedicineItemIdsByOrderId(Long orderId);
	
	Order createOrder(List<MedicineData> medicineItemData, List<MedicineData> newMedicineItemData, Long pharmacyAdminId,
			Long pharmacyId, Date offerDeadline);
	
	ArrayList<OrderDTO> findAllWaitingOfferOrders();
	List<OrderDTO> findAllPossibleEditingOrdersByPharmacyAdmin();
	
	Order editOrder(List<MedicineData> medicineItemInOrderData, List<MedicineData> newMedicineItemData, Long orderId,
			Date offerDeadline);
}
