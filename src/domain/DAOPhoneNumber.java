package domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOPhoneNumber {

	private PhoneNumber phoneNumber;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public DAOPhoneNumber(){
		phoneNumber = null;
	}
	
	public void createPhoneNumber(String phoneNumber, String phoneKind){
		this.phoneNumber = new PhoneNumber(phoneNumber, phoneKind);
	}
	
	public void createPhoneNumber(PhoneNumber phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public void addContact(Contact contact){
		this.phoneNumber.setContact(contact);
	}
	
	public void validatePhoneNumber(){
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(this.phoneNumber);
	}
	
	public void commitPhoneNumber(){
		transaction.commit();
		session.close();	
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
