package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private Long contactId;
	private String phoneNumber;
	private String email;

	// Getters and setters

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

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", getPhoneNumber()=" + getPhoneNumber() + ", getContactId()=" + getContactId() + ", getEmail()="
				+ getEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
