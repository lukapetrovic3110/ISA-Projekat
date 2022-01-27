package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class SaleAndPromotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleId;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    public Pharmacy pharmacy;
   
    public SaleAndPromotion(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}
    
    public SaleAndPromotion()
    {
    	
    }

    public Pharmacy getPharmacy() {
	    return pharmacy;
    }
	
    public void setPharmacy(Pharmacy pharmacy) {
	    this.pharmacy = pharmacy;
    }

}