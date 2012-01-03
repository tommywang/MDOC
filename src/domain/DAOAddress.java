package domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOAddress {

	private Address address;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public DAOAddress(){
		this.address = null;
	}
	
	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
	}
	
	public void createAddress(Address address){
		this.address = address;
	}

	public void validateAddress(){
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(this.address);
	}
	
	public void commitAddress(){
		transaction.commit();
		session.close();	
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
