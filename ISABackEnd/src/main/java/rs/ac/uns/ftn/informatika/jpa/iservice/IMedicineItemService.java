package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineItemDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;

@Service
public interface IMedicineItemService {
	
	public MedicineItem saveQuantityMedicineItem(MedicineItem mi);

	void findMedicineItmeAndChangeQuantity(MedicineAvailableInPharmacyDTO dto);
	
	List<MedicineItemDTO> findMedicineItemsByPharmacy(Long pharmacyId);

	List<MedicineItemDTO> findPotentiallyNewMedicineItemsForPharmacy(Long pharmacyId);

	MedicineItem increaseQuantityMedicineItem(@Valid MedicineItem mi);

	void findMedicineItemAndIncreaseQuantity(ReservationViewDTO r);

	List<MedicineItemDTO> findMedicineItemsByOrderId(Long orderId);
	
	List<MedicineItemDTO> findMedicineItemsNotExistByOrderId(Long orderId);

	Boolean deleteMedicineItem(Long deleteMedicineItemId);
}
