package rs.ac.uns.ftn.informatika.jpa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OrderDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IOrderService;
import rs.ac.uns.ftn.informatika.jpa.model.Order;
import rs.ac.uns.ftn.informatika.jpa.model.OrderStatus;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService{

	private IOrderRepository _orderRepository;
	
	@Autowired
	public OrderService(IOrderRepository orderRepository) {
		this._orderRepository = orderRepository;
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
				if (order.getOfferDeadline().before(currentDate)) {
					order.setOrderStatus(OrderStatus.PROCESSED);
					_orderRepository.save(order);
				}
				String deadline = new SimpleDateFormat("dd.MM.yyyy.").format(order.getOfferDeadline());
				OrderDTO orderDTO = new OrderDTO(order.getOrderId(), deadline, order.getPharmacyAdministrator().getFirstName() + " " + order.getPharmacyAdministrator().getLastName(), order.getOrderStatus());
				list.add(orderDTO);
			}
		}
		return list;
	}

}
