package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Named
public class UserController implements Serializable {

	@Inject
	private IDaoUser userDao;

	private UserViewModel form;

	public String addNewUser() {
		User userToCreate = new User();
		userToCreate.setActive(true);
		userDao.createUser(userToCreate);
		return "index";
	}

	public String deleteUser(Long userToDeleteId) {
		userDao.deleteUser(userToDeleteId);
		return "index";
	}

	public String authentification() {
		String msg;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (form.login.isEmpty()) {
			msg = "Login " + form.login + " inexistant !!";
			fc.addMessage(null, new FacesMessage(msg));
			return null;
		} else if (form.password.isEmpty()) {
			msg = "vÃ©rifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(msg));
			return null;
		} else {
			fc.getExternalContext().getSessionMap().put("login", form.login);
		}
		return "index";
	}

	public String logout() {
		form.login = form.password = "";
		return null;
	}

}
