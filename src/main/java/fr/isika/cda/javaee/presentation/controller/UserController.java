package fr.isika.cda.javaee.presentation.controller;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;

@ManagedBean(name = "User")
public class UserController {

	@Inject
	private IDaoUser userDao;

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
