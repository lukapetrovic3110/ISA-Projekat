package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Promotion;

public interface IPromotionService {
	
	Promotion savePromotionAndSendEmail(Promotion promotion);
	
	Pharmacy subscribeToPharmacy(Long pharmacyId) throws Exception;
	
	void unsubscribeToPharmacy(Long pharmacyId) throws Exception;

}
