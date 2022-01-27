package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ReportDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IReportDermService;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Recommendation;
import rs.ac.uns.ftn.informatika.jpa.model.ReportDerm;
import rs.ac.uns.ftn.informatika.jpa.repository.IExaminationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IRecommendationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReportDermRepository;

@Service
public class ReportDermService implements IReportDermService{
	
	@Autowired
	private IReportDermRepository _reportDermRepository;
	
	@Autowired
	private IRecommendationRepository _recommendationRepository;
	
	@Autowired 
	private IExaminationRepository _examinationRepository;
	
	
	public ReportDerm saveReportDerm(ReportDTO reportDTO) {
	
		Examination examination = _examinationRepository.findById(reportDTO.getAppointmentId()).orElse(null);
		ReportDerm reportDerm = new ReportDerm();	
		
		reportDerm.setExamination(examination);
		reportDerm.setInformation(reportDTO.getInfo());
		reportDerm.setRecommendation(reportDTO.getRecommendations());
		
		for (Recommendation r : reportDerm.getRecommendation()) {
			this._recommendationRepository.save(r);
		}
		this._reportDermRepository.save(reportDerm);
		
		return reportDerm;
	}
	
}
