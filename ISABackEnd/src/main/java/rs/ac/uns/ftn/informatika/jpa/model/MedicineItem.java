package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


@Entity
public class MedicineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineItemId;
	
	@Version
	private Long version;
	
	@Column(name = "quantity", nullable = false)
    private int quantity;
   
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    public Medicine medicine;
  
    public MedicineItem(int quantity, Medicine medicine) {
		super();
		this.quantity = quantity;
		this.medicine = medicine;
    }
    public MedicineItem()
    {
    	
    }
    
	public Long getMedicineItemId() {
		return medicineItemId;
	}
	public void setMedicineItemId(Long medicineItemId) {
		this.medicineItemId = medicineItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}