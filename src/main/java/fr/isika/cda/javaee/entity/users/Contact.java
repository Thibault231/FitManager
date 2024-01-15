package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contact datas of a user.
 * 
 * @author Alex Thibault
 *
 */
@Entity
public class Contact {

	@Id
	@GeneratedValue
	private Long contactId;

	private String phoneNumber;

	private String email;

//*******************************************************************	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
