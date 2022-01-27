package rs.ac.uns.ftn.informatika.jpa.dto;

public class ComplaintPharmacyDTO {

		private String textEmp;
		
		private Long pharmacyId;
			
		private Long patientId;

		public ComplaintPharmacyDTO() {}
		

		public ComplaintPharmacyDTO(String textEmp, Long pharmacyId, Long patientId) {
			super();
			this.textEmp = textEmp;
			this.pharmacyId = pharmacyId;
			this.patientId = patientId;
		}

		public Long getPatientId() {
			return patientId;
		}


		public void setPatientId(Long patientId) {
			this.patientId = patientId;
		}


		public String getTextEmp() {
			return textEmp;
		}

		public void setTextEmp(String textEmp) {
			this.textEmp = textEmp;
		}





		public Long getPharmacyId() {
			return pharmacyId;
		}





		public void setPharmacyId(Long pharmacyId) {
			this.pharmacyId = pharmacyId;
		}



}
