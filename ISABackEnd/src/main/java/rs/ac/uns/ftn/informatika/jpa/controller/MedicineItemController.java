package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.DeleteMedicineItemRequestDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineItemDTO;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineItemService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/medicineItem", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineItemController {
	
	private MedicineItemService _medicineItemService;
	
	@Autowired
	public MedicineItemController (MedicineItemService medicineItemService) {
		this._medicineItemService = medicineItemService;
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/findMedicineItemsByPharmacy/{pharmacyId}")
	public ResponseEntity<List<MedicineItemDTO>> findMedicineItemsByPharmacy(@PathVariable Long pharmacyId) {
		List<MedicineItemDTO> medicineItemDTOs = _medicineItemService.findMedicineItemsByPharmacy(pharmacyId);
		return new ResponseEntity<List<MedicineItemDTO>>(medicineItemDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/findPotentiallyNewMedicineItemsForPharmacy/{pharmacyId}")
	public ResponseEntity<List<MedicineItemDTO>> findPotentiallyNewMedicineItemsForPharmacy(@PathVariable Long pharmacyId) {
		List<MedicineItemDTO> medicineItemDTOs = _medicineItemService.findPotentiallyNewMedicineItemsForPharmacy(pharmacyId);
		return new ResponseEntity<List<MedicineItemDTO>>(medicineItemDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/findMedicineItemsByOrderId/{orderId}")
	public ResponseEntity<List<MedicineItemDTO>> findMedicineItemsByOrderId(@PathVariable Long orderId) {
		List<MedicineItemDTO> medicineItemDTOs = _medicineItemService.findMedicineItemsByOrderId(orderId);
		return new ResponseEntity<List<MedicineItemDTO>>(medicineItemDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@GetMapping(value = "/findMedicineItemsNotExistByOrderId/{orderId}")
	public ResponseEntity<List<MedicineItemDTO>> findMedicineItemsNotExistByOrderId(@PathVariable Long orderId) {
		List<MedicineItemDTO> medicineItemDTOs = _medicineItemService.findMedicineItemsNotExistByOrderId(orderId);
		return new ResponseEntity<List<MedicineItemDTO>>(medicineItemDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PH_ADMIN')")
	@PostMapping(value = "/deleteMedicineItemByMedicineId")
	public ResponseEntity<Boolean> deleteMedicineItem(@RequestBody DeleteMedicineItemRequestDTO deleteMedicineItemRequestDTO) {
		return new ResponseEntity<Boolean>(_medicineItemService.deleteMedicineItem(deleteMedicineItemRequestDTO.getDeleteMedicineItemId()), HttpStatus.OK);
	}
	
}
