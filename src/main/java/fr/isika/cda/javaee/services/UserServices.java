package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Address;
import fr.isika.cda.javaee.entity.users.Civility;
import fr.isika.cda.javaee.entity.users.Contact;
import fr.isika.cda.javaee.entity.users.Profile;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Stateless
public class UserServices {
	@Inject
	private IDaoUser userDao;

	public Long createUser(UserViewModel userViewModel) {
		User userToCreate = userViewModel.getUser();
		Profile profileToCreate = userViewModel.getProfile();
		Account accountToCreate = userViewModel.getAccount();
		Address addressToCreate = userViewModel.getAdress();
		Civility civilityToCreate = userViewModel.getCivility();
		Contact contactToCreate = userViewModel.getContact();

		profileToCreate.setAdress(addressToCreate);
		profileToCreate.setCivility(civilityToCreate);
		profileToCreate.setContact(contactToCreate);

		userToCreate.setAccount(accountToCreate);
		userToCreate.setProfile(profileToCreate);
		userToCreate.setActive(true);

		return userDao.createUser(userToCreate);

	}

}
