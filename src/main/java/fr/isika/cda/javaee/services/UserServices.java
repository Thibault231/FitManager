package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;
import fr.isika.cda.javaee.services.exceptions.UserExistsException;

@Stateless
public class UserServices {

	@Inject
	private IDaoUser daoUser;

	/**
	 * Create a new user object if it not already exist in the database then fill it
	 * from a form and call the userDao for persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @return
	 */
	public Long createUser(UserViewModel userViewModel) throws UserExistsException {
		User previousManager = daoUser.getUserByEmail(userViewModel.getUser().getAccount().getLogin());
		// N'existe pas
		if (previousManager == null) {
			User userToCreate = new User(true);
			userToCreate.setProfile(userViewModel.getUser().getProfile());
			userToCreate.setAccount(userViewModel.getUser().getAccount());
			userToCreate.getProfile().getContact().setEmail(userViewModel.getUser().getAccount().getLogin());
			return daoUser.createUser(userToCreate);
		}
		// On arrive ici si le user existe
		throw new UserExistsException(
				String.format("L'uilisateur avec le login (%s) existe déjà", userViewModel.getEmail()));
	}
}
