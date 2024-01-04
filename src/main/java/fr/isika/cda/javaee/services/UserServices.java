package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;

import fr.isika.cda.javaee.dao.IDaoUser;

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
		return userDao.createUser(userToCreate);
	}

}
