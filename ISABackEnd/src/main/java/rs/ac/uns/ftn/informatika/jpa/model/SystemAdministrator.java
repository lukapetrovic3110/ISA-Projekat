package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class SystemAdministrator extends User {
	
	private static final long serialVersionUID = 810387715401362080L;
	
	public SystemAdministrator(String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, Long userId, UserType userType, Address address, Boolean enabled, Date lastResetPasswordDate, Boolean firstLogin) {
		super(firstName, lastName, password, email, phoneNumber, enabled, userId, userType, address, lastResetPasswordDate, firstLogin);
		// TODO Auto-generated constructor stub
	}
	public SystemAdministrator()
	{
		
	}
	
}