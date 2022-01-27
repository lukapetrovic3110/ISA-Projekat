package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Contraindication {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long contraindicationId;
   
   @Column(name = "description", nullable = false)
   private String description;
   
	public Contraindication(String description, Long contraindicationId) {
		super();
		this.description = description;
		this.contraindicationId = contraindicationId;
	}
	
	public Contraindication()
	{
		
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getContraindicationId() {
		return contraindicationId;
	}
	public void setContraindicationId(Long contraindicationId) {
		this.contraindicationId = contraindicationId;
	}

}