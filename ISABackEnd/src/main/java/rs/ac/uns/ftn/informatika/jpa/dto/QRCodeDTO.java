package rs.ac.uns.ftn.informatika.jpa.dto;

public class QRCodeDTO {
	
	private String name;
	private String code;
	private int quantity;
	
	private QRCodeDTO() {}

	public QRCodeDTO(String name, String code, int quantity) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
