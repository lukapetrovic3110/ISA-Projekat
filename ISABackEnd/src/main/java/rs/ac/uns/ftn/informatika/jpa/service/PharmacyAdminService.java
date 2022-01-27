package rs.ac.uns.ftn.informatika.jpa.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IAddressService;
import rs.ac.uns.ftn.informatika.jpa.iservice.IAuthorityService;
import rs.ac.uns.ftn.informatika.jpa.iservice.IPharmacyAdminService;
import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.model.Authority;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;
import rs.ac.uns.ftn.informatika.jpa.repository.IAddressRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyAdminRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;

@Service
public class PharmacyAdminService implements IPharmacyAdminService{

	private IPharmacyAdminRepository _pharmacyAdminRepository;
	
	private IAddressRepository _addressRepository;
	
	private IPharmacyRepository _pharmacyRepository;
	
	private IAddressService _addressService;
	
	private IAuthorityService _authorityService;
	
	@Autowired
	public PharmacyAdminService(IPharmacyAdminRepository iPharmacyAdminRepository, IAddressRepository iAddressRepository, IPharmacyRepository iPharmacyRepository, IAuthorityService iauthorityService, IAddressService iaddressService) {
		this._pharmacyAdminRepository = iPharmacyAdminRepository;
		this._addressRepository = iAddressRepository;
		this._pharmacyRepository = iPharmacyRepository;
		this._addressService = iaddressService;
		this._authorityService = iauthorityService;
	}	

	@Override
	public PharmacyAdminDTO getPharmacyAdminById(Long id) {
		
		PharmacyAdministrator pAdmin = _pharmacyAdminRepository.findByUserId(id);
		
		Address pAdminAddress = _addressRepository.getOne(pAdmin.getAddress().getAddressId());
		
		Pharmacy pAdminPharmacy = _pharmacyRepository.getOne(pAdmin.getPharmacy().getPharmacyId());
		
		PharmacyAdminDTO phAdminDTO = new PharmacyAdminDTO(pAdmin.getEmail(), pAdmin.getFirstName(), pAdmin.getLastName(), pAdmin.getPhoneNumber(), pAdminAddress.getStreet(), pAdminAddress.getStreetNumber(), pAdminAddress.getCity(), pAdminAddress.getCountry(), pAdminPharmacy.getName());
		
		return phAdminDTO;
	}

	@Override
	public PharmacyAdministrator savePharmacyAdmin(PharmacyAdminRegistrationDTO request) {
		Address a = new Address();
		
		PharmacyAdministrator ph_admin = new PharmacyAdministrator();
		
		ph_admin.setEmail(request.getEmail());

		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		ph_admin.setSalt(encodedSalt);
		String rawPassword = generatePasswordWithSalt(request.getPassword(), encodedSalt); 
		String securePassword = hashPassword(rawPassword);
		ph_admin.setPassword(securePassword);
		
		ph_admin.setFirstName(request.getFirstName());
		ph_admin.setLastName(request.getLastName());

		ph_admin.setPhoneNumber(request.getPhoneNumber());

		a = request.getAddress();
		
		
		ph_admin.setAddress(a);
		this._addressService.createAddress(request.getAddress());
		ph_admin.setUserType(UserType.PH_ADMINISTRATOR);
		
		ph_admin.setEnabled(true);
		ph_admin.setFirstLogin(false);
		
		Pharmacy pharmacy = _pharmacyRepository.findById(request.getPharmacyId()).orElse(null);
		ph_admin.setPharmacy(pharmacy);
		ph_admin.setLastResetPasswordDate(new Date());
		
		List<Authority> auth = _authorityService.findByName("ROLE_PH_ADMIN");
		ph_admin.setAuthorities(auth);
		
		this._pharmacyAdminRepository.save(ph_admin);
		return ph_admin;	

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
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
	
}
