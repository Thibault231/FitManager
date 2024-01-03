package fr.isika.cda.javaee.presentation.viewmodel;

import java.beans.Visibility;

import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Address;
import fr.isika.cda.javaee.entity.users.Civility;
import fr.isika.cda.javaee.entity.users.Contact;
import fr.isika.cda.javaee.entity.users.Profile;
import fr.isika.cda.javaee.entity.users.User;

public class UserViewModel {
	private String email;
	private String password;
	private User user;
	private Profile profile;
	private Account account;
	private Address adress;
	private Contact contact;
	private Civility civility;

//**********************************
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

}
