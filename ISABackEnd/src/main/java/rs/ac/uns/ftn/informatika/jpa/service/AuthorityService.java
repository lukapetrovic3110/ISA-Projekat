package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IAuthorityService;
import rs.ac.uns.ftn.informatika.jpa.model.Authority;
import rs.ac.uns.ftn.informatika.jpa.repository.IAuthorityRepository;

@Service
public class AuthorityService implements IAuthorityService {

	@Autowired
	private IAuthorityRepository auhtorityRepository;
	
	@Override
	public List<Authority> findById(Long id) {
		Authority auth = this.auhtorityRepository.getOne(id);
		List<Authority> auths = new ArrayList<Authority>();
		auths.add(auth);
		return auths;
	}

	@Override
	public List<Authority> findByName(String name) {

		Authority auth = this.auhtorityRepository.findByName(name);
		List<Authority> auths = new ArrayList<Authority>();
		auths.add(auth);
		return auths;
	}

	
}
