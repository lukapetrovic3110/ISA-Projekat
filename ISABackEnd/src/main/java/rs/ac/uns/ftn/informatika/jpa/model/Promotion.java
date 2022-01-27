package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=100)
	@Column(name = "caption", unique = false, nullable = false)
	private String caption;
	
	@Size(min=2, max=256)
	@Column(name = "content", unique = false, nullable = false)
	private String content;
	
	@FutureOrPresent
	@Column(name = "startOfPromotion", unique = false, nullable = false)
	private Date startOfPromotion;
	
	@Future
	@Column(name = "endOfPromotion", unique = false, nullable = false)
	private Date endOfPromotion;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	
	public Promotion() {
		
	}


	public Promotion(Long id, String caption, String content,
			Date startOfPromotion, Date endOfPromotion, Pharmacy pharmacy) {
		super();
		this.id = id;
		this.caption = caption;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
		this.pharmacy = pharmacy;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	@Override
	public String toString() {
		return "Promotion [id=" + id + ", caption=" + caption + ", content=" + content + ", startOfPromotion="
				+ startOfPromotion + ", endOfPromotion=" + endOfPromotion + ", pharmacy=" + pharmacy + "]";
	}

}
