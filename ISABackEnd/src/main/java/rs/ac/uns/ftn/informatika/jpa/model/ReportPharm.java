package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class ReportPharm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Consultation consultation;
	  
	@Column(name = "information")
	public String information;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public Set<Recommendation> recommendation;
	
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}


	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Set<Recommendation> getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Set<Recommendation> recommendation) {
		this.recommendation = recommendation;
	}

	public ReportPharm(Long reportId, Consultation consultation) {
		super();
		this.reportId = reportId;
		this.consultation = consultation;
	}

	public ReportPharm()
	{
		
	}

}
