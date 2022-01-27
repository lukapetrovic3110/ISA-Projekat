package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   
	private PharmacyAdministrator pharmacyAdmin;
	
	public Notification() {}

	public Notification(Long notificationId, String content, PharmacyAdministrator pharmacyAdmin) {
		super();
		this.notificationId = notificationId;
		this.content = content;
		this.pharmacyAdmin = pharmacyAdmin;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PharmacyAdministrator getPharmacyAdmin() {
		return pharmacyAdmin;
	}

	public void setPharmacyAdmin(PharmacyAdministrator pharmacyAdmin) {
		this.pharmacyAdmin = pharmacyAdmin;
	}
	
}
