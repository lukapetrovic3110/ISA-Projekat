package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AnswerOnComplaintForPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintEmployeeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ComplaintPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AnswerEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.AnswerPharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintEmployee;
import rs.ac.uns.ftn.informatika.jpa.model.ComplaintPharmacy;

@Service
public interface IComplaintService {

	ComplaintEmployee createComplaint(ComplaintEmployeeDTO complaintEmployeeDTO);

	ComplaintPharmacy createComplaintForPharmacy(ComplaintPharmacyDTO complaintPharmacyDTO);
	
	ArrayList<ComplaintEmployee> getComplaintsEmployee();
	
	ArrayList<ComplaintPharmacy> getComplaintsPharmacy();
	
	AnswerEmployee answerOnComplaintForEmployee(AnswerOnComplaintDTO answerOnComplaintDTO);
	
	AnswerPharmacy answerOnComplaintForPharmacy(AnswerOnComplaintForPharmacyDTO answerOnComplaintDTO);
}
