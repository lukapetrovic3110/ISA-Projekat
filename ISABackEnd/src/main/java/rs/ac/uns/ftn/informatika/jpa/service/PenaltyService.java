package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IPenaltyService;
import rs.ac.uns.ftn.informatika.jpa.model.Penalty;
import rs.ac.uns.ftn.informatika.jpa.repository.IPenaltyRepository;

@Service
public class PenaltyService implements IPenaltyService{

	@Autowired
	private IPenaltyRepository _penaltyRepository;

	@Override
	public Penalty save(Penalty penalty) {
		return _penaltyRepository.save(penalty);
	}
}
