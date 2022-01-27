package rs.ac.uns.ftn.informatika.jpa.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintForPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintPharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Consultation;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.Offer;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.Promotion;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.IComplaintEmployeeRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IComplaintPharmacyRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private IComplaintEmployeeRepository _complaintEmployeeRepository;
	
	@Autowired
	private IComplaintPharmacyRepository _complaintPharmacyRepository;
	
	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;

	/*
	 * Anotacija za oznacavanje asinhronog zadatka
	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
	 */
	
	@Async
	public void sendNotificaitionAsync(String emailAddress, String subject, String text) throws MailException, InterruptedException {
		
		System.out.println(env.getProperty("spring.mail.username")); // Email sa koga se salje
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailAddress);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		System.out.println("Email successfully sent!");
	}

	public void sendPromotionEmailNotificationAsync(Promotion promotion, Patient subscribedPatient) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(subscribedPatient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(promotion.getCaption());
		
		// konverzija datuma
		String startOfPromotion = new SimpleDateFormat("dd.MM.yyyy.").format(promotion.getStartOfPromotion());
	    String endOfPromotion = new SimpleDateFormat("dd.MM.yyyy.").format(promotion.getEndOfPromotion());
	        
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + subscribedPatient.getFirstName() + " "  + subscribedPatient.getLastName() + ",");
		text.append("\n\n");
		text.append(promotion.getContent());
		text.append("\nThe promotion will be active from " + startOfPromotion + " to " + endOfPromotion);
		text.append("\n\nYour pharmacy, " + promotion.getPharmacy().getName());
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Promotion email successfully sent to subscribed user/patient!");
	}
	
	public void sendRecievedMedicineEmail(String code, Pharmacy pharmacy, Patient patient) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Dispensed medicine");
		
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + patient.getFirstName() + ",");
		text.append("\n\n");
		text.append("Your reservation is successfully issued.");
		text.append("\n");
		text.append("Reservation code:" + code);
		text.append("\n\nYour pharmacy, " + pharmacy.getName());
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Medicine reveived mail sent!");
	}
	
	public void sendReservationMadeEmail(Reservation reservation) {
		String d = new SimpleDateFormat("dd.MM.yyyy.").format(reservation.getDeadline());
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(reservation.getPatient().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Medicine reservation");
		
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + reservation.getPatient().getFirstName() + ",");
		text.append("\n\n");
		text.append("Your have successfully made a reservation .");
		text.append("\n");
		text.append("Medicine: " + reservation.getMedicineItem().getMedicine().getName());
		text.append("Reservation code: " + reservation.getReservationCode());
		text.append("Reserved until: " + d.toString());
		
		text.append("\n\nYour pharmacy, " + reservation.getPharmacy().getName());
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Reservation mail sent!");
	}
	
	public void sendConsultationConfirmation(Consultation consultation) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(consultation.getPatient().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Consultation confirmation");
		
		String date = new SimpleDateFormat("dd.MM.yyyy. hh:mm:ss").format(consultation.getDateAndTime());
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + consultation.getPatient().getFirstName() + " "  + consultation.getPatient().getLastName() + ",");
		text.append("\n\n");
		text.append("\nYou have successfully scheduled a consultation.");
		text.append("\nConsultation information:");
		text.append("\nPHARMACY: " + consultation.getPharmacy().getName());
		text.append("\nDATE AND TIME: " + date);
		text.append("\nPHARMACIST: " + consultation.getPharmacist().getFirstName() + " " + consultation.getPharmacist().getLastName());
		text.append("\n\nYour pharmacy, " + consultation.getPharmacy().getName());
		mail.setText(text.toString());
		javaMailSender.send(mail);
		System.out.println("Confirmation email successfully sent to user/patient!");
	}

	public void sendExaminationConfirmation(Examination examination) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(examination.getPatient().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Examination confirmation");
		
		String date = new SimpleDateFormat("dd.MM.yyyy. hh:mm:ss").format(examination.getDateAndTime());
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + examination.getPatient().getFirstName() + " "  + examination.getPatient().getLastName() + ",");
		text.append("\n\n");
		text.append("\nYou have successfully scheduled a examination.");
		text.append("\nConsultation information:");
		text.append("\nPHARMACY: " + examination.getPharmacy().getName());
		text.append("\nDATE AND TIME: " + date);
		text.append("\nPHARMACIST: " + examination.getDermatologist().getFirstName() + " " + examination.getDermatologist().getLastName());
		text.append("\n\nYour pharmacy, " + examination.getPharmacy().getName());
		mail.setText(text.toString());
		javaMailSender.send(mail);
		System.out.println("Confirmation email successfully sent to user/patient!");
	}


	public void sendAcceptedVactionEmailForPharmacistAsync(PharmacistVacation pharmacistVacation) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pharmacistVacation.getPharmacist().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Accepted vacation");
		
		String startOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(pharmacistVacation.getStartDate());
	    String endOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(pharmacistVacation.getEndDate());
	        
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + pharmacistVacation.getPharmacist().getUserType() + ", " + pharmacistVacation.getPharmacist().getFirstName() + " "  + pharmacistVacation.getPharmacist().getLastName() + ",");
		text.append("\n\n");
		text.append("You are on vacation from  " + startOfVacation + " to " + endOfVacation + " .");
		text.append("\n\n\r\n"
				+ "All the best");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Accepted vacation email successfully sent!");
	}
	
	public void sendDeclinedVactionEmailForPharmacistAsync(PharmacistVacation pharmacistVacation, String explanation) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pharmacistVacation.getPharmacist().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Declined vacation");
		
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + pharmacistVacation.getPharmacist().getUserType() + ", " + pharmacistVacation.getPharmacist().getFirstName() + " "  + pharmacistVacation.getPharmacist().getLastName() + ",");
		text.append("\n\n");
		text.append("Unfortunately, we had to decline your vacation request.");
		text.append("\nReson, " + explanation + ".");
		text.append("\nOf course, you still have the right to take advantage of your days off, but on another occasion.");
		text.append("\n\n\r\n"
				+ "Greeting");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Declined vacation email successfully sent!");
	}
	
	public void sendAcceptedVactionEmailForDermatologistAsync(DermatologistVacation dermatologistVacation) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(dermatologistVacation.getDermatologist().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Accepted vacation");
		
		String startOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(dermatologistVacation.getStartDate());
	    String endOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(dermatologistVacation.getEndDate());
	        
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + dermatologistVacation.getDermatologist().getUserType() + ", " + dermatologistVacation.getDermatologist().getFirstName() + " "  + dermatologistVacation.getDermatologist().getLastName() + ",");
		text.append("\n\n");
		text.append("You are on vacation from  " + startOfVacation + " to " + endOfVacation + " .");
		text.append("\n\n\r\n"
				+ "All the best");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Accepted vacation email successfully sent!");
	}
	
	
	public void sendDeclinedVactionEmailForDermatologistAsync(DermatologistVacation dermatologistVacation, String explanation) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(dermatologistVacation.getDermatologist().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Declined vacation");
		
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + dermatologistVacation.getDermatologist().getUserType() + ", " + dermatologistVacation.getDermatologist().getFirstName() + " "  + dermatologistVacation.getDermatologist().getLastName() + ",");
		text.append("\n\n");
		text.append("Unfortunately, we had to decline your vacation request.");
		text.append("\nReson, " + explanation + ".");
		text.append("\nOf course, you still have the right to take advantage of your days off, but on another occasion.");
		text.append("\n\n\r\n"
				+ "Greeting");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Declined vacation email successfully sent!");
	}

	public void sendAcceptedOfferEmailAsync(Offer o) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(o.getSupplier().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Accepted offer");
		
		String deliveryDeadline = new SimpleDateFormat("dd.MM.yyyy.").format(o.getDeliveryDeadline());	        
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + o.getSupplier().getUserType() + ", " + o.getSupplier().getFirstName() + " "  + o.getSupplier().getLastName() + ",");
		text.append("\n\n");
		text.append("Your offer " + o.getOfferId() + " has been accepted.");
		text.append("\nWe will pay the amount of " + o.getPrice() + " RSD to your account.");
		text.append("\nDelivery deadline is " + deliveryDeadline + ", we hope you will respect that as you have so far.");
		
		text.append("\n\n\r\n"
				+ "All the best");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Accepted offer email successfully sent!");
		
	}

	public void sendDeclinedOfferEmailAsync(Offer o) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(o.getSupplier().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Declined offer");
		      
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + o.getSupplier().getUserType() + ", " + o.getSupplier().getFirstName() + " "  + o.getSupplier().getLastName() + ",");
		text.append("\n\n");
		text.append("We are sorry to have to let you know but your offer " + o.getOfferId() + " has been declined.");
		text.append("\nGood luck next time.");
		text.append("\n\n\r\n"
				+ "Greeting");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Declined offer email successfully sent!");
	}
	
	public void sendAnswerOnComplaintEmailAsync(AnswerOnComplaintDTO answerEmployee) {
			
		ComplaintEmployee complaintEmployee = _complaintEmployeeRepository.findById(answerEmployee.getComplaintEmployeeId()).orElse(null);
		
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(complaintEmployee.getPatient().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Answer for your complaint");
		      
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " + complaintEmployee.getPatient().getFirstName() + " "  + complaintEmployee.getPatient().getLastName() + ",");
		text.append("\n\n");
		text.append(answerEmployee.getTextAnswer());
		text.append("\n");
		text.append("\n\n\r\n"
				+ "Greeting");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Answer on complaint email successfully sent!");
	}
	
	

	
	public void sendAnswerOnComplaintPharmacyEmailAsync(AnswerOnComplaintForPharmacyDTO answerOnComplaintForPharmacyDTO) {
			
		ComplaintPharmacy complaintPharmacy = _complaintPharmacyRepository.findById(answerOnComplaintForPharmacyDTO.getComplaintPharmacyId()).orElse(null);
		
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(complaintPharmacy.getPatient().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Answer for your complaint");
		      
		StringBuilder text = new StringBuilder();
		
		text.append("Dear " +  complaintPharmacy.getPatient().getFirstName() + " "  + complaintPharmacy.getPatient().getLastName() + ",");
		text.append("\n\n");
		text.append(answerOnComplaintForPharmacyDTO.getTextAnswer());
		text.append("\n");
		text.append("\n\n\r\n"
				+ "Greeting");
		
		mail.setText(text.toString());
		
		javaMailSender.send(mail);
		System.out.println("Answer on complaint email successfully sent!");
	}
	
}