package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Address;
import fr.isika.cda.javaee.entity.users.Civility;
import fr.isika.cda.javaee.entity.users.Contact;
import fr.isika.cda.javaee.entity.users.Profile;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Stateless
public class UserDao implements IDaoUser {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createUser(User userToCreate) {
		em.persist(userToCreate.getProfile().getCivility());
		em.persist(userToCreate.getProfile().getAdress());
		em.persist(userToCreate.getProfile().getContact());
		em.persist(userToCreate.getProfile());
		em.persist(userToCreate.getAccount().getAdministrativeDocument());
		em.persist(userToCreate.getAccount());
		em.persist(userToCreate);
		return userToCreate.getUserId();
	}

	@Override
	public boolean deleteUser(Long userToDeleteId) {
		User userTodelete = em.find(User.class, userToDeleteId);
		if (userTodelete != null) {
			em.remove(userTodelete);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User getUserById(Long userToGetId) {
		return em.find(User.class, userToGetId);
	}

	@Override
	public User getUserByEmail(String userToGetEmail) {
		Account account = (Account) em.createQuery("SELECT u FROM Account u WHERE u.login = :login")
				.setParameter("login", userToGetEmail).getSingleResult();
		if (account != null) {
			User userToGet = (User) em.createQuery("SELECT u FROM User u WHERE u.account = :account")
					.setParameter("account", account).getSingleResult();
			return userToGet;
		} else {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		Query queryOne = em.createQuery("SELECT u FROM User u WHERE u.isActive = 1");
		List<User> usersList = queryOne.getResultList();
		return usersList;
	}

}
