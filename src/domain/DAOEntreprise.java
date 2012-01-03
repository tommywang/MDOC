package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOEntreprise extends DAOContact {
	private Entreprise entreprise;
	protected Address address;
	protected Set<ContactGroup> contactGroupSet;
	protected Set<PhoneNumber> phoneNumberSet;	
	
	public DAOEntreprise(){
		super();
		this.entreprise=null;
		this.address = null;
		this.contactGroupSet = new HashSet<ContactGroup>();
		this.phoneNumberSet = new HashSet<PhoneNumber>();
	}
	
	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
		this.entreprise.setAddress(this.address);
	}
	
	public void createContactGroupSet(Set<String> groupNameSet){
		for (String groupName : groupNameSet){
			ContactGroup contactGroup = new ContactGroup(groupName);
			this.entreprise.addContactGroup(contactGroup);
			contactGroup.addContact(this.entreprise);
			this.contactGroupSet.add(contactGroup);
		}
	}
	
	public void createPhoneNumberSet(Set<String> phoneNumberSet_, Set<String> phoneKindSet){
		for (Iterator<String> iterator = phoneKindSet.iterator(), iterator2=phoneNumberSet_.iterator(); iterator.hasNext()&&iterator2.hasNext();) {
			String phoneNumber_ = (String) iterator2.next();
			String phoneKind = (String) iterator.next();
			PhoneNumber phoneNumber = new PhoneNumber(phoneNumber_, phoneKind);
			this.entreprise.addPhoneNumber(phoneNumber);
			phoneNumber.setContact(this.entreprise);
			this.phoneNumberSet.add(phoneNumber);
		}
	}
	
	public void createEntreprise(String firstName, String lastName, String email, int numSiret){
		this.entreprise = new Entreprise(firstName, lastName, email,numSiret);
	}
	
	public void commit(){
		//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		//Session session = sessionFactory.openSession();
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		session.save(address);
		for (ContactGroup contactGroup : this.contactGroupSet){
			session.save(contactGroup);
		}
		for (PhoneNumber phoneNumber : this.phoneNumberSet){
			session.save(phoneNumber);
		}
		session.save(this.entreprise);
		//session.flush();
		transaction.commit();
		//session.close();
		HibernateUtil.closeSession();
	}
}
