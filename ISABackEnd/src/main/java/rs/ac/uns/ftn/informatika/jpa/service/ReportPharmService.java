package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ReportDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IReportPharmService;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Recommendation;
import rs.ac.uns.ftn.informatika.jpa.model.ReportDerm;
import rs.ac.uns.ftn.informatika.jpa.model.ReportPharm;
import rs.ac.uns.ftn.informatika.jpa.repository.IConsultationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IExaminationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IRecommendationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReportDermRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReportPharmRepository;

@Service
public class ReportPharmService implements IReportPharmService {

	@Autowired
	private IReportPharmRepository _reportPharmRepository;
	
	@Autowired
	private IRecommendationRepository _recommendationRepository;
	
	@Autowired 
	private IConsultationRepository _consultationRepository;
	
	
	public ReportPharm saveReportPharm(ReportDTO reportDTO) {
	
		Consultation consultation = _consultationRepository.findById(reportDTO.getAppointmentId()).orElse(null);
		ReportPharm reportPharm = new ReportPharm();	
		
		reportPharm.setConsultation(consultation);
		reportPharm.setInformation(reportDTO.getInfo());
		reportPharm.setRecommendation(reportDTO.getRecommendations());
		
		for (Recommendation r : reportPharm.getRecommendation()) {
			this._recommendationRepository.save(r);
		}
		
		this._reportPharmRepository.save(reportPharm);
		
		return reportPharm;
	}
	
}
