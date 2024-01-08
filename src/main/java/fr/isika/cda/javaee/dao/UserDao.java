package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.users.User;

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
	public User getUserByIdWithLinkedSpaces(Long userId) {
		return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.linkedSpaces WHERE u.id = :userIdParam",
				User.class).setParameter("userIdParam", userId).getSingleResult();
	}

	@Override
	public User getUserByEmail(String userToGetEmail) {
		// @formatter:off
		try {
			User user = em
					.createQuery("SELECT u FROM User u WHERE u.account.login = :login", User.class)
					.setParameter("login", userToGetEmail)
					.getSingleResult();
			return user;
		} catch(NoResultException ex) {
			return null;
		}

		// @formatter:on
	}

	@Override
	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u WHERE u.isActive = 1", User.class).getResultList();
	}

	@Override
	public void updateUser(User userToUpdate) {
		em.merge(userToUpdate);
	}
}
