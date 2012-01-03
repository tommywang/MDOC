package domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOContact {
	
	private Contact contact;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public void createContact(String firstName, String lastName, String email){
		this.contact = new Contact(firstName, lastName, email);
	}

	public void createContact(Contact contact){
		this.contact = contact;
	}
	
	public void addAdress(Address address){
		this.contact.setAddress(address);
	}
	
	public void addPhoneNumber(PhoneNumber phoneNumber){
		this.contact.addPhoneNumber(phoneNumber);
	}
	
	public void addContactGroup(ContactGroup contactGroup){
		this.contact.addContactGroup(contactGroup);
	}
	
	public void validateContact(){
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(this.contact);
	}
	
	public void commitContact(){
		transaction.commit();
		session.close();	
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
