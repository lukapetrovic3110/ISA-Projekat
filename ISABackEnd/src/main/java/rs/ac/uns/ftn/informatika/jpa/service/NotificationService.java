package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.NotificationDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.INotificationService;
import rs.ac.uns.ftn.informatika.jpa.model.Notification;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacyAdministrator;

import rs.ac.uns.ftn.informatika.jpa.repository.INotificationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IUserRepository;

@Service
public class NotificationService implements INotificationService{
	
	@Autowired
	INotificationRepository _notificationRepository;
	
	@Autowired
	IUserRepository _userRepository;
	
	@Autowired 
	IPharmacyRepository _pharmacyRepository;
	
	public Notification saveNotification(NotificationDTO notificationDTO) {
	
		Notification notification = new Notification();
		
		Pharmacy pharmacy = _pharmacyRepository.findById((notificationDTO.getPharmacyId())).orElse(null);
		
		for(PharmacyAdministrator pharmacyAdmin: pharmacy.getPhAdministrators()) {
			notification.setPharmacyAdmin(pharmacyAdmin);
			break;
		}
		
		notification.setContent(notificationDTO.getContent());
		
		this._notificationRepository.save(notification);
		
		return notification;
	}
}
