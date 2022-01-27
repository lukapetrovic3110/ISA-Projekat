package rs.ac.uns.ftn.informatika.jpa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineData;
import rs.ac.uns.ftn.informatika.jpa.dto.OrderDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IOrderService;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Offer;
import rs.ac.uns.ftn.informatika.jpa.model.Order;
import rs.ac.uns.ftn.informatika.jpa.model.OrderStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IOfferRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IOrderRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyAdminRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;

@Service
public class OrderService implements IOrderService{

	private IOrderRepository _orderRepository;
	
	private IMedicineRepository _medicineRepository;
	
	private IPharmacyRepository _pharmacyRepository;
	
	private IPharmacyAdminRepository _pharmacyAdminRepository;
	
	private IOfferRepository _offerRepository;
	
	@Autowired
	public OrderService(IOrderRepository orderRepository, IMedicineRepository medicineRepository, IPharmacyRepository pharmacyRepository, IPharmacyAdminRepository pharmacyAdminRepository, IOfferRepository offerRepository) {
		this._orderRepository = orderRepository;
		this._medicineRepository = medicineRepository;
		this._pharmacyRepository = pharmacyRepository;
		this._pharmacyAdminRepository = pharmacyAdminRepository;
		this._offerRepository = offerRepository;
	}
	
	@Override
	public Order findById(Long id) {
		return _orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> findAll() {
		return _orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
		return _orderRepository.save(order);
	}
	
	@Override
	public List<OrderDTO> findAllOrdersForPharmacy() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<Order> allOrders = _orderRepository.findAll();
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		Date currentDate = new Date(System.currentTimeMillis());
		
		
		for (Order order : allOrders) {
			if (order.getPharmacy().getPharmacyId() == pAdmin.getPharmacy().getPharmacyId()) {
				if (order.getOfferDeadline().before(currentDate) && order.getOrderStatus() != OrderStatus.FINISHED) {
					order.setOrderStatus(OrderStatus.PROCESSED);
					_orderRepository.save(order);
				}
				
				if (order.getOrderStatus() == OrderStatus.PROCESSED) {
					String deadline = new SimpleDateFormat("dd.MM.yyyy.").format(order.getOfferDeadline());
					OrderDTO orderDTO = new OrderDTO(order.getOrderId(), deadline, order.getPharmacyAdministrator().getUserId(), order.getPharmacyAdministrator().getFirstName() + " " + order.getPharmacyAdministrator().getLastName(), order.getOrderStatus());
					list.add(orderDTO);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<OrderDTO> findAllOrdersWaitingOfferForPharmacy() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<Order> allOrders = _orderRepository.findAll();
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		Date currentDate = new Date(System.currentTimeMillis());
		
		
		for (Order order : allOrders) {
			if (order.getPharmacy().getPharmacyId() == pAdmin.getPharmacy().getPharmacyId()) {
				if (order.getOfferDeadline().before(currentDate) && order.getOrderStatus() != OrderStatus.FINISHED) {
					order.setOrderStatus(OrderStatus.PROCESSED);
					_orderRepository.save(order);
				}
				
				if (order.getOrderStatus() == OrderStatus.WAITING_OFFER) {
					String deadline = new SimpleDateFormat("dd.MM.yyyy.").format(order.getOfferDeadline());
					OrderDTO orderDTO = new OrderDTO(order.getOrderId(), deadline, order.getPharmacyAdministrator().getUserId(), order.getPharmacyAdministrator().getFirstName() + " " + order.getPharmacyAdministrator().getLastName(), order.getOrderStatus());
					list.add(orderDTO);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<OrderDTO> findAllPossibleEditingOrdersByPharmacyAdmin() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<Order> allOrders = _orderRepository.findAll();
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		List<Offer> allOffers = _offerRepository.findAll();
		Date currentDate = new Date(System.currentTimeMillis());
		for (Order order : allOrders) {
			if (order.getPharmacyAdministrator().getUserId() == pAdmin.getUserId()) {
				if (order.getOfferDeadline().before(currentDate) && order.getOrderStatus() != OrderStatus.FINISHED) {
					order.setOrderStatus(OrderStatus.PROCESSED);
					_orderRepository.save(order);
				}
				
				// added and processed request in case the deadline expires - order.getOrderStatus() == OrderStatus.PROCESSED
				if (order.getOrderStatus() == OrderStatus.WAITING_OFFER || order.getOrderStatus() == OrderStatus.PROCESSED) { 
								
					if (!hasOffer(allOffers, order)) {
						String deadline = new SimpleDateFormat("dd.MM.yyyy.").format(order.getOfferDeadline());
						OrderDTO orderDTO = new OrderDTO(order.getOrderId(), deadline, order.getPharmacyAdministrator().getUserId(), order.getPharmacyAdministrator().getFirstName() + " " + order.getPharmacyAdministrator().getLastName(), order.getOrderStatus());
						list.add(orderDTO);
					}
				}
			}
		}
		
		return list;
	}

	private Boolean hasOffer(List<Offer> allOffers, Order order) {
		for (Offer offer : allOffers) {
			if (offer.getOrder().getOrderId() == order.getOrderId()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<OrderDTO> findAllFinishedOrdersForPharmacy() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<Order> allOrders = _orderRepository.findAll();
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		Date currentDate = new Date(System.currentTimeMillis());
		for (Order order : allOrders) {
			if (order.getPharmacy().getPharmacyId() == pAdmin.getPharmacy().getPharmacyId()) {	
				if (order.getOfferDeadline().before(currentDate) && order.getOrderStatus() != OrderStatus.FINISHED) {
					order.setOrderStatus(OrderStatus.PROCESSED);
					_orderRepository.save(order);
				}
				if (order.getOrderStatus() == OrderStatus.FINISHED) {
					String deadline = new SimpleDateFormat("dd.MM.yyyy.").format(order.getOfferDeadline());
					OrderDTO orderDTO = new OrderDTO(order.getOrderId(), deadline, order.getPharmacyAdministrator().getUserId(), order.getPharmacyAdministrator().getFirstName() + " " + order.getPharmacyAdministrator().getLastName(), order.getOrderStatus());
					list.add(orderDTO);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Long> findMedicineItemIdsByOrderId(Long orderId) {
		return _orderRepository.findMedicineItemIdsByOrderId(orderId);
	}

	@Override
	public Order createOrder(List<MedicineData> medicineItemData, List<MedicineData> newMedicineItemData,
			Long pharmacyAdminId, Long pharmacyId, Date offerDeadline) {
		
		Order order = new Order();
		Set<MedicineItem> orderItems = new HashSet<MedicineItem>();
		Pharmacy p = _pharmacyRepository.getOne(pharmacyId);
		Set<MedicineItem> pharmcayItems = p.getMedicineItem();
		
		for (MedicineData m : medicineItemData) {
			MedicineItem medicineItem = new MedicineItem();
			medicineItem.setMedicine(_medicineRepository.getOne(m.getMedicineId()));
			medicineItem.setQuantity(m.getQuantity());
			orderItems.add(medicineItem);
		}
		
		for (MedicineData m : newMedicineItemData) {
			MedicineItem medicineItem = new MedicineItem();
			MedicineItem medicineItemPharmacy = new MedicineItem();
			Medicine medicine = _medicineRepository.getOne(m.getMedicineId());
			medicineItem.setMedicine(medicine);
			medicineItem.setQuantity(m.getQuantity());
			orderItems.add(medicineItem);
			medicineItemPharmacy.setMedicine(medicine);
			medicineItemPharmacy.setQuantity(0);
			pharmcayItems.add(medicineItemPharmacy);
	
		}
		
		order.setOrderStatus(OrderStatus.WAITING_OFFER);
		order.setOfferDeadline(offerDeadline);
		_pharmacyRepository.save(p);
		order.setPharmacy(p);
		order.setMedicineItem(orderItems);
		order.setPharmacyAdministrator(_pharmacyAdminRepository.findByUserId(pharmacyAdminId));
		return _orderRepository.save(order);
	}

	@Override
	public ArrayList<OrderDTO> findAllWaitingOfferOrders() {
		
		ArrayList<OrderDTO> result = new ArrayList<>();
		ArrayList<Order> allOrders =  (ArrayList<Order>) _orderRepository.findAll();
		
		for(Order o: allOrders) {
			if(o.getOrderStatus().equals(OrderStatus.WAITING_OFFER)) {
				result.add(new OrderDTO(o.getOrderId(), o.getOfferDeadline().toString(), o.getPharmacy().getName(), o.getPharmacyAdministrator().getUserId(), o.getPharmacyAdministrator().getFirstName(), o.getOrderStatus()));
			}
		}
		
		return result;
	}
	
	@Override
	public Order deleteOrder(Long orderId) {
		
		Order deleteOrder = _orderRepository.getOne(orderId);
		/* Logical deletion
		 * I did not want to add a new deleted state, 
		 * because I believe that the order can be finished even if the supplier's offer is not accepted, ie if the administrator deletes it, 
		 * then it will go into that finished state, and in this state of course the medicines will not be updated in the pharmacy.
		 */
		deleteOrder.setOrderStatus(OrderStatus.FINISHED);
		return _orderRepository.save(deleteOrder);
	}
	
	@Override
	public Order editOrder(List<MedicineData> medicineItemInOrderData, List<MedicineData> newMedicineItemData, Long orderId,
			Date offerDeadline) {
		Order order = _orderRepository.getOne(orderId);
		Set<MedicineItem> updatedMedicineItems = new HashSet<MedicineItem>();
		Set<MedicineItem> orderMedicineItems = order.getMedicineItem();
		
		for (MedicineData m : medicineItemInOrderData)
			for (MedicineItem orderMedicineItem : orderMedicineItems) {
				Medicine medicine = _medicineRepository.getOne(m.getMedicineId());
				if (orderMedicineItem.getMedicine() == medicine) {
					if (m.getQuantity() > 0) {
						MedicineItem mItem = new MedicineItem(m.getQuantity(), medicine);
						updatedMedicineItems.add(mItem);
					}
				}
			}
		
		
		for (MedicineData newMedicineData : newMedicineItemData) 
		{
			MedicineItem newMedicineItemInOrder = new MedicineItem(newMedicineData.getQuantity(), _medicineRepository.getOne(newMedicineData.getMedicineId()));
			updatedMedicineItems.add(newMedicineItemInOrder);
		}
		
		order.setMedicineItem(updatedMedicineItems);
		
		if (!offerDeadline.equals(order.getOfferDeadline()))
			order.setOfferDeadline(offerDeadline);
		
		return _orderRepository.save(order);
	}
	
}
