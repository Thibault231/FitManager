package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.users.User;

/**
 * UserViewModel for User controller
 * 
 * @author Thibault
 *
 */
public class UserViewModel {
	private String email;
	private String password;
	private User user;

	public UserViewModel() {
		this.user = new User(true);
	}

//**********************************
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	@Override
	public String toString() {
		return "UserViewModel [email=" + email + ", password=" + password + ", user=" + user + ", getUser()="
				+ getUser() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
