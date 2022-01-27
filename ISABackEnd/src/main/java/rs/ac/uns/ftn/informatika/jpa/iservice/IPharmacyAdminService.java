package rs.ac.uns.ftn.informatika.jpa.iservice;


import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAdminRegistrationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;

public interface IPharmacyAdminService {
	PharmacyAdminDTO getPharmacyAdminById(Long id);
	PharmacyAdministrator savePharmacyAdmin(PharmacyAdminRegistrationDTO request);

}
