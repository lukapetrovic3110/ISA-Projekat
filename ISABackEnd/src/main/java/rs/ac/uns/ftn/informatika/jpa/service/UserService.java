package rs.ac.uns.ftn.informatika.jpa.service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PatientAppointmentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RegistrationRequest;
import rs.ac.uns.ftn.informatika.jpa.dto.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IUserService;
import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.Allergy;
import rs.ac.uns.ftn.informatika.jpa.model.Authority;
import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCard;
import rs.ac.uns.ftn.informatika.jpa.model.LoyaltyCategory;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;
import rs.ac.uns.ftn.informatika.jpa.repository.ILoyaltyCardRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;
import rs.ac.uns.ftn.informatika.jpa.security.auth.JwtAuthenticationRequest;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository _userRepository;

	@Autowired
	private AuthorityService _authorityService;

	@Autowired
	private AddressService _addressService;
	
	@Autowired
	private ConsultationService _consultationService;
	
	@Autowired
	private ExaminationService _examinationService;

	@Autowired
	private EmailService _emailService;
	
	@Autowired
	private ILoyaltyCardRepository _loyaltyCardRepository;

	@Autowired
	private ConfirmationTokenService _confirmationTokenService;

	@Override
	public User findById(Long id) {

		return _userRepository.findById(id).orElse(null);
	}

	@Override
	public User save(RegistrationRequest request) {

		Address a = new Address();
		Patient p = new Patient();
		Allergy allergy = new Allergy();
		LoyaltyCard loyaltyCard = new LoyaltyCard();
		
		
		p.setEmail(request.getEmail());
		
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		p.setSalt(encodedSalt);
		String rawPassword = generatePasswordWithSalt(request.getPassword(), encodedSalt); 
		String securePassword = hashPassword(rawPassword);
		p.setPassword(securePassword);
		
		p.setFirstName(request.getFirstName());
		p.setLastName(request.getLastName());

		p.setPhoneNumber(request.getPhoneNumber());
		a = request.getAddress();
		p.setAddress(a);
		this._addressService.createAddress(request.getAddress());
		
		p.setUserType(UserType.PATIENT);

		p.setEnabled(false);
		p.setFirstLogin(false);
		p.setAllergy(allergy);
		p.setLastResetPasswordDate(new Date());
		
		List<Authority> auth = _authorityService.findByName("ROLE_PATIENT");
		p.setAuthorities(auth);

		loyaltyCard.setPatient(p);
		loyaltyCard.setLoyaltyCategory(LoyaltyCategory.REGULAR);
		this._loyaltyCardRepository.save(loyaltyCard);
		
		this._userRepository.save(p);
		ConfirmationToken confirmationToken = _confirmationTokenService.saveConfirmationToken(p);
		sendConfirmationEmail(p, confirmationToken);
	
		return p;
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
	
	public User updatePassword(User user) {
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		user.setSalt(encodedSalt);
		String password = generatePasswordWithSalt(user.getPassword(), encodedSalt);
		String securePassword = hashPassword(password);
		user.setPassword(securePassword);
		return _userRepository.save(user);
	}
	
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
	
	@Override
	public void update(@Valid User user) {
		User existing = _userRepository.findById(user.getUserId()).orElse(null);

		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setAddress(user.getAddress());
		existing.setPhoneNumber(user.getPhoneNumber());
		existing.setEnabled(user.getEnabled());

		_userRepository.save(existing);

	}

	@Override
	public User findByEmail(String email) {
		return _userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {

		List<User> users = _userRepository.findAll();
		User user = new User();

		if (users == null) {
			return null;
		}

		for (User u : users) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}
		return user;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = _userRepository.findAll();
		List<User> usersList = new ArrayList<>();
		for (User u : users) {
			if (u.getUserType() == UserType.PATIENT)
				usersList.add(u);
		}
		List<UserDTO> dtos = new ArrayList<>();
		usersList.stream().forEach(user -> dtos.add(new UserDTO(user.getFirstName(), user.getLastName())));
		return dtos;
	}
	
	@Override
	public List<User> getAllPatients() {
		List<User> users = _userRepository.findAll();
		List<User> usersList = new ArrayList<>();
		for (User u : users) {
			if (u.getUserType() == UserType.PATIENT)
				usersList.add(u);
		}
		
		return usersList;
	}

	@Override
	public List<PatientAppointmentDTO> getPatientsByName(String name, Long employeeId){
		if(name.trim().equals("")) {
			return findPatientsByAppointment(employeeId);
		}
		List<PatientAppointmentDTO> patients = findPatientsByAppointment(employeeId);
		List<PatientAppointmentDTO> result = new ArrayList<PatientAppointmentDTO>();
		
		for(PatientAppointmentDTO pa: patients) {
			String fullName = pa.getFirstName() + " " + pa.getLastName();
			if(fullName.toLowerCase().contains(name.toLowerCase().trim())) {
				result.add(pa);
			}
		}
		return result;
	}	
	
	@Override
	public List<PatientAppointmentDTO> findPatientsByAppointment(Long employeeId){
		
		List<PatientAppointmentDTO> result = new ArrayList<PatientAppointmentDTO>();
		//User employee = findById(employeeId);
	
		for(Examination e : _examinationService.findAllExamination()) {
			if(employeeId.equals(e.getDermatologist().getUserId())) {
				if(e.getPatient() != null) {
					result.add(new PatientAppointmentDTO(e.getPatient().getUserId(), e.getPatient().getFirstName(), 
							e.getPatient().getLastName(),e.getDateAndTime().toString()));
				}
			}
		}
		
		for(Consultation c : _consultationService.findAllConsultation()) {
			if(employeeId.equals(c.getPharmacist().getUserId())) {
				if(c.getPatient() != null) {
					result.add(new PatientAppointmentDTO(c.getPatient().getUserId(), c.getPatient().getFirstName(),
							c.getPatient().getLastName(), c.getDateAndTime().toString()));
				}
			}
		}
		
		return result;
	}
	
	@Override
	public ArrayList<UserDTO> userSearch(UserDTO userDTO) {
		ArrayList<UserDTO> users = new ArrayList<>();
		for (UserDTO user : getAllUsers()) {
			if (user.getFirstName().equals(userDTO.getFirstName()) && user.getLastName().equals(userDTO.getLastName()))
				users.add(user);
		}
		return users;
	}

	@Override
	public List<UserDTO> findUserByUserType(UserType userType) {
		return _userRepository.findUserByUserType(userType).stream()
				.map(u -> new UserDTO(u.getFirstName(), u.getLastName())).collect(Collectors.toList());
	}

	@Override
	public void sendConfirmationEmail(User user, ConfirmationToken confirmationToken) {
		System.out.println("User's email: " + user.getEmail());
		try {

			String recipientEmail = user.getEmail();
			String subject = "Confirm registration";
			String message = "Please confirm your registration by clicking the link below \n\n\n"
					+ "http://localhost:8080/confirmRegistration/" + confirmationToken.getConfirmationToken();
			_emailService.sendNotificaitionAsync(recipientEmail, subject, message);

			System.out.println("Email successfully sent!");

		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
		}

	}
	
	public List<User> findAll() {
		return _userRepository.findAll();
	}
	
	@Override
	public User login(JwtAuthenticationRequest authenticationRequest) {
		List<User> users = findAll();
		for (User user : users) {
			if (user.getEmail().equals(authenticationRequest.getEmail())
					&& verifyHash(generatePasswordWithSalt(authenticationRequest.getPassword(), user.getSalt()), user.getPassword()))
				return user;

		}

		return null;
	}
	
	@Override
	public void increasePenalty(Long id, Penalty p) {
		
		User user = findById(id);
		
		int penalty = ((Patient) user).getPenalty() + 1;
		
		((Patient) user).setPenalty(penalty);
		((Patient) user).getPenalties().add(p);
		update(user);
	}
	
	@Override
	public boolean checkPenalties(Long id) {
		User existing = _userRepository.findById(id).orElse(null);
		if(existing != null) {
			int penalty = ((Patient) existing).getPenalty();
			if(penalty >= 3) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void deletePenalties(Long id) {
		User existing = _userRepository.findById(id).orElse(null);
		if(existing != null) {
			((Patient) existing).setPenalty(0);
			for (Penalty p : ((Patient) existing).getPenalties()) {
				p.setDeleted(true);
			}
			((Patient) existing).setPenalties(new HashSet<Penalty>());
			_userRepository.save(existing);
		}
	}

}
