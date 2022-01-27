package rs.ac.uns.ftn.informatika.jpa.iservice;

import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;
import rs.ac.uns.ftn.informatika.jpa.model.User;

public interface IConfirmationTokenService {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken saveConfirmationToken(User user);
}
