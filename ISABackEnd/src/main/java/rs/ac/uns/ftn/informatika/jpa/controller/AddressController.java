package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.model.Address;
import rs.ac.uns.ftn.informatika.jpa.service.AddressService;


@RestController
@RequestMapping(value = "address")
public class AddressController {
	

	@Autowired
	private AddressService _addressService ;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> getAddressId(@PathVariable Long id) {
		Address a = (Address) _addressService.findById(id);
		return new ResponseEntity<Address>(a,HttpStatus.OK);
	}

}
