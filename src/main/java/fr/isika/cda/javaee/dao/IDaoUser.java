package fr.isika.cda.javaee.dao;

import java.util.List;

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
	 * Hard delete of a user from the Db, using it's id.
	 * 
	 * @param userToDeleteId (:Id)
	 * @return
	 */
	boolean deleteUser(Long userToDeleteId);

}
