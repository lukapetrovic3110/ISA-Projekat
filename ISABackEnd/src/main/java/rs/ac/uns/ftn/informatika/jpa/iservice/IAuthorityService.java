package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Authority;

@Service
public interface IAuthorityService {

	List<Authority> findById(Long id);
	List<Authority> findByName(String name);
}
