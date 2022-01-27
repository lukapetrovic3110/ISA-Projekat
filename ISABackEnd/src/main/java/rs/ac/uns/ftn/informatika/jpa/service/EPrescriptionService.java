package rs.ac.uns.ftn.informatika.jpa.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javassist.NotFoundException;
import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionBuyMedicineDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAvailabilityDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.QRCodeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionDTO;
import rs.ac.uns.ftn.informatika.jpa.iservice.IEPrescriptionService;
import rs.ac.uns.ftn.informatika.jpa.iservice.IMedicineService;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescriptionStatus;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.model.MedicineItem;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.PriceTag;
import rs.ac.uns.ftn.informatika.jpa.repository.IEPrescriptionRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineItemRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IMedicineRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.IPharmacyRepository;

@Service
public class EPrescriptionService implements IEPrescriptionService {

	@Autowired
	private IEPrescriptionRepository _ePrescriptionRepository;

	@Autowired
	private IPharmacyRepository _pharmacyRepository;
	
	@Autowired
	private IMedicineItemRepository _medicineItemRepository;
	
	@Autowired
	private IMedicineService _medicineService;
	
	@Autowired
	private IMedicineRepository _medicineRepository;
	
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
		ArrayList<Medicine> med = getIssuedMedicine(patientId);
		for (Medicine medicine : med) {
			if(!result.contains(medicine)) {
				result.add(medicine);
			}
		}
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

	@Override
	public ArrayList<Medicine> getIssuedMedicine(Long patientId) {
		ArrayList<EPrescription> all = (ArrayList<EPrescription>) _ePrescriptionRepository.findAll();
		ArrayList<Medicine> result = new ArrayList<Medicine>();
		for (EPrescription ePrescription : all) {
			if(ePrescription.getPatient().getUserId() == patientId && ePrescription.getStatus().equals(EPrescriptionStatus.PROCESSED)) {
				result.addAll(ePrescription.getMedicine());
			}
		}
		return result;
	}

	@Override
	public EPrescription findByCode(String code) {
		return _ePrescriptionRepository.findByCode(code);
	}
	
	public static String decodeQRCode(File qrCodeimage) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch ( com.google.zxing.NotFoundException n) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

	@Override
	public ArrayList<QRCodeDTO> getQRCodeMedicine(String decodedText) {
		ArrayList<QRCodeDTO> qrCodeDTOs = new ArrayList<>();
		
		if(decodedText.contains(";")) {					//792521!brufen_M60_4;aspirin_M35_7
			String []code = decodedText.split("!");		//792521!brufen_M60_4
			String []medicines = code[1].split(";");	//brufen_M60_4
			
			for(String medicine : medicines) {
				String []medicineParts = medicine.split("_");	
				
				qrCodeDTOs.add(new QRCodeDTO(medicineParts[0], medicineParts[1], Integer.parseInt(medicineParts[2])));
			}
		}
		else {
			String []code = decodedText.split("!");
			String []medicineParts = code[1].split("_");
			qrCodeDTOs.add(new QRCodeDTO(medicineParts[0], medicineParts[1], Integer.parseInt(medicineParts[2])));
		}
		
		return qrCodeDTOs;
	}

	@Override
	public String getCodeForEPrescription(String decodedText) {
	     String []code = decodedText.split("!");
	     return code[0];
	}

	@Override
	public ArrayList<PharmacyAvailabilityDTO> checkAvailabilityMedicineInPharmacies(ArrayList<QRCodeDTO> qrCodeDTOs) {
		ArrayList<Pharmacy> pharmacies = (ArrayList<Pharmacy>) _pharmacyRepository.findAll();
		ArrayList<PharmacyAvailabilityDTO> result = new ArrayList<PharmacyAvailabilityDTO>();
		
		for(Pharmacy p: pharmacies) {
			double sumPrice = 0;
			for(QRCodeDTO q: qrCodeDTOs) {
				for(MedicineItem m: p.getMedicineItem()) {
					if(q.getName().equals(m.getMedicine().getName()) && q.getCode().equals(m.getMedicine().getMedicineCode()) && q.getQuantity() < m.getQuantity()) {
						if(_medicineService.getCurrentPriceForMedicine(p, q.getName())!=null) {
							PriceTag price = _medicineService.getCurrentPriceForMedicine(p, q.getName());
							sumPrice += q.getQuantity() * price.getPrice();
							result.add(new PharmacyAvailabilityDTO(p, sumPrice));
					}
					}
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public EPrescription saveEPrescription(EPrescriptionBuyMedicineDTO ePrescription, Pharmacy pharmacy, Patient patient) {
		ArrayList<QRCodeDTO> qrCodeDTOs = ePrescription.getQrcodeDTO();
		EPrescription ep = new EPrescription();
		ArrayList<MedicineItem> medicineItems = (ArrayList<MedicineItem>) pharmacy.getMedicineItem();
		
		ArrayList<Medicine> medicineEPrescription = new ArrayList<Medicine>();
		
		for(QRCodeDTO q: qrCodeDTOs) {
			for(MedicineItem m: medicineItems) {
				if(q.getCode().equals(m.getMedicine().getMedicineCode())) {
					int quantity = m.getQuantity() - q.getQuantity();
					m.setQuantity(quantity);
					
					ArrayList<Medicine> medicines = (ArrayList<Medicine>) _medicineRepository.findAll();
					for(Medicine med: medicines) {
						if(med.getMedicineCode().equals(q.getCode())) {
							medicineEPrescription.add(med);
						}
					}
					ep.setPatient(patient);
					ep.setCode(ePrescription.getCode());
					ep.setDate(new Date());
					ep.setPharmacy(pharmacy);
					ep.setMedicine(medicineEPrescription);
				}
			}
		}
		
		return _ePrescriptionRepository.save(ep);
	}
	
	
		
}
