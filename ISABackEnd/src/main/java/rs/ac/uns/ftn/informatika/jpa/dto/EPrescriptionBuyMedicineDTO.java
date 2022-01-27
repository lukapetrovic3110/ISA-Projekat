package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.ArrayList;

public class EPrescriptionBuyMedicineDTO {
	
	private String code;
	private ArrayList<QRCodeDTO> qrcodeDTO;
	private Long pharmacyId;
	
	public EPrescriptionBuyMedicineDTO() {}
	
	public EPrescriptionBuyMedicineDTO(String code, ArrayList<QRCodeDTO> qrcodeDTO, Long pharmacyId) {
		super();
		this.code = code;
		this.qrcodeDTO = qrcodeDTO;
		this.pharmacyId = pharmacyId;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<QRCodeDTO> getQrcodeDTO() {
		return qrcodeDTO;
	}

	public void setQrcodeDTO(ArrayList<QRCodeDTO> qrcodeDTO) {
		this.qrcodeDTO = qrcodeDTO;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	

}
