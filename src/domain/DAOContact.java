package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOContact {
	
	private Contact contact;
	private Address address;
	private Set<ContactGroup> contactGroupSet;
	private Set<PhoneNumber> phoneNumberSet;	
	
	public DAOContact(){
		this.contact = null;
		this.address = null;
		this.contactGroupSet = new HashSet<ContactGroup>();
		this.phoneNumberSet = new HashSet<PhoneNumber>();
	}
	
	public void createContact(String firstName, String lastName, String email){
		this.contact = new Contact(firstName, lastName, email);
	}	
	
	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
		this.contact.setAddress(this.address);
	}
	
	public void createContactGroupSet(Set<String> groupNameSet){
		for (String groupName : groupNameSet){
			ContactGroup contactGroup = new ContactGroup(groupName);
			this.contact.addContactGroup(contactGroup);
			contactGroup.addContact(this.contact);
			this.contactGroupSet.add(contactGroup);
		}
	}
	
	public void createPhoneNumberSet(Set<String> phoneNumberSet_, Set<String> phoneKindSet){
		for (Iterator<String> iterator = phoneKindSet.iterator(), iterator2=phoneNumberSet_.iterator(); iterator.hasNext()&&iterator2.hasNext();) {
			String phoneNumber_ = (String) iterator2.next();
			String phoneKind = (String) iterator.next();
			PhoneNumber phoneNumber = new PhoneNumber(phoneNumber_, phoneKind);
			this.contact.addPhoneNumber(phoneNumber);
			phoneNumber.setContact(this.contact);
			this.phoneNumberSet.add(phoneNumber);
		}
	}
	
	public void commit(){
		//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		//Session session = sessionFactory.openSession();
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this.address);
		for (ContactGroup contactGroup : this.contactGroupSet){
			session.save(contactGroup);
		}
		for (PhoneNumber phoneNumber : this.phoneNumberSet){
			session.save(phoneNumber);
		}
		session.save(this.contact);
		session.flush();
		transaction.commit();
		//session.close();
		HibernateUtil.closeSession();
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ContactGroup> getContactGroupSet() {
		return contactGroupSet;
	}

	public void setContactGroupSet(Set<ContactGroup> contactGroupSet) {
		this.contactGroupSet = contactGroupSet;
	}

	public Set<PhoneNumber> getPhoneNumberSet() {
		return phoneNumberSet;
	}

	public void setPhoneNumberSet(Set<PhoneNumber> phoneNumberSet) {
		this.phoneNumberSet = phoneNumberSet;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
