package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IEPrescriptionService;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.repository.IEPrescriptionRepository;

@Service
public class EPrescriptionService implements IEPrescriptionService {

	@Autowired
	private IEPrescriptionRepository _ePrescriptionRepository;

	@Override
	public void getPharmaciesForPatient(Long patientId, ArrayList<Pharmacy> result) {
		ArrayList<EPrescription> all = (ArrayList<EPrescription>) _ePrescriptionRepository.findAll();
		for (EPrescription ePrescription : all) {
			if(ePrescription.getPatient().getUserId() == patientId) {
				if(!result.contains(ePrescription.getPharmacy())) {
					result.add(ePrescription.getPharmacy());
				}
			}
		}
		
	}

	@Override
	public void getMedicineForRating(Long patientId, ArrayList<Medicine> result) {
		/*ArrayList<EPrescription> all = (ArrayList<EPrescription>) _ePrescriptionRepository.findAll();
		for (EPrescription ePrescription : all) {
			if(ePrescription.getPatient().getUserId() == patientId) {
				if(!result.contains(ePrescription.getMedicine())) {
					result.add(ePrescription.getMedicine());
				}
			}
		}*/
	}

	@Override
	public ArrayList<EPrescriptionDTO> getByPatient(Long patientId) {
		ArrayList<EPrescription> all = (ArrayList<EPrescription>) _ePrescriptionRepository.findAll();
		ArrayList<EPrescriptionDTO> result = new ArrayList<EPrescriptionDTO>();
		for (EPrescription ePrescription : all) {
			if(ePrescription.getPatient().getUserId() == patientId) {
				result.add(new EPrescriptionDTO(ePrescription.getDate().toString(), ePrescription.getPharmacy(), ePrescription.getStatus()));
			}
		}
		return result;
	}

	@Override
	public ArrayList<EPrescriptionDTO> filtrate(String status, Long patientId) {
		ArrayList<EPrescription> all = (ArrayList<EPrescription>) _ePrescriptionRepository.findAll();
		ArrayList<EPrescriptionDTO> result = new ArrayList<EPrescriptionDTO>();
		for (EPrescription ePrescription : all) {
			if(ePrescription.getPatient().getUserId() == patientId && ePrescription.getStatus().toString().equals(status)) {
				result.add(new EPrescriptionDTO(ePrescription.getDate().toString(), ePrescription.getPharmacy(), ePrescription.getStatus()));
			}
		}
		return result;
	}
		
}
