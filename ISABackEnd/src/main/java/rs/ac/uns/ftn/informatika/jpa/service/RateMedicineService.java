package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IRateMedicineService;
import rs.ac.uns.ftn.informatika.jpa.model.RateEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.RateMedicine;
import rs.ac.uns.ftn.informatika.jpa.repository.IRateMedicineRepository;

@Service
public class RateMedicineService implements IRateMedicineService{

	@Autowired
	private IRateMedicineRepository _rateMedicineRepository;

	@Override
	public Double rateMedicine(RateMedicine rate) {
		ArrayList<RateMedicine> ratings = (ArrayList<RateMedicine>) _rateMedicineRepository.findAll();
		boolean found = false;
		for (RateMedicine rateMedicine : ratings) {
			if(rateMedicine.getPatient().getUserId() == rate.getPatient().getUserId()
					&& rateMedicine.getMedicine().getMedicineId() == rate.getMedicine().getMedicineId()) {
				rateMedicine.setRating(rate.getRating());
				_rateMedicineRepository.save(rateMedicine);
				found = true;
				break;
			}
		}
		if(!found) {
			_rateMedicineRepository.save(rate);
		}
		return calculateRatingForMedicine(rate);
	}
	

	@Override
	public Double calculateRatingForMedicine(RateMedicine rate) {
		ArrayList<RateMedicine> ratings = (ArrayList<RateMedicine>) _rateMedicineRepository.findAll();
		double numberOfRatings = 0;
		double rateSum = 0;
		for (RateMedicine rateMedicine : ratings) {
			if(rateMedicine.getMedicine().getMedicineId() == rate.getMedicine().getMedicineId()) {
				numberOfRatings++;
				rateSum += rateMedicine.getRating();
			}
		}
		Double rating = rateSum/numberOfRatings;
		return rating;
	}
	
}
