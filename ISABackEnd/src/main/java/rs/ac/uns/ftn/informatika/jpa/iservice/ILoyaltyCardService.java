package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.service.LoyaltyCardDTO;

public interface ILoyaltyCardService {
	LoyaltyCardDTO findLoyaltyCardByUserId(Long id);
}
