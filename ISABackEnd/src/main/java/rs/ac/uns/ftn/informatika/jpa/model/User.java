package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "salt", unique = true, nullable = false)
	private String salt;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;

	@Column(name = "lastResetPassDate")
	protected Date lastResetPasswordDate;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address address;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	/*
	 * @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name =
	 * "user_id", referencedColumnName = "id"), inverseJoinColumns
	 * = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	 */
	private List<Authority> authorities;

	@Column(name = "firstLogin", nullable = false)
	private Boolean firstLogin;

	public User() {
	}

	public User(String firstName, String lastName, String password, String email, String phoneNumber,
			Boolean enabled, Long userId, UserType userType, Address address, Date lastPassResetDate,
			Boolean firstLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.userType = userType;
		this.address = address;
		this.enabled = enabled;
		this.lastResetPasswordDate = lastPassResetDate;
		this.firstLogin = firstLogin;
	}

	public User(String firstName, String lastName, String password, String email, String phoneNumber,
			UserType userType, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
		this.address = address;
	}

	public User(String firstName, String lastName, String password, String salt, String email,
			String phoneNumber, UserType userType, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastResetPasswordDate() {
		return lastResetPasswordDate;
	}

	public void setLastResetPasswordDate(Date lastResetPasswordDate) {
		this.lastResetPasswordDate = lastResetPasswordDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}