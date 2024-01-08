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
	 * @param spaceViewModel (: SpaceViewModel)
	 * @return
	 */
	public Long createUser(SpaceViewModel spaceViewModel) throws UserExistsException {
		User user = spaceViewModel.getUser();
		if (spaceViewModel.getAdministrativeDocPath() != null) {
			user.getAccount().getAdministrativeDocument().setFilePath(spaceViewModel.getAdministrativeDocPath());
		}
		return createUserAndRelations(user);
	}

	/**
	 * Create a new user object if it not already exist in the database then fill it
	 * from a form and call the userDao for persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @return
	 */
	public Long createUser(UserViewModel userViewModel) throws UserExistsException {
		User user = userViewModel.getUser();
		return createUserAndRelations(user);
	}

	private Long createUserAndRelations(User user) throws UserExistsException {
		User previousManager = userDao.getUserByEmail(user.getAccount().getLogin());

		if (previousManager == null) {
			User userToCreate = new User(true);
			userToCreate.setProfile(user.getProfile());
			userToCreate.setAccount(user.getAccount());

			userToCreate.getProfile().getContact().setEmail(user.getAccount().getLogin());

			return userDao.createUser(userToCreate);
		}
		// On arrive ici si le user existe
		throw new UserExistsException(
				String.format("L'uilisateur avec le login (%s) existe déjà", user.getAccount().getLogin()));
	}
}
