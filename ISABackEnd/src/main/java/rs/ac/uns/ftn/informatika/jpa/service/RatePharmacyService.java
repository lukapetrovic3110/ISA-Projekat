package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IRatePharmacyService;
import rs.ac.uns.ftn.informatika.jpa.model.RateEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.RatePharmacy;
import rs.ac.uns.ftn.informatika.jpa.repository.IRatePharmacyRepository;

@Service
public class RatePharmacyService implements IRatePharmacyService{

	@Autowired
	private IRatePharmacyRepository _ratePharmacyRepository;

	@Override
	public Double ratePharmacy(RatePharmacy rate) {
		ArrayList<RatePharmacy> ratings = (ArrayList<RatePharmacy>) _ratePharmacyRepository.findAll();
		boolean found = false;
		for (RatePharmacy ratePharmacy : ratings) {
			if(ratePharmacy.getPatient().getUserId() == rate.getPatient().getUserId()
					&& ratePharmacy.getPharmacy().getPharmacyId() == rate.getPharmacy().getPharmacyId()) {
				ratePharmacy.setRating(rate.getRating());
				_ratePharmacyRepository.save(ratePharmacy);
				found = true;
				break;
			}
		}
		if(!found) {
			_ratePharmacyRepository.save(rate);
		}
		return calculateRatingForPharmacy(rate);
	}
	

	@Override
	public Double calculateRatingForPharmacy(RatePharmacy rate) {
		ArrayList<RatePharmacy> ratings = (ArrayList<RatePharmacy>) _ratePharmacyRepository.findAll();
		double numberOfRatings = 0;
		double rateSum = 0;
		for (RatePharmacy ratePharmacy : ratings) {
			if(ratePharmacy.getPharmacy().getPharmacyId() == rate.getPharmacy().getPharmacyId()) {
				numberOfRatings++;
				rateSum += ratePharmacy.getRating();
			}
		}
		
		Double rating = rateSum/numberOfRatings;
		return rating;
	}
	
}
