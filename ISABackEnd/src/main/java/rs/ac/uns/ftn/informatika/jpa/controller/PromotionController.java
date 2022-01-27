package rs.ac.uns.ftn.informatika.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import rs.ac.uns.ftn.informatika.jpa.dto.PromotionDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Promotion;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacyService;
import rs.ac.uns.ftn.informatika.jpa.service.PromotionService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PromotionController {
	
	private PromotionService _promotionService;
	private PharmacyService _pharmacyService;
	
	@Autowired
	public PromotionController(PromotionService promotionService, PharmacyService pharmacyService) {
		this._promotionService = promotionService;
		this._pharmacyService = pharmacyService;
	}
	
	@PostMapping(value = "/savePromotionAndSendEmail")
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	public ResponseEntity<Promotion> savePromotionAndSendEmail(@RequestBody @Valid PromotionDTO promotionDTO) {
		
		if (promotionDTO.getEndOfPromotion().before(promotionDTO.getStartOfPromotion()) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Promotion p = new Promotion();
		p.setCaption(promotionDTO.getCaption());
		p.setContent(promotionDTO.getContent());
		p.setStartOfPromotion(promotionDTO.getStartOfPromotion());
		p.setEndOfPromotion(promotionDTO.getEndOfPromotion());
		Pharmacy pharmacy = _pharmacyService.findById(promotionDTO.getPharmacyId());
		p.setPharmacy(pharmacy);
		
		try {
			_promotionService.savePromotionAndSendEmail(p);
			return new ResponseEntity<Promotion>(HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}
	///subscribeToPharmacy - Role : patient
	@PostMapping(path = "/subscribeToPharmacy/{pharmacyId}")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<?> subscribeToPharmacy(@PathVariable Long pharmacyId) {
    	try {
            return new ResponseEntity<>(this._promotionService.subscribeToPharmacy(pharmacyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	//unsubscribeToPharmacy - Role : patient
	@PostMapping(path = "/unsubscribeToPharmacy/{pharmacyId}")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<Boolean> unsubscribeToPharmacy(@PathVariable Long pharmacyId) {
		try {
    		_promotionService.unsubscribeToPharmacy(pharmacyId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
	}
	
}
