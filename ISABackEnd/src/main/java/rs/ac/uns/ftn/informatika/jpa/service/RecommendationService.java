package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.RecommendationDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IRecommendationService;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.Recommendation;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IRecommendationRepository;

@Service
public class RecommendationService implements IRecommendationService {

	@Autowired
	private IRecommendationRepository _recommendationRepository;
	
	@Autowired
	private IMedicineRepository _medicineRepository;
	
	public Recommendation saveRecommendation(RecommendationDTO recommendationDTO) {
		
		Medicine medicine = _medicineRepository.findById(recommendationDTO.getMedicineId()).orElse(null);
		
		Recommendation recommendation = new Recommendation();
		
		recommendation.setDuration(recommendationDTO.getDuration());
		recommendation.setMedicine(medicine);
		
		this._recommendationRepository.save(recommendation);
		
		return recommendation;
	}
}
