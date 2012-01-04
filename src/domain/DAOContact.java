package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

public class DAOContact extends HibernateDaoSupport{

	private HibernateTemplate hibernateTemplate;
	protected Contact contact;
	protected Address address;
	protected Set<ContactGroup> contactGroupSet;
	protected Set<PhoneNumber> phoneNumberSet;	

	public DAOContact(){
		this.contact = null;
		this.address = null;
		this.contactGroupSet = new HashSet<ContactGroup>();
		this.phoneNumberSet = new HashSet<PhoneNumber>();
	}

	
	
	public void setHibernateTemplate(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void createContact(String firstName, String lastName, String email){
		this.contact = new Contact(firstName, lastName, email);
	}	

	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
		this.contact.setAddress(this.address);
	}

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
				this.contact.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
			}
			else{
				ContactGroup contactGroup = new ContactGroup(groupName);
				this.contact.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
			}
		}
		
		
		/*
			boolean existed=false;
			while(it.hasNext()){
				ContactGroup contactGroup=(ContactGroup)it.next();
				if (contactGroup.getGroupName().equals(groupName)){
					existed=true||existed;

					this.contact.addContactGroup(contactGroup);
					contactGroup.addContact(this.contact);
					//this.contactGroupSet.add(contactGroup);
					break;
				}
			}
			if (!existed){
				ContactGroup contactGroup = new ContactGroup(groupName);
				this.contact.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
			}
		}*/
	}

/*	
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createContactGroupSet(final Set<String> groupNameSet){

		this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				//Session session = HibernateUtil.currentSession();
				String hqlSearchGroup="from ContactGroup";
				List list=session.createQuery(hqlSearchGroup).list();
				Iterator it = list.iterator();

				for (String groupName : groupNameSet){
					boolean existed=false;
					while(it.hasNext()){
						ContactGroup contactGroup=(ContactGroup)it.next();
						if (contactGroup.getGroupName().equals(groupName)){
							existed=true;

							contact.addContactGroup(contactGroup);
							contactGroup.addContact(contact);
							contactGroupSet.add(contactGroup);
							break;
						}
					}
					if (!existed){
						ContactGroup contactGroup = new ContactGroup(groupName);
						contact.addContactGroup(contactGroup);
						contactGroup.addContact(contact);
						contactGroupSet.add(contactGroup);
					}
				}
				return null;
			}
		});
	}
	
	*/
	
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
		//session.flush();
		transaction.commit();
		HibernateUtil.closeSession();
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
		this.getHibernateTemplate().save(this.contact);
		//session.flush();
		//transaction.commit();
		//HibernateUtil.closeSession();
	}
	*/

	@Transactional
	public void commitAddress(){
		//Session session = HibernateUtil.currentSession();
		//Transaction transaction = session.beginTransaction();
		this.getHibernateTemplate().save(this.address);
		//transaction.commit();
		//HibernateUtil.closeSession();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public void update(final long id, final String firstName, final String lastName, final String email, 
			final String street, final String city, final String zip, final String country, 
			final String phoneKind, final String phoneNumber, final String groupName){
		this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				//Session session = HibernateUtil.currentSession();
				Transaction transaction = session.beginTransaction();
				Contact contact = (Contact) session.load(Contact.class, id);
				contact.setFirstName(firstName);
				contact.setLastName(lastName);
				contact.setEmail(email);
				Address address=contact.getAddress();
				address.setStreet(street);
				address.setCity(city);
				address.setZip(zip);
				address.setCountry(country);
				contact.getProfiles().iterator().next().setPhoneKind(phoneKind);
				contact.getProfiles().iterator().next().setPhoneNumber(phoneNumber);

				/*
				Set<String> groupNameSet=new HashSet<String>();
				groupNameSet.add(groupName);
				groupNameSet.add(groupName1);
				createContactGroupSet(groupNameSet);
				*/
				
				String hqlSearchGroup="from ContactGroup";
				List list=session.createQuery(hqlSearchGroup).list();
				Iterator it = list.iterator();
				boolean existed=false;
				while(it.hasNext()){
					ContactGroup contactGroup=(ContactGroup)it.next();
					if (contactGroup.getGroupName().equals(groupName)){
						existed=true;
						contact.getBooks().clear();
						contact.addContactGroup(contactGroup);
						contactGroup.addContact(contact);
						contactGroupSet.add(contactGroup);
						break;
					}
				}
				
				if (!existed){
					ContactGroup contactGroup = new ContactGroup(groupName);
					//contact.getBooks().clear();
					contact.addContactGroup(contactGroup);
					contactGroup.addContact(contact);
					contactGroupSet.add(contactGroup);
				}
				
				for (ContactGroup contactGroup : contactGroupSet){
					session.save(contactGroup);
				}
				session.save(contact);
				transaction.commit();

				HibernateUtil.closeSession();
				return null;
			}
		});
	}
	/*
	public void update(long id, String firstName, String lastName, String email, String street, String city, String zip, String country, 
			String phoneKind, String phoneNumber, String groupName){
		
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		Address address=contact.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		contact.getProfiles().iterator().next().setPhoneKind(phoneKind);
		contact.getProfiles().iterator().next().setPhoneNumber(phoneNumber);
		
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();
		boolean existed=false;
		while(it.hasNext()){
			ContactGroup contactGroup=(ContactGroup)it.next();
			if (contactGroup.getGroupName().equals(groupName)){
				existed=true;
				contact.getBooks().clear();
				contact.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
				break;
			}
		}
		if (!existed){
			ContactGroup contactGroup = new ContactGroup(groupName);
			contact.getBooks().clear();
			contact.addContactGroup(contactGroup);
			contactGroup.addContact(contact);
			this.contactGroupSet.add(contactGroup);
		}
		for (ContactGroup contactGroup : this.contactGroupSet){
			session.save(contactGroup);
		}
		session.save(contact);
		transaction.commit();

		HibernateUtil.closeSession();
	}
*/
	
	@Transactional
	public void deleteContact(long id){
		//Session session = HibernateUtil.currentSession();
		//Transaction transaction = session.beginTransaction();
		Contact contact = (Contact) this.getHibernateTemplate().load(Contact.class, id);
		ContactGroup contactGroup=contact.getBooks().iterator().next();
		contact.getBooks().remove(contactGroup);//Le groupe de contact reste
		//session.delete(contactGroup);
		this.getHibernateTemplate().delete(contact.getProfiles().iterator().next());
		this.getHibernateTemplate().save(contact);
		this.getHibernateTemplate().delete(contact);
		this.getHibernateTemplate().delete(contact.getAddress());
		//transaction.commit();
		//HibernateUtil.closeSession();
	}
	
	

	/*
	public void deleteContact(long id){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		ContactGroup contactGroup=contact.getBooks().iterator().next();
        contact.getBooks().remove(contactGroup);//Le groupe de contact reste
        //session.delete(contactGroup);
        session.delete(contact.getProfiles().iterator().next());
        session.save(contact);
        session.delete(contact);
        session.delete(contact.getAddress());
        transaction.commit();
        HibernateUtil.closeSession();
	}
	*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized Contact searchContactByName(final String firstName, final String lastName){
		System.out.println("firstName = "+ firstName + " and lastName = " + lastName);
		Contact contactTmp = (Contact)this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				String hqlSearch="select c from Contact as c left join fetch c.address a "
						+ "left join fetch c.profiles p "
						+ "left join fetch c.books b "
						+ "where firstName like :firstName and lastName like :lastName";
				List list = session.createQuery(hqlSearch).setString("firstName", firstName).setString("lastName", lastName).list();
				System.out.println(list.toString());
				Iterator it = list.iterator();
				//HibernateUtil.closeSession();
				if (it.hasNext()){
					Contact contact = (Contact)it.next();
					return contact;
				}
				else{
					return null;
				}
			}
		});
		return contactTmp;
	}
	
	/*
	public synchronized Contact searchContactByName(String firstName, String lastName){
		Session session = HibernateUtil.currentSession();
		//HQL from ..join
		String hqlSearch="select c from Contact as c left join fetch c.address a "
				+ "left join fetch c.profiles p "
				+ "left join fetch c.books b "
				+ "where firstName like :firstName and lastName like :lastName";
		List list = session.createQuery(hqlSearch).setString("firstName", firstName).setString("lastName", lastName).list();
		Iterator it = list.iterator();
		HibernateUtil.closeSession();
		if (it.hasNext()){
			Contact contact = (Contact)it.next();
			//System.out.println(contact.getLastName());	
			return contact;
		}
		else{
			return null;
		}
	}
	*/
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  Contact searchContactById(final long id){
		/*
		contactExample.setFirstName("Hello");
		contactExample.setLastName("Titi");
		Contact contact = (Contact) session.createCriteria(Contact.class).
				add(Example.create(contactExample)).uniqueResult();*/
		
		
		//HQL Criteria simple
		Session session = HibernateUtil.currentSession();
		Contact contact = (Contact) session.createCriteria(Contact.class).
				add(Restrictions.like("id_contact", id)).uniqueResult();
		return contact;
		
		/*
		Contact contactTmp = (Contact)this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				Contact contact = (Contact) session.createCriteria(Contact.class).
						add(Restrictions.like("id_contact", id)).uniqueResult();
				return contact;
			}
		});
		return contactTmp;
		*/
		
	}
	
	/*
	@Transactional

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized Set<Contact> searchContacts(final String firstName,final String lastName,final int numResult){
		Set<Contact> contactSet = (Set<Contact>)this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
			Set<Contact> contacts=new HashSet<Contact>();
			String mFirstName=firstName+"%";
			String mLastName=lastName+"%";
			List list =session.createCriteria(Contact.class).
					add(Restrictions.like("firstName", mFirstName)).
					add(Restrictions.like("lastName", mLastName)).
					setMaxResults(numResult).list();
			Iterator it = list.iterator();
			while(it.hasNext()){
				contacts.add((Contact)it.next());
			}
			return contacts;
			}
			
		});
		return contactSet;
	}
	*/
	
	
	
	public  Set<Contact> searchContacts(final String firstName,final String lastName,final int numResult){
		Session session = HibernateUtil.currentSession();
			Set<Contact> contacts=new HashSet<Contact>();
			String mFirstName=firstName+"%";
			String mLastName=lastName+"%";
			List list =session.createCriteria(Contact.class).
					add(Restrictions.like("firstName", mFirstName)).
					add(Restrictions.like("lastName", mLastName)).
					setMaxResults(numResult).list();
			Iterator it = list.iterator();
			while(it.hasNext()){
				contacts.add((Contact)it.next());
			}
			return contacts;
	}
	
	public void springSetterWay(ApplicationContext context){
		this.contact = (Contact)context.getBean("beanContact");

		this.address = (Address)context.getBean("beanAddress");
		this.contact.addAddress(this.address);

		ContactGroup contactGroup1 = (ContactGroup)context.getBean("beanContactGroup1");
		this.contact.addContactGroup(contactGroup1);
		contactGroup1.addContact(this.contact);
		this.contactGroupSet.add(contactGroup1);

		ContactGroup contactGroup2 = (ContactGroup)context.getBean("beanContactGroup2");
		this.contact.addContactGroup(contactGroup2);
		contactGroup2.addContact(this.contact);
		this.contactGroupSet.add(contactGroup2);

		PhoneNumber phoneNumber1 = (PhoneNumber)context.getBean("beanPhoneNumber1");
		this.contact.addPhoneNumber(phoneNumber1);
		phoneNumber1.setContact(contact);
		this.phoneNumberSet.add(phoneNumber1);

		PhoneNumber phoneNumber2 = (PhoneNumber)context.getBean("beanPhoneNumber2");
		this.contact.addPhoneNumber(phoneNumber2);
		phoneNumber2.setContact(contact);
		this.phoneNumberSet.add(phoneNumber2);
	}

	public void springConstructorWay(ApplicationContext context){
		this.address = (Address)context.getBean("beanAddress2");		
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
