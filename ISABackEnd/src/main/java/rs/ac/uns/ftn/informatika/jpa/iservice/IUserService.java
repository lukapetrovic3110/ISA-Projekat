package rs.ac.uns.ftn.informatika.jpa.iservice;


import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.RegistrationRequest;
import rs.ac.uns.ftn.informatika.jpa.dto.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;
import rs.ac.uns.ftn.informatika.jpa.security.auth.JwtAuthenticationRequest;

@Service
public interface IUserService {

	User findById(Long id);

	void update(User user);
	
	User login(JwtAuthenticationRequest authenticationRequest);

	User save(RegistrationRequest request);
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);

	List<UserDTO> getAllUsers();
	
	List<UserDTO> userSearch(UserDTO userDTO);
	
	List<UserDTO> findUserByUserType(UserType userType);
	
	void sendConfirmationEmail(User user, ConfirmationToken confirmationToken);
}
