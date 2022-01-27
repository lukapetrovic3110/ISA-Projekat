package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.jpa.model.ConfirmationToken;

public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{

}
