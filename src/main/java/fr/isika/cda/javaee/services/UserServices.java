package fr.isika.cda.javaee.services;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Address;
import fr.isika.cda.javaee.entity.users.Civility;
import fr.isika.cda.javaee.entity.users.Contact;
import fr.isika.cda.javaee.entity.users.Profile;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

public class UserServices {

	public Long createUser(UserViewModel userViewModel, IDaoUser userDao) {
		User userToCreate = new User();
		userToCreate.setAccount(new Account());
		userToCreate.setProfile(new Profile());
		userToCreate.getProfile().setCivility(new Civility());
		userToCreate.getProfile().setAdress(new Address());
		userToCreate.getProfile().setContact(new Contact());
		userToCreate.getProfile().getCivility().setName(userViewModel.getName());
		userToCreate.setActive(true);
		System.out.println(userToCreate);

		return userDao.createUser(userToCreate);
	}

}
