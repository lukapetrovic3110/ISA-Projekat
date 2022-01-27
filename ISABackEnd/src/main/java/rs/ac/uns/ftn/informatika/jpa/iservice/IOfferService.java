package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OfferDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.OfferForOrderDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Offer;
import rs.ac.uns.ftn.informatika.jpa.model.Order;
import rs.ac.uns.ftn.informatika.jpa.model.Supplier;

@Service
public interface IOfferService {

	List<Offer> findAll();
	
	List<OfferForOrderDTO> findOffersByOrderId(Long orderId);
	
	Boolean accept(Long offerId);
	
	Offer save(Offer offer);

	ArrayList<OfferDTO> findOffersBySupplier(Long id);
	
	Offer findById(Long id);
	
	Boolean checkMedicineAvailable(Order order, Supplier supplier);

	Boolean checkOffer(OfferDTO offerDTO, Supplier supplier);
	
	void createOffer(Long order_id, OfferDTO offerDTO);
	
	Offer changeOffer(OfferDTO offerDTO);
	
}
