package domain;

public class PhoneNumber {
	private String phoneNumber;
	private String phoneKind;
	private long id_phoneNumber;
	Contact contact;
	
	public PhoneNumber() {
		this.id_phoneNumber = 0;
		this.phoneKind=null;
		this.phoneNumber=null;
		this.contact=null;
	}

	public PhoneNumber(String phoneNumber, String phoneKind) {
		this.phoneNumber = phoneNumber;
		this.phoneKind = phoneKind;
		this.contact = null;
	}
	
	public void setUpPhoneNumber(String phoneNumber, String phoneKind){
		this.phoneNumber = phoneNumber;
		this.phoneKind = phoneKind;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public long getId_phoneNumber() {
		return id_phoneNumber;
	}

	public void setId_phoneNumber(long id_phoneNumber) {
		this.id_phoneNumber = id_phoneNumber;
	}

}
