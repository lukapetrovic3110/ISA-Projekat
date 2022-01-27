package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.iservice.IConfirmationTokenService;
import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.repository.IConfirmationTokenRepository;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {
	
	private final IConfirmationTokenRepository _iConfirmationTokenRepository;
	
	@Autowired
	public ConfirmationTokenService(IConfirmationTokenRepository iConfirmationTokenRepository) {
		this._iConfirmationTokenRepository = iConfirmationTokenRepository;
	}

	@Override
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		for (ConfirmationToken confirmationTokenIt : findAll()) {

			if (confirmationToken.equals(confirmationTokenIt.getConfirmationToken())) {

				return confirmationTokenIt;
			}
		}

		return null;
	}

	@Override
	public ConfirmationToken saveConfirmationToken(User user) {
		return _iConfirmationTokenRepository.save(new ConfirmationToken(user));
	}
	
	public List<ConfirmationToken> findAll() {
		return _iConfirmationTokenRepository.findAll();
	}

}
