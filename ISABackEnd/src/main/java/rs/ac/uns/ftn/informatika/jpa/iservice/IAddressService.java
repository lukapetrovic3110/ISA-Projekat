package rs.ac.uns.ftn.informatika.jpa.iservice;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Address;

@Service
public interface IAddressService {

	public Address findById(Long id);
	
	public Address createAddress(Address address);

}
