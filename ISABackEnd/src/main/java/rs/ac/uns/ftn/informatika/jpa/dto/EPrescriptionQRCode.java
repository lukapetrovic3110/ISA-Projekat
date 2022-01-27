package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.ArrayList;

public class EPrescriptionQRCode {
	private ArrayList<QRCodeDTO> qrcodeDTO;
	private String code;
	private ArrayList<PharmacyAvailabilityDTO> pharmacyAvailabilityDTO;
	
	public EPrescriptionQRCode() {}
	
	public EPrescriptionQRCode(ArrayList<QRCodeDTO> qrcodeDTO, String code,
			ArrayList<PharmacyAvailabilityDTO> pharmacyAvailabilityDTO) {
		super();
		this.qrcodeDTO = qrcodeDTO;
		this.code = code;
		this.pharmacyAvailabilityDTO = pharmacyAvailabilityDTO;
	}

	public ArrayList<QRCodeDTO> getQrcodeDTO() {
		return qrcodeDTO;
	}

	public void setQrcodeDTO(ArrayList<QRCodeDTO> qrcodeDTO) {
		this.qrcodeDTO = qrcodeDTO;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<PharmacyAvailabilityDTO> getPharmacyAvailabilityDTO() {
		return pharmacyAvailabilityDTO;
	}

	public void setPharmacyAvailabilityDTO(ArrayList<PharmacyAvailabilityDTO> pharmacyAvailabilityDTO) {
		this.pharmacyAvailabilityDTO = pharmacyAvailabilityDTO;
	}
}