package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offerId;
   
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Supplier supplier;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   
	public Order order;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@Column(name = "deliveryDeadline", nullable = false)
	private Date deliveryDeadline;
	
	@Column(name = "price", nullable = false)
	private Double price;   
	
	public Offer(Date deliveryDeadline, Double price, Long offerId, Supplier supplier, Order order, Status status) {
		super();
		this.deliveryDeadline = deliveryDeadline;
		this.price = price;
		this.offerId = offerId;
		this.supplier = supplier;
		this.order = order;
		this.status = status;
	}
	
	public Offer()
	{
		
	}
	
	public Date getDeliveryDeadline() {
		return deliveryDeadline;
	}
	
	public void setDeliveryDeadline(Date deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getOfferId() {
		return offerId;
	}
	
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}