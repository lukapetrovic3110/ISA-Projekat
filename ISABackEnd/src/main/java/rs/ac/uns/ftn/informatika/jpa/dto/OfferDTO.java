package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import rs.ac.uns.ftn.informatika.jpa.model.Order;
import rs.ac.uns.ftn.informatika.jpa.model.Status;
import rs.ac.uns.ftn.informatika.jpa.model.Supplier;

public class OfferDTO {

	private Long id;
	
	private Double price;
	
    private String deliveryDeadline;
	    
    private Boolean isApproved;
    
    private Status status;
    
    private Supplier supplier;
    
    private Order order;
    
    public OfferDTO() {}

	public OfferDTO(Long id, Double price, String deliveryDeadline, Status status, Supplier supplier,
			Order order, Boolean isApproved) {
		super();
		this.id = id;
		this.price = price;
		this.deliveryDeadline = deliveryDeadline;
		this.status = status;
		this.supplier = supplier;
		this.order = order;
		this.isApproved = isApproved;
	}

	public OfferDTO(Long id, Double price, String deliveryDeadline, Status status, Supplier supplier,
			Order order) {
		super();
		this.id = id;
		this.price = price;
		this.deliveryDeadline = deliveryDeadline;
		this.status = status;
		this.supplier = supplier;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFinalPrice() {
		return price;
	}

	public void setFinalPrice(Double finalPrice) {
		this.price = finalPrice;
	}

	public String getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public void setDeliveryDeadline(String deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
