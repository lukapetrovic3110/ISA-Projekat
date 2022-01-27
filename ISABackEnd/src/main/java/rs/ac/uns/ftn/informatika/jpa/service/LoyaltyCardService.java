package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.ILoyaltyCardService;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCard;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.repository.ILoyaltyCardRepository;

@Service
public class LoyaltyCardService implements ILoyaltyCardService{
	
	@Autowired ILoyaltyCardRepository _loyaltyCardRepository;

	@Override
	public LoyaltyCardDTO findLoyaltyCardByUserId(Long id) {
		ArrayList<LoyaltyCard> cards = (ArrayList<LoyaltyCard>) _loyaltyCardRepository.findAll();
		for (LoyaltyCard loyaltyCard : cards) {
			Patient p = loyaltyCard.getPatient();
			if(p.getUserId() == id)
				return new LoyaltyCardDTO(loyaltyCard.getPoints(), loyaltyCard.getLoyaltyCategory());
		}
		return null;
	}
	
	
	
}
