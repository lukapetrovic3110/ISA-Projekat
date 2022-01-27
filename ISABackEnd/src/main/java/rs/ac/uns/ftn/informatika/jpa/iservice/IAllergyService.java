package rs.ac.uns.ftn.informatika.jpa.iservice;
import java.util.Set;

import rs.ac.uns.ftn.informatika.jpa.model.Medicine;

public interface IAllergyService {
	public Set<Medicine> addMedicineToAllergy(Medicine medicine,Long id);
}
