package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
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
public class ReportDerm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;
	
	@OneToOne(fetch = FetchType.LAZY)
    public Examination examination;
	  
	@Column(name = "information")
	public String information;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public Set<Recommendation> recommendations = new HashSet<Recommendation>();
	
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Set<Recommendation> getRecommendation() {
		return recommendations;
	}

	public void setRecommendation(Set<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}

	public ReportDerm(Long reportId, Examination examination) {
		super();
		this.reportId = reportId;
		this.examination = examination;
		this.recommendations = new HashSet<Recommendation>();
	}

	public ReportDerm()
	{
		
	}
}