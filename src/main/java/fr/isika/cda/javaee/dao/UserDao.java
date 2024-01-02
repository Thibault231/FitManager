package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.users.User;

@Stateless
public class UserDao implements IDaoUser {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createUser(User userToCreate) {
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
		User userToGet = em.find(User.class, userToGetId);
		return userToGet;
	}

}
