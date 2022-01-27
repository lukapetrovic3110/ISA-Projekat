package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.HashSet;
import java.util.Set;

import rs.ac.uns.ftn.informatika.jpa.model.Recommendation;

public class ReportDTO {

	private String info;
	private Long appointmentId;
	private Set<Recommendation> recommendations = new HashSet<Recommendation>();
	
	public ReportDTO() {}
	
	public ReportDTO(String info, Long appointmentId) {
		super();
		this.info = info;
		this.appointmentId = appointmentId;
		this.recommendations = new HashSet<Recommendation>();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Set<Recommendation> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
	
}
