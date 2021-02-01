package rs.ac.uns.isaprojekat.model;

import java.util.*;

	public class Patient extends User {
		
	public Patient(String firstName, String lastName, String userName, String password, String email,
				String phoneNumber, Long userId, UserType userType, Address address) {
			super(firstName, lastName, userName, password, email, phoneNumber, userId, userType, address);
			// TODO Auto-generated constructor stub
		}
	private int penalty;
	   public Allergy allergy;
	   
	
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	public Allergy getAllergy() {
		return allergy;
	}
	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}


}