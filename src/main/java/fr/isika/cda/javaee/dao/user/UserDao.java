package fr.isika.cda.javaee.dao.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;

/**
 * Manage the persistence of User objects in MySQL DB.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
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
		em.createNativeQuery("DELETE FROM space_user WHERE users_userId = :userId")
				.setParameter("userId", userToDeleteId).executeUpdate();
		em.createNativeQuery("DELETE FROM course WHERE coach_userId = :userId").setParameter("userId", userToDeleteId)
				.executeUpdate();
		User userTodelete = getUserByIdWithLinkedSpaces(userToDeleteId);

		if (userTodelete != null) {
			em.remove(userTodelete);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u WHERE u.isActive = 1", User.class).getResultList();
	}

	@Override
	public User getUserByEmail(String userToGetEmail) {
		try {
			User user = em
					.createQuery("SELECT u FROM User u WHERE u.account.login = :login AND u.isActive = 1", User.class)
					.setParameter("login", userToGetEmail).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}

	}

	@Override
	public User getUserById(Long userToGetId) {
		try {
			User user = em
					.createQuery("SELECT u FROM User u WHERE u.userId = :userIdParam AND u.isActive = 1", User.class)
					.setParameter("userIdParam", userToGetId).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}

	}

	@Override
	public User getUserByRole(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByIdWithLinkedSpaces(Long userId) {
		try {
			return em.createQuery(
					"SELECT u FROM User u LEFT JOIN FETCH u.linkedSpaces WHERE u.id = :userIdParam AND u.isActive = 1",
					User.class).setParameter("userIdParam", userId).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public User getUserByLoginAndRole(String userLogin, Role userRole) {
		// @formatter:off
		try {
			User user = em.createQuery(
				"SELECT u FROM User u LEFT JOIN FETCH u.linkedSpaces WHERE u.account.login = :userLoginParam AND u.account.role = :userRole ",
				User.class).setParameter("userLoginParam", userLogin).setParameter("userRole", userRole)
				.getSingleResult();
			return user;
		} catch(NoResultException ex) {
			return null;
		}
		// @formatter:on
	}

	@Override
	public void updateUser(User userToUpdate) {
		em.merge(userToUpdate);
	}

	@Override
	public User getUserBySpaceAndLogin(String userLogin, Long spaceId) {
		// @formatter:off
				try {
					Space linkedSpace = em.createQuery(
							"SELECT s FROM Space s WHERE s.spaceId = :spaceIdParam", Space.class).setParameter("spaceIdParam", spaceId).getSingleResult();
					User user = em.createQuery(
						"SELECT u FROM User u LEFT JOIN FETCH u.linkedSpaces WHERE u.account.login = :userLoginParam  ",
						User.class).setParameter("userLoginParam", userLogin).getSingleResult();
					if(user.getLinkedSpaces().contains(linkedSpace)) {
						return user;
					} else {
						return null;
					}
				} catch(NoResultException ex) {
					return null;
				}
				// @formatter:on
	}

}
