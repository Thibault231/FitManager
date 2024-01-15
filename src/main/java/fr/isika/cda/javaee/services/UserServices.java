package fr.isika.cda.javaee.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Sex;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.util.Crypto;
import fr.isika.cda.javaee.presentation.util.SessionUtils;

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

	@Inject
	private IDaoSpace spaceDao;

	/**
	 * Compare the unencrypted password given by the user login form to the
	 * encrypted password in the DB.
	 * 
	 * @param formPassword      (:String)
	 * @param referencePassword (:String)
	 * @return (:boolean)
	 */
	public boolean comparePassword(String formPassword, String referencePassword) {
		String decryptedReference = Crypto.DecryptDataInWords(referencePassword);
		if (decryptedReference.equals(formPassword))
			return true;
		return false;
	}

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
			// copy datas from the form
			User userToCreate = new User(true);
			userToCreate.setProfile(userFromForm.getProfile());
			userToCreate.setAccount(userFromForm.getAccount());
			userToCreate.setLinkedSpaces(userFromForm.getLinkedSpaces());
			userToCreate.getProfile().getContact().setEmail(userFromForm.getAccount().getLogin());

			// encrypt password
			userToCreate.getAccount()
					.setPassword(Crypto.EncryptDataInNumbers(userFromForm.getAccount().getPassword(), 12));
			return userDao.createUser(userToCreate);
		}
		throw new UserExistsException(
				String.format("L'utilisateur avec le login (%s) existe déjà", userFromForm.getAccount().getLogin()));
	}

	/**
	 * Create a new user object if it not already exist in the database then fill it
	 * from a form and call the userDao for persistence.
	 * 
	 * @param userViewModel (: UserViewModel)
	 * @return
	 */
	public Long createUserOnPlateform(User userFromForm) throws UserExistsException {
		User previousUser = userDao.getUserByEmail(userFromForm.getAccount().getLogin());
		Space currentSpace = spaceDao.getSpaceWithMembers(SessionUtils.getSpaceIdFromSession());

		// The new user doesn't exist in any space
		if (previousUser == null) {
			// copy datas from the form
			User userToCreate = new User(true);
			userToCreate.setProfile(userFromForm.getProfile());
			userToCreate.setAccount(userFromForm.getAccount());
			userToCreate.setLinkedSpaces(userFromForm.getLinkedSpaces());
			userToCreate.getProfile().getContact().setEmail(userFromForm.getAccount().getLogin());

			// encrypt password
			userToCreate.getAccount()
					.setPassword(Crypto.EncryptDataInNumbers(userFromForm.getAccount().getPassword(), 12));
			return userDao.createUser(userToCreate);
		}
		// The user already exist in an other space
		if (previousUser != null && !currentSpace.getUsers().contains(previousUser)) {
			currentSpace.getUsers().add(previousUser);
			previousUser.getLinkedSpaces().add(currentSpace);
			spaceDao.updateSpace(currentSpace);
			userDao.updateUser(previousUser);
			return previousUser.getUserId();

		}
		// The user already exist in this space
		throw new UserExistsException(
				String.format("L'utilisateur avec le login (%s) existe déjà", userFromForm.getAccount().getLogin()));

	}

	/**
	 * Update all attributes of a user given by a controller's form and persit them.
	 * 
	 * @param userToUpdate  (:User)
	 * @param currentUserId (:Long)
	 */
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
			String newPassword = Crypto.EncryptDataInNumbers(userToUpdate.getAccount().getPassword(), 12);
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
		if (userToUpdate.getProfile().getAdress().getCity() != null) {
			String newCity = userToUpdate.getProfile().getAdress().getCity();
			currentUser.getProfile().getAdress().setStreet(newCity);
		}

		userDao.updateUser(currentUser);
	}

}
