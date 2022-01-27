package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient extends User {

	private static final long serialVersionUID = 6497460964207145259L;

	@Column(name = "penalty")
	private int penalty;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Allergy allergy;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Penalty> penalties = new HashSet<Penalty>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	public Patient() {
		super();
	}

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

	public Set<Penalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(Set<Penalty> penalties) {
		this.penalties = penalties;
	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

}