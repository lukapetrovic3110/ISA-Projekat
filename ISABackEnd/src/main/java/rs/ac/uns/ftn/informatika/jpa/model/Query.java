package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Query {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long queryId;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Medicine medicine;
   
	public Query(Long queryId, Medicine medicine) {
		super();
		this.queryId = queryId;
		this.medicine = medicine;
	}
	
	public Query()
	{
		
	}
	
	public Long getQueryId() {
		return queryId;
	}
	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}