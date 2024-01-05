package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Stateless
public class UserServices {

	/**
	 * Create a new user object if it not already exist in the database then fill it
	 * from a form and call the userDao for persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @param userDao       (: IUserDao)
	 * @return
	 */
	public Long createUser(UserViewModel userViewModel, IDaoUser userDao) {
		// TODO Vérifier l'unicité du User à partir de son email dans la table Account.
		// + gérer le retour dans le contrôleur.
		User userToCreate = new User(true);
		userToCreate.setProfile(userViewModel.getUser().getProfile());
		userToCreate.setAccount(userViewModel.getUser().getAccount());
		userToCreate.getAccount().setLogin(userToCreate.getProfile().getContact().getEmail());
		return userDao.createUser(userToCreate);
	}
}
