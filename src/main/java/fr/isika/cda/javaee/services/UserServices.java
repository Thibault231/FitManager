package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Stateless
public class UserServices {
	@Inject
	private IDaoUser userDao;

	/**
	 * Create a new user object if it not already exist in the database then fill it
	 * from a form and call the userDao for persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @return
	 */
	public Long createUser(User userFromForm) throws UserExistsException {
		User previousManager = userDao.getUserByEmail(userFromForm.getAccount().getLogin());

		if (previousManager == null) {
			User userToCreate = new User(true);
			userToCreate.setProfile(userFromForm.getProfile());
			userToCreate.setAccount(userFromForm.getAccount());
			userToCreate.getProfile().getContact().setEmail(userFromForm.getAccount().getLogin());
			return userDao.createUser(userToCreate);
		}
		throw new UserExistsException(
				String.format("L'uilisateur avec le login (%s) existe déjà", userFromForm.getAccount().getLogin()));
	}

}
