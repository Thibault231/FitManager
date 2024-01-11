package fr.isika.cda.javaee.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.users.Sex;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;

/**
 * Service for user management between userDao and Controllers
 * 
 * @author Thibault
 *
 */
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

		if (userToUpdate.getProfile().getCivility().getForename() != null) {
			String newForename = userToUpdate.getProfile().getCivility().getForename();
			currentUser.getProfile().getCivility().setForename(newForename);
		}

		if (userToUpdate.getProfile().getCivility().getSex() != null) {
			Sex newSex = userToUpdate.getProfile().getCivility().getSex();
			currentUser.getProfile().getCivility().setSex(newSex);
		}

		if (userToUpdate.getProfile().getCivility().getBirthday() != null) {
			Date newBirthday = userToUpdate.getProfile().getCivility().getBirthday();
			currentUser.getProfile().getCivility().setBirthday(newBirthday);
		}

		if (userToUpdate.getAccount().getPassword() != null) {
			String newPassword = userToUpdate.getAccount().getPassword();
			currentUser.getAccount().setPassword(newPassword);
		}

		if (userToUpdate.getProfile().getAdress().getStreet() != null) {
			String newStreet = userToUpdate.getProfile().getAdress().getStreet();
			currentUser.getProfile().getAdress().setStreet(newStreet);
		}

		if (userToUpdate.getProfile().getAdress().getZipCode() != 0) {
			int newZipCode = userToUpdate.getProfile().getAdress().getZipCode();
			currentUser.getProfile().getAdress().setZipCode(newZipCode);
		}
		if (userToUpdate.getProfile().getAdress().getStreet() != null) {
			String newStreet = userToUpdate.getProfile().getAdress().getCity();
			currentUser.getProfile().getAdress().setStreet(newStreet);
		}
		userDao.updateUser(userToUpdate);

	}

}
