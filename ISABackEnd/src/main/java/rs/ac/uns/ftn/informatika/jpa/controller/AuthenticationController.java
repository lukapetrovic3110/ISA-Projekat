package rs.ac.uns.ftn.informatika.jpa.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rs.ac.uns.ftn.informatika.jpa.dto.RegistrationRequest;
import rs.ac.uns.ftn.informatika.jpa.exception.ResourceConflictException;
import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserTokenState;
import rs.ac.uns.ftn.informatika.jpa.security.TokenUtils;
import rs.ac.uns.ftn.informatika.jpa.security.auth.JwtAuthenticationRequest;
import rs.ac.uns.ftn.informatika.jpa.service.ConfirmationTokenService;
import rs.ac.uns.ftn.informatika.jpa.service.CustomUserDetailsService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		try {
			User logInUser = userService.login(authenticationRequest);
			StringBuilder passwordWithSalt = new StringBuilder();
			passwordWithSalt.append(authenticationRequest.getPassword());
			passwordWithSalt.append(logInUser.getSalt());
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), passwordWithSalt.toString()));

			// Ubaci korisnika u trenutni security kontekst
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = (User) authentication.getPrincipal();

			System.out.println(user.getEmail());
			
			if (user.getEnabled()) {
				// Kreiraj token za tog korisnika
				String jwt = tokenUtils.generateToken(authenticationRequest.getEmail());
				int expiresIn = tokenUtils.getExpiredIn();
				// Vrati token kao odgovor na uspesnu autentifikaciju
				return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user));
			}
		} catch (Exception e) {			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody RegistrationRequest userRequest, UriComponentsBuilder ucBuilder) {
		try {
			User existUser = this.userService.findByEmail(userRequest.getEmail());

			if (existUser != null)
				throw new ResourceConflictException(userRequest.getId(), "Username already exists");

			return new ResponseEntity<>(this.userService.save(userRequest), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() + " ovde je propalo", HttpStatus.BAD_REQUEST);
		}
	}

	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token
	// osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastResetPasswordDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, user));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@PutMapping(value = "/confirm_account/{token}", consumes = "application/json")
	public ResponseEntity<Boolean> confirmAccount(@PathVariable String token) {

		try {

			ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);

			System.out.println(confirmationToken.toString());
			if (confirmationToken != null
					&& LocalDateTime.now().isBefore(confirmationToken.getCreatedDate().plusDays(5))) {
				User user = userService.findByEmail(confirmationToken.getUsers().getEmail());
				user.setEnabled(true);
				userService.update(user);
				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		User passwordChangerUser = userService.findByEmail(passwordChanger.email);

		StringBuilder oldPasswordWithSalt = new StringBuilder();
		oldPasswordWithSalt.append(passwordChanger.oldPassword);
		oldPasswordWithSalt.append(passwordChangerUser.getSalt());

		userDetailsService.changePassword(oldPasswordWithSalt.toString(), passwordChanger.newPassword);

		HashMap<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
		public String email;
		public String rewritePassword;
	}

	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST', 'PH_ADMINISTRATOR', 'SYS_ADMINISTRATOR', 'SUPPLIER')")
	@RequestMapping(value = "/firstLogin", method = RequestMethod.POST)
	public ResponseEntity<?> firstLogin(@RequestBody PasswordChanger passwordChanger) {
		User user = userService.findByEmail(passwordChanger.email);

		if (user.getEmail().equals(null)) {
			throw new IllegalArgumentException("Invalid email or password");
		}

		if (passwordChanger.newPassword.equals(passwordChanger.oldPassword)) {
			throw new IllegalArgumentException("Password can not be same as old.");

	    }
		if(!passwordChanger.newPassword.equals(passwordChanger.rewritePassword)) {
            throw new IllegalArgumentException("Password must match!");
        }
        if(passwordChanger.newPassword.isEmpty() || passwordChanger.rewritePassword.isEmpty()|| passwordChanger.oldPassword.isEmpty()) {
            throw new IllegalArgumentException("Fill all the required fields!");
        }
        
		StringBuilder oldPasswordWithSalt = new StringBuilder();
		oldPasswordWithSalt.append(passwordChanger.oldPassword);
		oldPasswordWithSalt.append(user.getSalt());
       
		userDetailsService.changeFirstPassword(oldPasswordWithSalt.toString(), passwordChanger.newPassword);
		
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
	
	
	//**************OVA JE ISTA SAMO ZA OBICNO MIJENJANJE LOZINKE*****************
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST', 'PH_ADMINISTRATOR', 'SYS_ADMINISTRATOR', 'SUPPLIER')")
	@RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
	    public ResponseEntity<?> changeUserPassword(@RequestBody PasswordChanger passwordChanger) {
			User user = userService.findByEmail(passwordChanger.email);
		
			if (user.getEmail().equals(null)) {
				throw new IllegalArgumentException("Invalid email or password");
			}
			//System.out.println(passwordChanger.email); 
			
			if(passwordChanger.newPassword.equals(passwordChanger.oldPassword)) {
				throw new IllegalArgumentException("Password can not be same as old.");
		    }
			if(!passwordChanger.newPassword.equals(passwordChanger.rewritePassword)) {
	            throw new IllegalArgumentException("Password must match!");
	        }
	        if(passwordChanger.newPassword.isEmpty() || passwordChanger.rewritePassword.isEmpty()|| passwordChanger.oldPassword.isEmpty()) {
	            throw new IllegalArgumentException("Fill all the required fields!");
	        }
			if (!passwordChanger.newPassword.equals(passwordChanger.rewritePassword)) {
				throw new IllegalArgumentException("Password must match!");
			}
	
			StringBuilder oldPasswordWithSalt = new StringBuilder();
			oldPasswordWithSalt.append(passwordChanger.oldPassword);
			oldPasswordWithSalt.append(user.getSalt());
	
			// Ovo je razlika, pozivamo metodu za obicno mijenjanje lozinke
			userDetailsService.changeUserPassword(oldPasswordWithSalt.toString(), passwordChanger.newPassword);
	
			Map<String, String> result = new HashMap<>();
			result.put("result", "success");
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
}
