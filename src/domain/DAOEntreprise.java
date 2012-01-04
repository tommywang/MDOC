package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

public class DAOEntreprise extends DAOContact {
	private Entreprise entreprise;
	protected Address address;
	protected Set<ContactGroup> contactGroupSet;
	protected Set<PhoneNumber> phoneNumberSet;	
	private HibernateTemplate hibernateTemplate;
	
	public DAOEntreprise(){
		super();
		this.entreprise=null;
		this.address = null;
		this.contactGroupSet = new HashSet<ContactGroup>();
		this.phoneNumberSet = new HashSet<PhoneNumber>();
	}
	
	public void setHibernateTemplate(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
		this.entreprise.setAddress(this.address);
	}

	/*
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createContactGroupSet(final Set<String> groupNameSet){
		this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				session = HibernateUtil.currentSession();
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
							
							entreprise.addContactGroup(contactGroup);
							contactGroup.addContact(entreprise);
							contactGroupSet.add(contactGroup);
							break;
						}
					}
					if (!existed){
					ContactGroup contactGroup = new ContactGroup(groupName);
					entreprise.addContactGroup(contactGroup);
					contactGroup.addContact(entreprise);
					contactGroupSet.add(contactGroup);
					}
				}
				return null;
			}
		});
	}
	*/

	private ContactGroup getContactGroupByName(String name){
		Session session = HibernateUtil.currentSession();
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();
		while(it.hasNext()){
			ContactGroup contactGroup=(ContactGroup)it.next();
			if (contactGroup.getGroupName().equals(name))
				return contactGroup;
		}
		return null;
	}
	
	public void createContactGroupSet(Set<String> groupNameSet){

		Session session = HibernateUtil.currentSession();
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();
		Set<String> groupNameSaved= new HashSet<String>();
		while(it.hasNext()){
			groupNameSaved.add(((ContactGroup)it.next()).getGroupName());
		}
		for (String groupName : groupNameSet){
			if (groupNameSaved.contains(groupName)){
				ContactGroup contactGroup=getContactGroupByName(groupName);
				this.entreprise.addContactGroup(contactGroup);
				contactGroup.addContact(this.entreprise);
				this.contactGroupSet.add(contactGroup);
			}
			else{
				ContactGroup contactGroup = new ContactGroup(groupName);
				this.entreprise.addContactGroup(contactGroup);
				contactGroup.addContact(this.entreprise);
				this.contactGroupSet.add(contactGroup);
			}
		}
	}
	
	/*
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
					existed=true||existed;
					
					this.entreprise.addContactGroup(contactGroup);
					contactGroup.addContact(this.entreprise);
					//this.contactGroupSet.add(contactGroup);
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
*/
	
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
	
	/*
	@Transactional
	public void commit(){
		//Session session = HibernateUtil.currentSession();
		//Transaction transaction = session.beginTransaction();
		this.getHibernateTemplate().save(this.address);
		for (ContactGroup contactGroup : this.contactGroupSet){
			this.getHibernateTemplate().save(contactGroup);
		}
		for (PhoneNumber phoneNumber : this.phoneNumberSet){
			this.getHibernateTemplate().save(phoneNumber);
		}
		this.getHibernateTemplate().save(this.entreprise);
		//session.flush();
		//transaction.commit();
		//HibernateUtil.closeSession();
	}
	
	*/
	
	
	
	
	public void commit(){
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
		HibernateUtil.closeSession();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public void update(final long id, final String firstName, final String lastName, final String email, 
			final String street, final String city, final String zip, final String country, 
			final String phoneKind, final String phoneNumber, final String groupName, final int numSiret){
		this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				session = HibernateUtil.currentSession();
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
						contactGroup.addContact(contact);
						contactGroupSet.add(contactGroup);
						break;
					}
				}
				if (!existed){
					ContactGroup contactGroup = new ContactGroup(groupName);
					entreprise.getBooks().clear();
					entreprise.addContactGroup(contactGroup);
					contactGroup.addContact(entreprise);
					contactGroupSet.add(contactGroup);
				}
				for (ContactGroup contactGroup : contactGroupSet){
					session.save(contactGroup);
				}
				session.save(entreprise);
				transaction.commit();
		
				HibernateUtil.closeSession();
				return null;
			}
		});
	}
}

/*
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
		session.save(entreprise);
		transaction.commit();

		HibernateUtil.closeSession();
	}
	
}*/
