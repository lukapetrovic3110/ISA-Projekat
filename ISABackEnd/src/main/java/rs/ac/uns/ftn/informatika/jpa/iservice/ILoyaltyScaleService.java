package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.LoyaltyScaleDTO;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyScale;

@Service
public interface ILoyaltyScaleService {

	LoyaltyScale saveLoyaltyScale();
	
	void calculateLoyaltyCategoryForPatients();
	
	LoyaltyScale createLoyaltyScale(LoyaltyScaleDTO loyaltyScaleDTO);
	
}
