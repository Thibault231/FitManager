package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;

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
			userToCreate.setLinkedSpaces(userFromForm.getLinkedSpaces());
			userToCreate.getProfile().getContact().setEmail(userFromForm.getAccount().getLogin());
			return userDao.createUser(userToCreate);
		}
		throw new UserExistsException(
				String.format("L'uilisateur avec le login (%s) existe déjà", userFromForm.getAccount().getLogin()));
	}

	public void updateUserOnPlateform(User userToUpdate, Long currentUserId) {
		User currentUser = userDao.getUserById(currentUserId);
		if (userToUpdate.getProfile().getCivility().getName() != null) {
			String newName = userToUpdate.getProfile().getCivility().getName();
			currentUser.getProfile().getCivility().setName(newName);
		}
		userDao.updateUser(currentUser);
	}

}
