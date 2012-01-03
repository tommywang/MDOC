package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();
		for (String groupName : groupNameSet){
			boolean existed=false;
			while(it.hasNext()){
				//System.out.println(((ContactGroup)it.next()).getGroupName());
				ContactGroup contactGroup=(ContactGroup)it.next();
				if (contactGroup.getGroupName().equals(groupName)){
					existed=true;
					
					this.entreprise.addContactGroup(contactGroup);
					contactGroup.addContact(this.entreprise);
					this.contactGroupSet.add(contactGroup);
					break;
				}
			}
			if (!existed){
			ContactGroup contactGroup = new ContactGroup(groupName);
			this.entreprise.addContactGroup(contactGroup);
			contactGroup.addContact(this.entreprise);
			this.contactGroupSet.add(contactGroup);
			}
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
	
	public void update(long id, String firstName, String lastName, String email, String street, String city, String zip, String country, 
			String phoneKind, String phoneNumber, String groupName, int numSiret){
		
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		entreprise.setFirstName(firstName);
		entreprise.setLastName(lastName);
		entreprise.setEmail(email);
		entreprise.setNumSiret(numSiret);
		Address address=entreprise.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		entreprise.getProfiles().iterator().next().setPhoneKind(phoneKind);
		entreprise.getProfiles().iterator().next().setPhoneNumber(phoneNumber);
		
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();
		boolean existed=false;
		while(it.hasNext()){
			ContactGroup contactGroup=(ContactGroup)it.next();
			if (contactGroup.getGroupName().equals(groupName)){
				existed=true;
				entreprise.getBooks().clear();
				entreprise.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
				break;
			}
		}
		if (!existed){
			ContactGroup contactGroup = new ContactGroup(groupName);
			entreprise.getBooks().clear();
			entreprise.addContactGroup(contactGroup);
			contactGroup.addContact(entreprise);
			this.contactGroupSet.add(contactGroup);
		}
		for (ContactGroup contactGroup : this.contactGroupSet){
			session.save(contactGroup);
		}

		//this.contactGroupSet.add(new ContactGroup(groupName));
		/*
		Set<String> setGroupName = new HashSet<String>();
		setGroupName.add(groupName);
		createContactGroupSet(setGroupName);
		for (ContactGroup contactGroup : this.contactGroupSet){
			session.save(contactGroup);
		}*/
		session.save(entreprise);
		transaction.commit();

		HibernateUtil.closeSession();
	}
}
