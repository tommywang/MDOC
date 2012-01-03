package domain;

import java.util.HashSet;
import java.util.Set;

public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	private long id_contact;
	private Address address;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;
	
	public Contact() {
		this.firstName=null;
		this.lastName=null;
		this.id_contact=0;
		this.email=null;
		this.address=null;
		this.profiles=null;
		this.books=null;
	}

	public Contact(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = null;
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
	}

	public void setUpContact(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
	}
	
	public void addAddress(Address address){
		this.address = address;
	}
	
	public void addPhoneNumber(PhoneNumber phoneNumber) {
		this.profiles.add(phoneNumber);
	}
	
	public void addContactGroup(ContactGroup contactGroup){
		this.books.add(contactGroup);
	}
	
	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId_contact() {
		return id_contact;
	}

	public void setId_contact(long id_contact) {
		this.id_contact = id_contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	
}
