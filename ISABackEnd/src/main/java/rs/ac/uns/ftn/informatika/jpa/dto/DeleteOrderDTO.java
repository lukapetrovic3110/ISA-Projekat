package rs.ac.uns.ftn.informatika.jpa.dto;

public class DeleteOrderDTO {
	private Long orderId; 
	
	public DeleteOrderDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DeleteOrderDTO(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
}
