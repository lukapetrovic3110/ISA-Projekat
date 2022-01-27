package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.OfferDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.OfferForOrderDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Offer;
import rs.ac.uns.ftn.informatika.jpa.model.OfferAcceptDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Status;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {
	
	private OfferService _offerService;
	private UserService _userService;
	
	@Autowired
	public OfferController(OfferService offerService, UserService userService) {
		this._offerService = offerService;
		this._userService = userService;
	}
	
	@GetMapping(path = "/seeOffers/{id}")
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ArrayList<OfferDTO> findOffers(@PathVariable Long id) {		
		return  _offerService.findOffersBySupplier(id);
	}

	@GetMapping(path = "/filtrate/{status}/{id}")
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ArrayList<OfferDTO> filtrateOffers(@PathVariable Status status, @PathVariable Long id){
		
		ArrayList<OfferDTO> offers = findOffers(id);
		User user = _userService.findById(id);
		
		ArrayList<OfferDTO> filtrateOffers = new ArrayList<>();

		if(user.getUserId() == id) {
			for(OfferDTO o : offers) {
				if(o.getStatus().equals(status)) {
					filtrateOffers.add(o);
				}
			}
		}
		return filtrateOffers; 
	}
	
	@PostMapping(value = "/createOffer/{order_id}/add")
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<?> createOffer(@PathVariable Long order_id, @RequestBody OfferDTO offerDTO){
		try {
			_offerService.createOffer(order_id, offerDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(path="/findOffersByOrderId/{orderId}")
	public ResponseEntity<List<OfferForOrderDTO>> findOffersByOrder(@PathVariable Long orderId) {
		List<OfferForOrderDTO> offers = _offerService.findOffersByOrderId(orderId);
		return new ResponseEntity<List<OfferForOrderDTO>>(offers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value="/acceptOffer")
	@Transactional
	public ResponseEntity<Boolean> accept(@RequestBody OfferAcceptDTO offerAcceptDTO) {
		try {
			return new ResponseEntity<Boolean>(_offerService.accept(offerAcceptDTO.getOfferId()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	@PostMapping(value="/changeOffer")
	public ResponseEntity<?> changeOffer(@RequestBody OfferDTO offerDTO) {
		try {
			_offerService.changeOffer(offerDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}