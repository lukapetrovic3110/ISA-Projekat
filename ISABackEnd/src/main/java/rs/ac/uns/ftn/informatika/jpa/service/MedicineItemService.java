package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineItemDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReservationViewDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IMedicineItemService;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineItemRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IOrderRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IReservationRepository;

@Service
public class MedicineItemService implements IMedicineItemService{

	private IMedicineItemRepository _medicineItemRepository;

	private IPharmacyRepository _pharmacyRepository;
	
	private IMedicineRepository _medicineRepository;
	
	private IOrderRepository _orderRepository;
	
	private IReservationRepository _reservationRepository;
	
	@Autowired
	public MedicineItemService(IMedicineItemRepository medicineItemRepository, IPharmacyRepository pharmacyRepository, IMedicineRepository medicineRepository, IOrderRepository orderRepository, IReservationRepository reservationRepository) {
		this._medicineItemRepository = medicineItemRepository;
		this._pharmacyRepository = pharmacyRepository;
		this._medicineRepository = medicineRepository;
		this._orderRepository = orderRepository;
		this._reservationRepository = reservationRepository;
	}
	
	@Override
	public MedicineItem saveQuantityMedicineItem(@Valid MedicineItem mi) {
		
		int quantity = mi.getQuantity() - 1;
		
		MedicineItem medicineItem = _medicineItemRepository.findById(mi.getMedicine().getMedicineId()).orElse(null);
		medicineItem.setMedicine(mi.getMedicine());
		medicineItem.setQuantity(quantity);
		System.out.println(quantity +  "*****************");
		this._medicineItemRepository.save(medicineItem);
		return medicineItem;
	}
	
	@Override
	public MedicineItem increaseQuantityMedicineItem(@Valid MedicineItem mi) {
		
		int quantity = mi.getQuantity() + 1;
		
		MedicineItem medicineItem = _medicineItemRepository.findById(mi.getMedicine().getMedicineId()).orElse(null);
		medicineItem.setMedicine(mi.getMedicine());
		medicineItem.setQuantity(quantity);
		System.out.println(quantity +  "*****************");
		this._medicineItemRepository.save(medicineItem);
		return medicineItem;
	}

	@Override
	public void findMedicineItmeAndChangeQuantity(MedicineAvailableInPharmacyDTO dto) {
		Set<MedicineItem> all = dto.getPharmacy().getMedicineItem();
		for (MedicineItem m : all) {
			if(m.getMedicine().getName().equals(dto.getPriceTag().getMedicine().getName()) && m.getQuantity() > 0) {
				saveQuantityMedicineItem(m);
				break;
			}
		}
	
		}
	
	@Override
	public void findMedicineItemAndIncreaseQuantity(ReservationViewDTO r) {
		Set<MedicineItem> all = r.getPharmacy().getMedicineItem();
		for (MedicineItem m : all) {
			if(m.getMedicine().getName().equals(r.getMedicineItem().getMedicine().getName())) {
				increaseQuantityMedicineItem(m);
				break;
			}
		}
	
		}

	@Override
	public List<MedicineItemDTO> findMedicineItemsByPharmacy(Long pharmacyId) {
		Pharmacy pharmacy = _pharmacyRepository.getOne(pharmacyId);
		List<MedicineItemDTO> medicineItems = new ArrayList<MedicineItemDTO>();
		
		// manual convert Set to List
		for (MedicineItem medicineItem : pharmacy.getMedicineItem()) {
			Medicine m = medicineItem.getMedicine();
			MedicineItemDTO medicineDTO = new MedicineItemDTO(medicineItem.getMedicineItemId(), m.getName(), m.getMedicineId(), m.getMedicineCode(), m.getType(), m.getManufacturer(), m.getMedicineForm(), m.getPrescriptionType(), medicineItem.getQuantity());
			medicineItems.add(medicineDTO);			
		}
		
		return medicineItems;
	}
	
	@Override
	public List<MedicineItemDTO> findPotentiallyNewMedicineItemsForPharmacy(Long pharmacyId) {
		Pharmacy pharmacy = _pharmacyRepository.getOne(pharmacyId);
		Set<MedicineItem> medicineItemsInPharmacy = pharmacy.getMedicineItem();
		List<Medicine> allMedicine = _medicineRepository.findAll();
		List<MedicineItemDTO> potentiallyNewMedicineItemList = new ArrayList<MedicineItemDTO>();
		
		for (Medicine m : allMedicine)
		{
			Boolean isInPharmacy = false;
			for (MedicineItem mItem : medicineItemsInPharmacy) {
				if(mItem.getMedicine().getMedicineId() == m.getMedicineId())
					isInPharmacy = true;
			}
			
			if (!isInPharmacy) {
				MedicineItemDTO medicineItemDTO = new MedicineItemDTO(m.getName(), m.getMedicineId(), m.getMedicineCode(), m.getType(), m.getManufacturer(), m.getMedicineForm(), m.getPrescriptionType(), 0);
				potentiallyNewMedicineItemList.add(medicineItemDTO);
			}
			
		}
		
		return potentiallyNewMedicineItemList;
	}
	
	@Override
	public List<MedicineItemDTO> findMedicineItemsByOrderId(Long orderId) {
		List<Long> orderMedicineItemIds = _orderRepository.findMedicineItemIdsByOrderId(orderId);
		List<MedicineItemDTO> list = new ArrayList<MedicineItemDTO>();
		for (Long medicineItemId : orderMedicineItemIds) {
			MedicineItem medicineItem = _medicineItemRepository.getOne(medicineItemId);
			Medicine m = medicineItem.getMedicine();
			MedicineItemDTO medicineItemDTO = new MedicineItemDTO(m.getName(), m.getMedicineId(), m.getMedicineCode(), m.getType(), m.getManufacturer(), m.getMedicineForm(), m.getPrescriptionType(), medicineItem.getQuantity());
			list.add(medicineItemDTO);
		}
		
		return list;
	}
	
	@Override
	public List<MedicineItemDTO> findMedicineItemsNotExistByOrderId(Long orderId) {
		List<Long> orderMedicineItemIds = _orderRepository.findMedicineItemIdsByOrderId(orderId);
		List<MedicineItemDTO> list = new ArrayList<MedicineItemDTO>();
		List<Medicine> allMedicine = _medicineRepository.findAll();	
		
		for (Medicine m : allMedicine) {
			if (!isMedicineInOrder(orderMedicineItemIds, m)) {
				MedicineItemDTO medicineItemDTO = new MedicineItemDTO(m.getName(), m.getMedicineId(), m.getMedicineCode(), m.getType(), m.getManufacturer(), m.getMedicineForm(), m.getPrescriptionType(), 0);
				list.add(medicineItemDTO);
			}
		}
		
		return list;
	}

	private Boolean isMedicineInOrder(List<Long> orderMedicineItemIds, Medicine m) {
		for (Long medicineItemId : orderMedicineItemIds) {
			Medicine medicineInOrder = _medicineItemRepository.getOne(medicineItemId).getMedicine();
			if (m.getMedicineId() == medicineInOrder.getMedicineId()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean deleteMedicineItem(Long deleteMedicineItemId) {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Reservation> allReservations = _reservationRepository.findAll();
		MedicineItem medicineItem = _medicineItemRepository.getOne(deleteMedicineItemId);
		Pharmacy pharmacy = _pharmacyRepository.getOne(pAdmin.getPharmacy().getPharmacyId());
		
		Date date = new Date();
		System.out.println("Date now " + date.toString());
		
		for (Reservation r : allReservations) {
			Date deadline = r.getDeadline();
	
			if (r.getRecieved() == false && r.getCancelled() == false
					&& r.getPharmacy().getPharmacyId() == pharmacy.getPharmacyId()
				&& deadline.after(date) && r.getMedicineItem().getMedicine().getMedicineId() == medicineItem.getMedicine().getMedicineId()) {
				return false;
				
			}
		}
		
		Set<MedicineItem> medicineItemsInPharmacy = pharmacy.getMedicineItem();
		
		for (MedicineItem m : medicineItemsInPharmacy) {
			if (m.getMedicine().getMedicineId() == medicineItem.getMedicine().getMedicineId()) {
				medicineItemsInPharmacy.remove(m);
				break;
			}
		}
		
		pharmacy.setMedicineItem(medicineItemsInPharmacy);
		_pharmacyRepository.save(pharmacy);
		return true;
	}

}
