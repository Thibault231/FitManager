package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Stateless
public class UserServices {

	/**
	 * Create a new user object, fill it from a form and call the userDao for
	 * persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @param userDao       (: IUserDao)
	 * @return
	 */
	public Long createUser(UserViewModel userViewModel, IDaoUser userDao) {
		User userToCreate = new User(true);
		userToCreate.setProfile(userViewModel.getUser().getProfile());
		userToCreate.setAccount(userViewModel.getUser().getAccount());
		userToCreate.getAccount().setLogin(userToCreate.getProfile().getContact().getEmail());
		System.out.println("**********************" + userViewModel.getRole());
		if (userViewModel.getRole() == 1) {
			userToCreate.getAccount().setRole(Role.SuperAdmin);
		} else if (userViewModel.getRole() == 2) {
			userToCreate.getAccount().setRole(Role.Gestionnaire);
		} else if (userViewModel.getRole() == 3) {
			userToCreate.getAccount().setRole(Role.Coach);
		} else {
			userToCreate.getAccount().setRole(Role.Adherent);
		}
		return userDao.createUser(userToCreate);
	}
}
