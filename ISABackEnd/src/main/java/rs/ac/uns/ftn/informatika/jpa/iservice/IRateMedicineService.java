package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.model.RateMedicine;

public interface IRateMedicineService {

	Double rateMedicine(RateMedicine rate);

	Double calculateRatingForMedicine(RateMedicine rate);

}
