package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.dialect.Ingres10Dialect;


@Entity
public class Ingridient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingridientId; 
	
	@Column(name = "name", nullable = false)
    private String name;
	   
	public Ingridient(String name, Long ingridientId) {
		super();
		this.name = name;
		this.ingridientId = ingridientId;
	}
	
	public Ingridient()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIngridientId() {
		return ingridientId;
	}
	public void setIngridientId(Long ingridientId) {
		this.ingridientId = ingridientId;
	}

}