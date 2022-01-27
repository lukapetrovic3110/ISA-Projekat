package rs.ac.uns.ftn.informatika.jpa.iservice;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.model.DermatologistVacation;
import rs.ac.uns.ftn.informatika.jpa.model.Examination;
import rs.ac.uns.ftn.informatika.jpa.model.WorkScheduleDermatologist;

public interface IWorkScheduleDermatologistService {

	
	String getShiftByDermatologistId(Long dermatologistId);

	String getValidForByDermatologistId(Long dermatologistId);

	boolean checkVacation(Long startExaminatin, Long endExamination, ArrayList<DermatologistVacation> vacations,
			WorkScheduleDermatologist workSchedule);

	Boolean addNewExaminationToWorkSchedule(Examination e, ArrayList<DermatologistVacation> vacations);

}
