package rs.ac.uns.ftn.informatika.jpa.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	protected final Log LOGGER = (Log) LogFactory.getLog(getClass());

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
		} else {
			return user;
		}
	}

	// Funkcija pomocu koje korisnik menja svoju lozinku
	public void changePassword(String oldPassword, String newPassword) {
 
		// Ocitavamo trenutno ulogovanog korisnika
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);

		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		user.setSalt(encodedSalt);
		String newPlainPassword = generatePasswordWithSalt(newPassword, encodedSalt);
		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		String newSecurePassword = hashPassword(newPlainPassword);
		user.setPassword(newSecurePassword);
		
		userRepository.save(user);
	}
	
	public void changeFirstPassword(String oldPass, String newPass) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) currentUser.getPrincipal();
         
        if(!verifyHash(oldPass, user.getPassword()))
        	throw new IllegalArgumentException("Current password is not valid!");
        
        byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		user.setSalt(encodedSalt);
		String newPlainPassword = generatePasswordWithSalt(newPass, encodedSalt);
		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// radimo hesh(newPass + genSalt)
		String newSecurePassword = hashPassword(newPlainPassword);
		user.setPassword(newSecurePassword);
        user.setFirstLogin(true);
        userRepository.save(user);
	}
	
	//razlika je sto ovdje ne setujemo polje u tabeli za prvo logovanje
	public void changeUserPassword(String oldPass, String newPass) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) currentUser.getPrincipal();
         
        if(!verifyHash(oldPass, user.getPassword()))
        	throw new IllegalArgumentException("Current password is not valid!");
        
        byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		user.setSalt(encodedSalt);
		String newPlainPassword = generatePasswordWithSalt(newPass, encodedSalt);
		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// radimo hesh(newPass + genSalt)
		String newSecurePassword = hashPassword(newPlainPassword);
		user.setPassword(newSecurePassword);
        userRepository.save(user);
	}

	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	private String generatePasswordWithSalt(String inputPassword, String salt) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(inputPassword);
		stringBuilder.append(salt);
		return stringBuilder.toString();
	}
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}

