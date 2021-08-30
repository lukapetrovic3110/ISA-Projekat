package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.RateMedicine;
import rs.ac.uns.ftn.informatika.jpa.model.RatePharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineService;
import rs.ac.uns.ftn.informatika.jpa.service.RateMedicineService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/rateMedicine")
public class RateMedicineController {
	@Autowired 
	private RateMedicineService _rateMedicineService;
	
	@Autowired 
	private MedicineService _medicineService;
	
	@PostMapping(value = "/rate")
	public void rateEmployeePharmacist(@RequestBody RateMedicine rate) {
		if(rate.getRating()<6 || rate.getRating()>10) {
			return;
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		rate.setPatient((Patient) user);
		Double newRating = _rateMedicineService.rateMedicine(rate);
		_medicineService.updateRating(rate.getMedicine().getMedicineId(), newRating);
		
	}

}
