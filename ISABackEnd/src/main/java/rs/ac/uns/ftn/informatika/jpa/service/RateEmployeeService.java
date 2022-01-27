package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IRateEmployeeService;
import rs.ac.uns.ftn.informatika.jpa.model.RateEmployee;
import rs.ac.uns.ftn.informatika.jpa.repository.IRateEmployeeRepository;

@Service
public class RateEmployeeService implements IRateEmployeeService{

	@Autowired
	private IRateEmployeeRepository _rateEmployeeRepository;

	@Override
	public Double rateEmployee(RateEmployee rate) {
		ArrayList<RateEmployee> ratings = (ArrayList<RateEmployee>) _rateEmployeeRepository.findAll();
		boolean found = false;
		for (RateEmployee rateEmployee : ratings) {
			if(rateEmployee.getPatient().getUserId() == rate.getPatient().getUserId()
					&& rateEmployee.getPharmacyEmployee().getUserId() == rate.getPharmacyEmployee().getUserId()) {
				rateEmployee.setRating(rate.getRating());
				_rateEmployeeRepository.save(rateEmployee);
				found = true;
				break;
			}
		}
		if(!found) {
			_rateEmployeeRepository.save(rate);
		}
		return calculateRatingForEmployee(rate);
	}
	
	@Override
	public Double calculateRatingForEmployee(RateEmployee rate) {
		ArrayList<RateEmployee> ratings = (ArrayList<RateEmployee>) _rateEmployeeRepository.findAll();
		double numberOfRatings = 0;
		double rateSum = 0;
		for (RateEmployee rateEmployee : ratings) {
			if(rateEmployee.getPharmacyEmployee().getUserId() == rate.getPharmacyEmployee().getUserId()) {
				numberOfRatings++;
				rateSum += rateEmployee.getRating();
			}
		}
		Double rating = rateSum/numberOfRatings;
		return rating;
	}

}
