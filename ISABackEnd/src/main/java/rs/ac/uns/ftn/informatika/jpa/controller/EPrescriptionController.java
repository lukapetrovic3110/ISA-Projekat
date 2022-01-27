package rs.ac.uns.ftn.informatika.jpa.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javassist.NotFoundException;
import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionBuyMedicineDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionQRCode;
import rs.ac.uns.ftn.informatika.jpa.dto.MedicineAvailableInPharmacyDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PharmacyAvailabilityDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.QRCodeDTO;
import rs.ac.uns.ftn.informatika.jpa.exception.ResourceConflictException;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;
import rs.ac.uns.ftn.informatika.jpa.model.Patient;
import rs.ac.uns.ftn.informatika.jpa.model.Pharmacy;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.repository.IEPrescriptionRepository;
import rs.ac.uns.ftn.informatika.jpa.service.EPrescriptionService;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.MedicineService;
import rs.ac.uns.ftn.informatika.jpa.service.PharmacyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.EPrescriptionDTO;
import rs.ac.uns.ftn.informatika.jpa.model.EPrescription;
import rs.ac.uns.ftn.informatika.jpa.model.Medicine;
import rs.ac.uns.ftn.informatika.jpa.service.EPrescriptionService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/ePrescription", produces = MediaType.APPLICATION_JSON_VALUE)
public class EPrescriptionController {

	@Autowired
	public EPrescriptionService _ePrescriptionService;
	
	@Autowired
	public MedicineService _medicineService;
	
	@Autowired
	public PharmacyService _pharmacyService;

	@Autowired
	public EmailService _emailService;
	
	@Autowired
	public IEPrescriptionRepository _ePrescriptionRepository;
	
	@PostMapping("/file")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
	ResponseEntity<EPrescriptionQRCode> searchDrugsBasedOnQRCode(@RequestParam("file") MultipartFile file) throws IOException, NotFoundException {

        if (!file.isEmpty()) {
            try {
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/images/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = _ePrescriptionService.decodeQRCode(new File("src/main/resources/images/" + file.getOriginalFilename()));
  
                System.out.println(decodedText);
                if (decodedText == null) {
                    throw new IllegalArgumentException("Please upload valid QR code!");
                } else {
                    String code = _ePrescriptionService.getCodeForEPrescription(decodedText);
                    EPrescription ePrescription = _ePrescriptionService.findByCode(code);
                    if(ePrescription!=null) {
                        throw new IllegalArgumentException("This ePrescription is already used!");
                    }
                    
                    ArrayList<QRCodeDTO> qrCodeMed = _ePrescriptionService.getQRCodeMedicine(decodedText);
                    if(qrCodeMed==null) {
                        throw new IllegalArgumentException("Please try later, there are no medicine!");
                    }
                    
                    ArrayList<PharmacyAvailabilityDTO> pharmacyDrugAvailabilityDTOs = _ePrescriptionService.checkAvailabilityMedicineInPharmacies(qrCodeMed);
                    EPrescriptionQRCode ePrescriptionDTO = new EPrescriptionQRCode(qrCodeMed,code,pharmacyDrugAvailabilityDTOs);
                    return pharmacyDrugAvailabilityDTOs == null ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(ePrescriptionDTO);

                }
            } catch (IOException | NotFoundException e) {
                throw new IllegalArgumentException("Please upload valid QR code!");}
        }
        throw new IllegalArgumentException("Please upload valid QR code!");
    }
	
	@PostMapping("/buyMedicineInPharmacy")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> buyMedicineInPharmacy(@RequestBody EPrescriptionBuyMedicineDTO ePrescription) {
    	Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Patient patient = (Patient) currentUser.getPrincipal();
		System.out.println(patient.getEmail());
		try {
			Pharmacy pharmacy = _pharmacyService.findById(ePrescription.getPharmacyId());
			try {
				List<EPrescription> eprescriptionList = _ePrescriptionRepository.findAll();
				for(EPrescription e: eprescriptionList) {
					if(e.getCode().equals(ePrescription.getCode())) {
						throw new ResourceConflictException(null, "EPrescription with that code already exists");
					}
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(pharmacy.getPharmacyId());
			_ePrescriptionService.saveEPrescription(ePrescription, pharmacy, patient);
			this._emailService.sendNotificaitionAsync(patient.getEmail(), "EPrescription INFO", ""
					+ "EPrescription was successfull and you have successfully bought drugs in this pharmacy" + ".");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	 return new ResponseEntity<>("You have successfully bought drugs in this pharmacy!", HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/getByPatient/{patientId}")
	public ResponseEntity<?> getByPatiet(@PathVariable Long patientId){
		ArrayList<EPrescriptionDTO> result = _ePrescriptionService.getByPatient(patientId);
		return new ResponseEntity<ArrayList<EPrescriptionDTO>>(result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/filtrate/{patientId}/{status}")
	public ResponseEntity<?> filtrate(@PathVariable Long patientId, @PathVariable String status){
		ArrayList<EPrescriptionDTO> result = _ePrescriptionService.filtrate(status, patientId);
		return new ResponseEntity<ArrayList<EPrescriptionDTO>>(result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/issuedMedicine/{patientId}")
	public ResponseEntity<?> getIssuedMedicine(@PathVariable Long patientId){
		ArrayList<Medicine> result = _ePrescriptionService.getIssuedMedicine(patientId);
		return new ResponseEntity<ArrayList<Medicine>>(result, HttpStatus.OK);
	}
	
}
