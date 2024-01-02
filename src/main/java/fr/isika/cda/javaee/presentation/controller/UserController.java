package fr.isika.cda.javaee.presentation.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Named("User")
public class UserController {

	@Inject
	private IDaoUser userDao;
	private UserViewModel userViewModel;

	public UserViewModel getUserViewModel() {
		return userViewModel;
	}

	public void setUserViewModel(UserViewModel userViewModel) {
		this.userViewModel = userViewModel;
	}

	public String createUser() {
		User userToCreate = new User();
		userToCreate.setActive(true);
		userDao.createUser(userToCreate);
		return "index";
	}

	public String deleteUser(Long userToDeleteId) {
		userDao.deleteUser(userToDeleteId);
		return "index";
	}

	public List<User> getAllActiveUser() {
		return null;
	}

	public User getUser(Long userId) {
		return null;
	}

	public User getUser(String userEmail) {
		return null;
	}

	public String authenticate() {
		String message;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (userViewModel.getEmail().isEmpty()) {
			message = "Login " + userViewModel.getEmail() + " inexistant !!";
			fc.addMessage(null, new FacesMessage(message));
			return null;
		} else if (userViewModel.getPassword().isEmpty()) {
			message = "vérifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(message));
			return null;
		} else {
			fc.getExternalContext().getSessionMap().put("login", userViewModel.getEmail());
		}
		return "Page2";
	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "index";
	}

}
