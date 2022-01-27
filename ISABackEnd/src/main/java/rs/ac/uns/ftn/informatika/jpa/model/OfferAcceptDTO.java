package rs.ac.uns.ftn.informatika.jpa.model;

public class OfferAcceptDTO {
	
	private Long offerId;
	
	public OfferAcceptDTO() {
		// TODO Auto-generated constructor stub
	}	
	
	public OfferAcceptDTO(Long offerId) {
		super();
		this.offerId = offerId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
}
