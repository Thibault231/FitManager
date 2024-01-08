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
	private String filePath;

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "UserViewModel [email=" + email + ", password=" + password + ", user=" + user + ", form=" + form
				+ ", filePath=" + filePath + ", getUser()=" + getUser() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getForm()=" + getForm() + ", getFilePath()=" + getFilePath()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public void uploadFile(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		// form.setFilePath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}
}
