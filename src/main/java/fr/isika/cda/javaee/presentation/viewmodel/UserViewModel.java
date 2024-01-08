package fr.isika.cda.javaee.presentation.viewmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.FileUploadUtils;
import fr.isika.cda.javaee.entity.users.User;

public class UserViewModel {
	private String email;
	private String password;
	private User user;
	private UserForm form;

	public UserViewModel() {
		this.user = new User(true);
		this.form = new UserForm();
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

	public UserForm getForm() {
		return form;
	}

	public void setForm(UserForm form) {
		this.form = form;

	}

	@Override
	public String toString() {
		return "UserViewModel [email=" + email + ", password=" + password + ", user=" + user + ", form=" + form
				+ ", getUser()=" + getUser() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ ", getForm()=" + getForm() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
