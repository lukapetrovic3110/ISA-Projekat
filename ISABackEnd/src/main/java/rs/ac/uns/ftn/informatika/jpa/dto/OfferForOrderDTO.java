package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.OrderStatus;

public class OfferForOrderDTO {
	
	private Long offerId;
	private String supplierName;
	private String supplierEmail;
	private Double offerPrice;
	private String deliveryDeadline;
	private Long adminId;
	private OrderStatus orderStatus;
	
	public OfferForOrderDTO(Long offerId, String supplierName, String supplierEmail, Double offerPrice,
			String deliveryDeadline, Long adminId, OrderStatus orderStatus) {
		super();
		this.offerId = offerId;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.offerPrice = offerPrice;
		this.deliveryDeadline = deliveryDeadline;
		this.adminId = adminId;
		this.orderStatus = orderStatus;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public Double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getDeliveryDeadline() {
		return deliveryDeadline;
	}
	public void setDeliveryDeadline(String deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
