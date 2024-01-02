package fr.isika.cda.javaee.presentation.controller;

import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Named("User")
public class UserController {

	@Inject
	private IDaoUser userDao;
	private UserViewModel userViewModel;

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

}
