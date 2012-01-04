package domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;

@Entity
public class ContactGroup {
	private long id_contactGroup;
	private String groupName;
	Set<Contact> contactSet;
	
	public ContactGroup(){
		this.id_contactGroup = 0;
		this.groupName = null;
		this.contactSet = null;
	}
	
	public ContactGroup(String groupName){
		this.id_contactGroup = 0;
		this.groupName = groupName;
		this.contactSet = new HashSet<Contact>();
	}
	
	public ContactGroup(String groupName, Set<Contact> contactSet){
		this.id_contactGroup = 0;
		this.groupName = groupName;
		this.contactSet = contactSet;
	}
	
	public void setUpContactGroup(String groupName){
		this.groupName = groupName;
		this.contactSet = new HashSet<Contact>();
	}
	
	public void addContact(Contact contact){
		this.contactSet.add(contact);
	}
	
	public Set<Contact> getContactSet(){
		return this.contactSet;
	}
	
	public void setContactSet(Set<Contact> contactSet){
		this.contactSet = contactSet;
	}
	
	public long getId_contactGroup(){
		return id_contactGroup;
	}

	public void setId_contactGroup(long id_contactGroup){
		this.id_contactGroup = id_contactGroup;
	}
	
	public String getGroupName(){
		return this.groupName;
	}
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
}
