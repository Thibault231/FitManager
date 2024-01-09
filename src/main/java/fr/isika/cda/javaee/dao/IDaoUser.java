package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;

public interface IDaoUser {
	/**
	 * Add a new user in the database.
	 * 
	 * @param userToCreate (:User)
	 * @return The Id of the created user (:Long)
	 */
	Long createUser(User userToCreate);

	/**
	 * Return a user from the database if it exists or null otherwise.
	 * 
	 * @param userToGetId (:Long)
	 * @return user to get (:User)
	 */
	User getUserById(Long userToGetId);

	/**
	 * Return the list of all active users, regardless their roles.
	 * 
	 * @return (:List<User>)
	 */
	List<User> getAllUsers();

	/**
	 * Return a user from the database, using it's email, if it exists or null
	 * otherwise.
	 * 
	 * @param userToGetEmail (:String)
	 * @return user to get (:User)
	 */
	User getUserByEmail(String userToGetEmail);

	/**
	 * Return a user from the database with it's spaces dependencies, using it's id
	 * and it's role, if it exists or null otherwise.
	 * 
	 * @param userId (:Id)
	 * @return user to get (:User)
	 */
	User getUserByIdWithLinkedSpaces(Long userId);

	/**
	 * Return a user from the database, using it's id and it's role, if it exists or
	 * null otherwise.
	 * 
	 * @param userLogin (:String)
	 * @return user to get (:User)
	 * @param userRole (:Role)
	 * @return user to get (:User)
	 */
	User getUserByLoginAndRole(String userLogin, Role userRole);

	/**
	 * Return a user linked to a specific space, using it's login and the space Id,
	 * if it exists or null otherwise.
	 * 
	 * @param userLogin (:String)
	 * @param spaceId   (:Long)
	 * @return user to get (:User)
	 */
	User getUserBySpaceAndLogin(String userLogin, Long spaceId);

	/**
	 * Hard delete of a user from the Db, using it's id.
	 * 
	 * @param userToDeleteId (:Id)
	 * @return
	 */
	boolean deleteUser(Long userToDeleteId);

	/**
	 * Update a modified user.
	 * 
	 * @param userToUpdate (:User)
	 */
	void updateUser(User userToUpdate);

	User getUserByRole(Long roleId);

}
