package domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOContactGroup {

	private ContactGroup contactGroup;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public DAOContactGroup(){
		this.contactGroup = null;
	}
	
	public void createContactGroup(String groupName){
		this.contactGroup = new ContactGroup(groupName);
	}
	
	public void createContactGroup(ContactGroup contactGroup){
		this.contactGroup = contactGroup;
	}
	
	public void validateContactGroup(){
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(this.contactGroup);
	}
	
	public void commitContactGroup(){
		transaction.commit();
		session.close();	
	}
	
	public void addContact(Contact contact){
		this.contactGroup.addContact(contact);
	}

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}
}
