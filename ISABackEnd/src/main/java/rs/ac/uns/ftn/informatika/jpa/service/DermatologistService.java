package rs.ac.uns.ftn.informatika.jpa.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.CreateDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyDermatologistDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IDermatologistService;
import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.AppointmentStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Authority;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.Status;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;
import rs.ac.uns.ftn.informatika.jpa.repository.IDermatologistRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IDermatologistVacationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IExaminationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class DermatologistService implements IDermatologistService {

	private IDermatologistRepository _dermatologistRepository;
	private IPharmacyRepository _pharmacyRepository;
	private AuthorityService _authorityService;
	private AddressService _addressService;
	private IUserRepository _userRepository;
	private IExaminationRepository _examinationRepository;
	private IDermatologistVacationRepository _dermatologistVacationRepository;

	@Autowired
	public DermatologistService(IDermatologistRepository iDermatologistRepository,
			IPharmacyRepository iPharmacyRepository, AuthorityService authorityService, 
			AddressService addressService, IUserRepository userRepository,
			IExaminationRepository examinationRepository, 
			IDermatologistVacationRepository dermatologistVacationRepository) {
		this._dermatologistRepository = iDermatologistRepository;
		this._pharmacyRepository = iPharmacyRepository;
		this._addressService = addressService;
		this._userRepository = userRepository;
		this._authorityService = authorityService;
		this._examinationRepository = examinationRepository;
		this._dermatologistVacationRepository = dermatologistVacationRepository;
	}

	@Override
	public List<DermatologistDTO> getAllDermatologist() {
		List<Dermatologist> dermatologists = _dermatologistRepository.findAll();
		List<DermatologistDTO> dermatologistDTOs = new ArrayList<DermatologistDTO>();

		for (Dermatologist d : dermatologists) {

			StringBuilder pharmacyNames = new StringBuilder();

			List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

			for (int i = 0; i < pharmacyIds.size(); i++) {
				Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

				if (i == 0) {
					pharmacyNames.append(p.getName());
				} else {
					pharmacyNames.append(", " + p.getName());
				}
			}

			DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getFirstName(), d.getLastName(), d.getRating(),
					pharmacyNames.toString());

			dermatologistDTOs.add(dermatologistDTO);
		}

		return dermatologistDTOs;
	}

	@Override
	public List<DermatologistDTO> searchDermatologist(String firstName, String lastName) {
		List<Dermatologist> dermatologists = _dermatologistRepository.findAllByFirstNameAndLastName(firstName,
				lastName);
		List<DermatologistDTO> dermatologistDTOs = new ArrayList<DermatologistDTO>();
		for (Dermatologist d : dermatologists) {
			StringBuilder pharmacyNames = new StringBuilder();

			List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

			for (int i = 0; i < pharmacyIds.size(); i++) {
				Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

				if (i == 0) {
					pharmacyNames.append(p.getName());
				} else {
					pharmacyNames.append(", " + p.getName());
				}
			}

			DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getFirstName(), d.getLastName(), d.getRating(),
					pharmacyNames.toString());

			dermatologistDTOs.add(dermatologistDTO);

		}
		return dermatologistDTOs;
	}

	@Override
	public List<DermatologistDTO> searchDermatologistsByFirstName(String firstName) {
		List<Dermatologist> dermatologists = _dermatologistRepository.findAllByFirstName(firstName);
		List<DermatologistDTO> dermatologistDTOs = new ArrayList<DermatologistDTO>();
		for (Dermatologist d : dermatologists) {
			StringBuilder pharmacyNames = new StringBuilder();

			List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

			for (int i = 0; i < pharmacyIds.size(); i++) {
				Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

				if (i == 0) {
					pharmacyNames.append(p.getName());
				} else {
					pharmacyNames.append(", " + p.getName());
				}
			}

			DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getFirstName(), d.getLastName(), d.getRating(),
					pharmacyNames.toString());

			dermatologistDTOs.add(dermatologistDTO);

		}
		return dermatologistDTOs;
	}

	@Override
	public List<DermatologistDTO> searchDermatologistsByLastName(String lastName) {
		List<Dermatologist> dermatologists = _dermatologistRepository.findAllByLastName(lastName);
		List<DermatologistDTO> dermatologistDTOs = new ArrayList<DermatologistDTO>();
		for (Dermatologist d : dermatologists) {
			StringBuilder pharmacyNames = new StringBuilder();

			List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

			for (int i = 0; i < pharmacyIds.size(); i++) {
				Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

				if (i == 0) {
					pharmacyNames.append(p.getName());
				} else {
					pharmacyNames.append(", " + p.getName());
				}
			}

			DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getFirstName(), d.getLastName(), d.getRating(),
					pharmacyNames.toString());

			dermatologistDTOs.add(dermatologistDTO);

		}
		return dermatologistDTOs;
	}

	@Override
	public List<DermatologistDTO> filterDermatologistByRating(Double minRating, Double maxRating) {
		List<Dermatologist> dermatologists = _dermatologistRepository.findAll();
		List<DermatologistDTO> dermatologistDTOs = new ArrayList<DermatologistDTO>();

		for (Dermatologist d : dermatologists)
			if (d.getRating() >= minRating && d.getRating() <= maxRating) {
				StringBuilder pharmacyNames = new StringBuilder();

				List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

				for (int i = 0; i < pharmacyIds.size(); i++) {
					Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

					if (i == 0) {
						pharmacyNames.append(p.getName());
					} else {
						pharmacyNames.append(", " + p.getName());
					}
				}

				DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getFirstName(), d.getLastName(),
						d.getRating(), pharmacyNames.toString());

				dermatologistDTOs.add(dermatologistDTO);
			}

		return dermatologistDTOs;
	}

	public Set<Dermatologist> getAllDermatologistByPharmacyId(Long pharmacyId) {

		Pharmacy pharmacy = _pharmacyRepository.getOne(pharmacyId);
		Set<Dermatologist> dermatologists = pharmacy.getDermatologist();

		return dermatologists;
	}

	@Override
	public void updateRating(Long userId, Double newRating) {
		Dermatologist existing = _dermatologistRepository.findById(userId).orElse(null);
		if (existing != null) {
			existing.setRating(newRating);
			_dermatologistRepository.save(existing);
		}

	}

	@Override
	public List<PharmacyDermatologistDTO> findDermatologistsByPharmacy() {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<Dermatologist> dermatologists = _dermatologistRepository.findAll();
		List<PharmacyDermatologistDTO> dermatologistDTOs = new ArrayList<PharmacyDermatologistDTO>();

		for (Dermatologist d : dermatologists) {

			StringBuilder pharmacyNames = new StringBuilder();

			List<Long> pharmacyIds = _dermatologistRepository.findAllPharmacyIdsByDermatologistId(d.getUserId());

			if (pharmacyIds.contains(pAdmin.getPharmacy().getPharmacyId())) {

				for (int i = 0; i < pharmacyIds.size(); i++) {
					Pharmacy p = _pharmacyRepository.getOne(pharmacyIds.get(i));

					if (i == 0) {
						pharmacyNames.append(p.getName());
					} else {
						pharmacyNames.append(", " + p.getName());
					}
				}
				
				PharmacyDermatologistDTO dermatologistDTO = new PharmacyDermatologistDTO(d.getUserId(), d.getFirstName(), d.getLastName(), d.getPhoneNumber(), d.getEmail(), d.getAddress().getStreet(), d.getAddress().getStreetNumber(), d.getAddress().getCity(), d.getRating(), pharmacyNames.toString());

				dermatologistDTOs.add(dermatologistDTO);
			}
		}

		return dermatologistDTOs;
	}
	
	@Override
	public Dermatologist createDermatologistByPharmacyAdmin(CreateDermatologistDTO createDermatologistDTO) {
		Pharmacy pharmacy = _pharmacyRepository.getOne(createDermatologistDTO.getPharmacyId());
		User findUser = _userRepository.findByEmail(createDermatologistDTO.getEmail()); 
		if (findUser != null) {
			return null;
		}
		Dermatologist newDermatologist = new Dermatologist();
		Address dermatologistAddress = new Address();
		dermatologistAddress = createDermatologistDTO.getAddress();
		newDermatologist.setAddress(dermatologistAddress);
		this._addressService.createAddress(dermatologistAddress);
		newDermatologist.setEnabled(true);
		newDermatologist.setFirstLogin(false);
		newDermatologist.setFirstName(createDermatologistDTO.getFirstName());
		newDermatologist.setLastName(createDermatologistDTO.getLastName());
		newDermatologist.setEmail(createDermatologistDTO.getEmail());
		newDermatologist.setUserType(UserType.DERMATOLOGIST);
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		newDermatologist.setSalt(encodedSalt);
		String rawPassword = generatePasswordWithSalt(createDermatologistDTO.getPassword(), encodedSalt); 
		String securePassword = hashPassword(rawPassword);
		newDermatologist.setPassword(securePassword);
		newDermatologist.setPhoneNumber(createDermatologistDTO.getPhoneNumber());
		List<Authority> authorities = _authorityService.findByName("ROLE_DERMATOLOGIST");
		newDermatologist.setAuthorities(authorities);
		newDermatologist.setRating(0.0);
		// set Pharmacy
		pharmacy.getDermatologist().add(newDermatologist);
		_pharmacyRepository.save(pharmacy);
		return _dermatologistRepository.save(newDermatologist);
	}
	
	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] genSalt = new byte[16];
		random.nextBytes(genSalt);
		return genSalt;
	}
	
	private String generatePasswordWithSalt(String userPassword, String salt) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userPassword);
		stringBuilder.append(salt);
		return stringBuilder.toString();
	}
	
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	@Override
	public Boolean deleteDermatologist(Long deleteDermatologistId) {
		PharmacyAdministrator pAdmin = (PharmacyAdministrator) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Pharmacy pharmacy = _pharmacyRepository.getOne(pAdmin.getPharmacy().getPharmacyId());
		Dermatologist dermatologist = _dermatologistRepository.getOne(deleteDermatologistId);
		List<Examination> allExaminations = _examinationRepository.findAll();
		List<DermatologistVacation> allDermatologistVacations = _dermatologistVacationRepository.findAll();
		Date currentDate = new Date();
		System.out.println("Date now " + currentDate.toString());
		for (Examination e : allExaminations) {
			if (e.getDermatologist().getUserId() == dermatologist.getUserId()
					&& e.getPharmacy().getPharmacyId() == pharmacy.getPharmacyId()
					&& e.getCancelled() == false && e.getAppointmentStatus() == AppointmentStatus.NONE) {
				return false;
			}
		}
		
		// Dermatologist on vacation
		for (DermatologistVacation dVacation : allDermatologistVacations) {
			if (dVacation.getDermatologist().getUserId() == deleteDermatologistId
					&& dVacation.getStatus() == Status.ACCEPTED
					&& dVacation.getStartDate().before(currentDate) 
					&& dVacation.getEndDate().after(currentDate)) {
				return false;
			}
		}
		
		for (DermatologistVacation dVacation : allDermatologistVacations) {
			if (dVacation.getDermatologist().getUserId() == deleteDermatologistId
					&& (dVacation.getStatus() == Status.WAITING || dVacation.getStatus() == Status.DECLINED)) {
				_dermatologistVacationRepository.delete(dVacation);
			}
		}
		
		// Brisanje dermatologa bas iz te apoteke u kojoj radi administrator
		for (Dermatologist d : pharmacy.getDermatologist()) {
			if (d.getUserId() == deleteDermatologistId) {
				pharmacy.getDermatologist().remove(d);
				_pharmacyRepository.save(pharmacy);
			}
		}
		
		_dermatologistRepository.deleteById(deleteDermatologistId);
		return true;
	}
	

}
