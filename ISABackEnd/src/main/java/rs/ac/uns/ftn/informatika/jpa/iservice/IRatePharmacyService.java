package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.model.RatePharmacy;

public interface IRatePharmacyService {

	Double ratePharmacy(RatePharmacy rate);

	Double calculateRatingForPharmacy(RatePharmacy rate);

}
