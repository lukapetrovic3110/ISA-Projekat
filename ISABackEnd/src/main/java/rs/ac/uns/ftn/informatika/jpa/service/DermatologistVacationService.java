package rs.ac.uns.ftn.informatika.jpa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.DermatologistVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.RequestDeclineDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ResponseVacationDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IDermatologistVacationService;
import rs.ac.uns.ftn.informatika.jpa.model.Dermatologist;
import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.PharmacistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Status;
import rs.ac.uns.ftn.informatika.jpa.repository.IDermatologistVacationRepository;

@Service
public class DermatologistVacationService implements IDermatologistVacationService {
	
	private IDermatologistVacationRepository _dermatologistVacationRepository;
	private EmailService _emailService;
	
	@Autowired
	public DermatologistVacationService(IDermatologistVacationRepository dermatologistVacationRepository, EmailService emailService) {
		this._dermatologistVacationRepository = dermatologistVacationRepository;
		this._emailService = emailService;
	}

	@Override
	public List<DermatologistVacationDTO> findAllDermatologistVacation() {
		List<DermatologistVacation> allVacation = _dermatologistVacationRepository.findAll();
		List<DermatologistVacationDTO> list = new ArrayList<DermatologistVacationDTO>();
		
		for (DermatologistVacation dVacation : allVacation) {
			String startDate = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getStartDate());
			String endDate = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getEndDate());
			DermatologistVacationDTO dVacationDTO = new DermatologistVacationDTO(dVacation.getVacationId(), dVacation.getDermatologist().getUserId(), dVacation.getDermatologist().getFirstName(), 
					dVacation.getDermatologist().getLastName(), dVacation.getDermatologist().getEmail(), startDate, endDate, dVacation.getStatus());
			list.add(dVacationDTO);
		}
		return list;
	}

	@Override
	public List<DermatologistVacationDTO> findAllDermatologistVacationWithStatusWaiting() {
		List<DermatologistVacation> allVacation = _dermatologistVacationRepository.findAll();
		List<DermatologistVacationDTO> list = new ArrayList<DermatologistVacationDTO>();
		
		for (DermatologistVacation dVacation : allVacation) {
			if(dVacation.getStatus() == Status.WAITING) {
				String startDate = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getStartDate());
				String endDate = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getEndDate());
				DermatologistVacationDTO dVacationDTO = new DermatologistVacationDTO(dVacation.getVacationId(), dVacation.getDermatologist().getUserId(), dVacation.getDermatologist().getFirstName(), 
						dVacation.getDermatologist().getLastName(), dVacation.getDermatologist().getEmail(), startDate, endDate, dVacation.getStatus());
				list.add(dVacationDTO);
			}
		}
		return list;
	}

	@Override
	public DermatologistVacation accept(Long vacationId) {
		DermatologistVacation dermatologistVacation = _dermatologistVacationRepository.getOne(vacationId);
		dermatologistVacation.setStatus(Status.ACCEPTED);
		if(sendAcceptedVacationEmail(dermatologistVacation))
			return _dermatologistVacationRepository.save(dermatologistVacation);
		
		return null;
	}

	private boolean sendAcceptedVacationEmail(DermatologistVacation dermatologistVacation) {
		try {
			_emailService.sendAcceptedVactionEmailForDermatologistAsync(dermatologistVacation);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	@Override
	public DermatologistVacation decline(RequestDeclineDTO requestDeclineDTO) {
		DermatologistVacation dermatologistVacation = _dermatologistVacationRepository.getOne(requestDeclineDTO.getVacationId());
		dermatologistVacation.setStatus(Status.DECLINED);
		if(sendDeclinedVacationEmail(dermatologistVacation, requestDeclineDTO.getExplanation()))
			return _dermatologistVacationRepository.save(dermatologistVacation);
		
		return null;
	}

	private boolean sendDeclinedVacationEmail(DermatologistVacation dermatologistVacation, String explanation) {
		try {
			_emailService.sendDeclinedVactionEmailForDermatologistAsync(dermatologistVacation, explanation);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	@Override
	public DermatologistVacation requestForVacationDermatologist(DermatologistVacation dv) {
			
		DermatologistVacation dermatologistVacation = new DermatologistVacation();
		dermatologistVacation.setStartDate(dv.getStartDate());
		dermatologistVacation.setEndDate(dv.getEndDate());
		dermatologistVacation.setStatus(Status.WAITING);
		dermatologistVacation.setDermatologist(dv.getDermatologist());
		
		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		
		Calendar vacationS = Calendar.getInstance();
		vacationS.setTime(dv.getStartDate());
		
		Calendar vacationE = Calendar.getInstance(); // creates calendar
		vacationE.setTime(dv.getEndDate());               // sets calendar time/date
		
		Long startDate = start.getTimeInMillis(); 
		Long vacationStart = vacationS.getTimeInMillis();
		Long vacationEnd = vacationE.getTimeInMillis(); 
		
		if(vacationEnd <= vacationStart || startDate > vacationStart || startDate > vacationEnd) {
			return null;
		}
		
		return _dermatologistVacationRepository.save(dermatologistVacation);
	}

	@Override
	public List<ResponseVacationDTO> isOnVacation(Long dermatologistId) {
		List<DermatologistVacation> acceptedDermatologistVacations = _dermatologistVacationRepository.findAllByStatus(Status.ACCEPTED);
		List<ResponseVacationDTO> responseVacationList = new ArrayList<ResponseVacationDTO>();
		for (DermatologistVacation dVacation : acceptedDermatologistVacations) {
			Dermatologist d = dVacation.getDermatologist();
			if (d.getUserId() == dermatologistId) {
				StringBuilder fullName = new StringBuilder();
				fullName.append(d.getFirstName());
				fullName.append(" ");
				fullName.append(d.getLastName());
				
				StringBuilder description = new StringBuilder();
				
				String startOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getStartDate());
			    String endOfVacation = new SimpleDateFormat("dd.MM.yyyy.").format(dVacation.getEndDate());
				description.append("On the vacation is from ");
				description.append(startOfVacation);
				description.append(" to ");
				description.append(endOfVacation);
				ResponseVacationDTO responseVacationDTO = new ResponseVacationDTO(fullName.toString(), d.getEmail(), description.toString());
				responseVacationList.add(responseVacationDTO);
			}
		}
		
		return responseVacationList;
	
	}
	
	@Override
	public List<DermatologistVacation> findAllDermatologistVacationWithStatusWaitingByDermatologistId(Long dermatologistId) {
		List<DermatologistVacation> allVacation = _dermatologistVacationRepository.findAll();
		List<DermatologistVacation> list = new ArrayList<DermatologistVacation>();
		
		for (DermatologistVacation dVacation : allVacation) {
			if(dVacation.getStatus() == Status.ACCEPTED && dVacation.getDermatologist().getUserId() == dermatologistId) {
				list.add(dVacation);
			}
		}
		return list;
	}

	public ArrayList<DermatologistVacation> findAllAcceptedVacations() {
		ArrayList<DermatologistVacation> vacations = (ArrayList<DermatologistVacation>) _dermatologistVacationRepository.findAll();
		ArrayList<DermatologistVacation> result = new ArrayList<DermatologistVacation>();
		for (DermatologistVacation dermatologistVacation : vacations) {
			if(dermatologistVacation.getStatus() == Status.ACCEPTED) {
				result.add(dermatologistVacation);
			}
		}
		return result;
	}

}
