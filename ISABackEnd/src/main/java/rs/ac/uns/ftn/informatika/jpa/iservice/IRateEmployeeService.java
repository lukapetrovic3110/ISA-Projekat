package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.model.RateEmployee;

public interface IRateEmployeeService {

	Double rateEmployee(RateEmployee rate);

	Double calculateRatingForEmployee(RateEmployee rate);

}
