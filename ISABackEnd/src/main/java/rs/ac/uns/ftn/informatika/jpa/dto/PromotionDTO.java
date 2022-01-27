package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PromotionDTO {
	
	@Size(min = 1, max = 100, message = "Caption has to have at least 1 character and maximum 100.")
	private String caption;
	
	@Size(min = 2, max = 256, message = "Content has to have at least 2 charachters and maximum 256.")
	private String content;
	
	@FutureOrPresent(message = "The promotion can start today or in the coming day!")
	private Date startOfPromotion;
	
	@Future(message = "The promotion must end in the day in a future!")
	private Date endOfPromotion;
	
	@NotNull
	private Long pharmacyId;
	
	public PromotionDTO() {
		
	}
	
	public PromotionDTO(String caption, String content, Date startOfPromotion, Date endOfPromotion, Long pharmacyId) {
		super();
		this.caption = caption;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
		this.pharmacyId = pharmacyId;
	}
	
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartOfPromotion() {
		return startOfPromotion;
	}

	public void setStartOfPromotion(Date startOfPromotion) {
		this.startOfPromotion = startOfPromotion;
	}

	public Date getEndOfPromotion() {
		return endOfPromotion;
	}

	public void setEndOfPromotion(Date endOfPromotion) {
		this.endOfPromotion = endOfPromotion;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}	
}
