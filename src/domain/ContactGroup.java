package domain;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {
	private long id_contactGroup;
	private String groupName;
	Set<Contact> contacts;
	
	public ContactGroup(){
		this.id_contactGroup = 0;
		this.groupName = null;
		this.contacts = null;
	}
	
	public ContactGroup(String groupName){
		this.id_contactGroup = 0;
		this.groupName = groupName;
		this.contacts = new HashSet<Contact>();
	}
	
	public void setUpContactGroup(String groupName){
		this.groupName = groupName;
		this.contacts = new HashSet<Contact>();
	}
	
	public void addContact(Contact contact){
		this.contacts.add(contact);
	}
	
	public Set<Contact> getContacts(){
		return this.contacts;
	}
	
	public void setContacts(Set<Contact> contacts){
		this.contacts = contacts;
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
