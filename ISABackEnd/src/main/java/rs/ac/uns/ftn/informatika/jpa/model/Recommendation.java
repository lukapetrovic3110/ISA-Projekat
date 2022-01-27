package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Recommendation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;
    
	@OneToOne(fetch = FetchType.LAZY)
	public Medicine medicine;
	
	@Column(name = "duration", nullable = false)
	public Long duration;
	
    public Recommendation(Long recommendationId, Medicine medicine, Long duration) {
		super();
		this.recommendationId = recommendationId;
		this.medicine = medicine;
		this.duration = duration;
	}
     
    public Recommendation()
    {
    	
    }

	public Long getRecommendationId() {
		return recommendationId;
	}
	
	public void setRecommendationId(Long recommendationId) {
		this.recommendationId = recommendationId;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

}