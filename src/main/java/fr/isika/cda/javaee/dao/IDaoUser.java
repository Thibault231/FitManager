package fr.isika.cda.javaee.dao;

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
	 * @param userToGeId (:Long)
	 * @return user to get (:User)
	 */
	User getUserById(Long userToGeId);

	/**
	 * Hard delete of a user from the Db, using it's id.
	 * 
	 * @param userToDeleteId (:Id)
	 * @return
	 */
	boolean deleteUser(Long userToDeleteId);

}
