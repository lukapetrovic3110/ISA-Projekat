package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyRegisterDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IAddressService;
import rs.ac.uns.ftn.informatika.jpa.iservice.IPharmacyService;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Supplier;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.repository.IPatientRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class PharmacyService implements IPharmacyService {

	@Autowired
	private IPharmacyRepository _pharmacyRepository;

	@Autowired
	private IPatientRepository _patientRepository;
	
	@Autowired
	private IAddressService _addressService;
	
	@Override
	public Pharmacy findById(Long id) {
		return _pharmacyRepository.findById(id).orElse(null);
	}
	
	@Override
	public PharmacyDTO getPharmacyById(Long pharmacyId) {		
		Pharmacy p = _pharmacyRepository.findById(pharmacyId).orElse(null);
		
		PharmacyDTO result = new PharmacyDTO(p.getName(), p.getAddress().getStreet(), p.getAddress().getStreetNumber(), p.getAddress().getCity(), p.getAddress().getCountry(), p.getAddress().getLongitude(), p.getAddress().getLatitude(), p.getRating(), p.getDescription());
		
		return result;
	}

	public ArrayList<Pharmacy> findAllPharmacy() {
		return (ArrayList<Pharmacy>) _pharmacyRepository.findAll();
	}

	@Override
	public ArrayList<Pharmacy> getPharmacyByNameOrAddress(String parametar) {
		ArrayList<Pharmacy> result = getPharmacyByName(parametar);
		if(!result.isEmpty()) {
			return result;
		}
		result = getPharmacyByAddress(parametar);
		
		return result;
	}
	
	public ArrayList<Pharmacy> getPharmacyByName(String name){
		if(name.trim().equals("")) {
			return findAllPharmacy();
		}
		ArrayList<Pharmacy> pharmacies = findAllPharmacy();
		ArrayList<Pharmacy> result = new ArrayList<Pharmacy>();
		for (Pharmacy pharmacy : pharmacies) {
			if(pharmacy.getName().toLowerCase().contains(name.toLowerCase().trim())) {
				result.add(pharmacy);
			}
		}
		return result;
	}
	
	public ArrayList<Pharmacy> getPharmacyByAddress(String address){
		ArrayList<Pharmacy> pharmacies = findAllPharmacy();
		ArrayList<Pharmacy> result = new ArrayList<Pharmacy>();
		String city = address.trim();
		for (Pharmacy pharmacy : pharmacies) {
			if(pharmacy.getAddress().getCity().toLowerCase().contains(city.toLowerCase().trim())) {
				result.add(pharmacy);
			}
		}		
		return result;
	}

	@Override
	public ArrayList<Pharmacy> filtratePharmaciesByRating(Long rating, ArrayList<Pharmacy> pharmacies) {
		ArrayList<Pharmacy> result = new ArrayList<Pharmacy>();
		for (Pharmacy pharmacy : pharmacies) {
			if(pharmacy.getRating() >= rating) 
				result.add(pharmacy);
		}
		return result;
	}

	@Override
	public List<Pharmacy> getSubscribedPharmacyForPatient(Long patient_id) {
		
		Patient current_logged = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patient = _patientRepository.findById(current_logged.getUserId()).orElse(null);
	
		Pharmacy p = new Pharmacy();
		List<Pharmacy> pharmacies = _pharmacyRepository.findAll();
		List<Pharmacy> ph_list = new ArrayList<>();		
		
		for(Pharmacy pharmacy: patient.getPharmacies()) {
			for(Pharmacy pharm: pharmacies) {
				if(pharmacy.getPharmacyId() == pharm.getPharmacyId()) {
					if(!ph_list.contains(pharm)) {
						ph_list.add(pharm);
					}
				}
			}
		}
		
		return ph_list;
	}
	public void updateRating(Long pharmacyId, Double newRating) {
		Pharmacy existing = _pharmacyRepository.findById(pharmacyId).orElse(null);
		if(existing!= null) {
			existing.setRating(newRating);
			_pharmacyRepository.save(existing);
		}
	}
	
	@Override
	public Pharmacy createPharmacy(PharmacyRegisterDTO pharmacyDTO) {
		Pharmacy pharmacy = new Pharmacy();
		
		pharmacy.setName(pharmacyDTO.getName());
		pharmacy.setDescription(pharmacyDTO.getDescription());
		
		Address a = new Address();
		a = pharmacyDTO.getAddress();
		pharmacy.setAddress(a);
		pharmacy.setConsultationPrice(pharmacyDTO.getConsultationPrice());
		this._addressService.createAddress(pharmacyDTO.getAddress());
		return _pharmacyRepository.save(pharmacy);
	}
	
	@Override
	public ArrayList<Pharmacy> getAllByDermatologist(Long id){
		
		ArrayList<Pharmacy> all = findAllPharmacy();
		ArrayList<Pharmacy> result = new ArrayList<Pharmacy>();
		
		for(Pharmacy p : all) {
			for(Dermatologist d: p.getDermatologist()) {
				if(id.equals(d.getUserId())){
					result.add(p);
				}
			}
		}
		return result;
		
	}

}
