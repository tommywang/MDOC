package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAOContact {

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

	public void createContact(String firstName, String lastName, String email){
		this.contact = new Contact(firstName, lastName, email);
	}	

	public void createAdress(String street, String city, String zip, String country){
		this.address = new Address(street, city, zip, country);
		this.contact.setAddress(this.address);
	}

	public void createContactGroupSet(Set<String> groupNameSet){

		Session session = HibernateUtil.currentSession();
		String hqlSearchGroup="from ContactGroup";
		List list=session.createQuery(hqlSearchGroup).list();
		Iterator it = list.iterator();

		for (String groupName : groupNameSet){
			boolean existed=false;
			while(it.hasNext()){
				ContactGroup contactGroup=(ContactGroup)it.next();
				if (contactGroup.getGroupName().equals(groupName)){
					existed=true;

					this.contact.addContactGroup(contactGroup);
					contactGroup.addContact(this.contact);
					this.contactGroupSet.add(contactGroup);
					break;
				}
			}
			if (!existed){
				ContactGroup contactGroup = new ContactGroup(groupName);
				this.contact.addContactGroup(contactGroup);
				contactGroup.addContact(this.contact);
				this.contactGroupSet.add(contactGroup);
			}
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
