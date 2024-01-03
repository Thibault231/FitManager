package fr.isika.cda.javaee.dao;

import fr.isika.cda.javaee.entity.users.Account;

public interface IDaoAccount {

	/**
	 * Add a new Account in the database.
	 * 
	 * @param accountToCreate (:account)
	 * @return The Id of the created account (:Long)
	 */
	Long createAccount(Account accountToCreate);

	/**
	 * Return a account from the database if it exists or null otherwise.
	 * 
	 * @param accountToGeId (:Long)
	 * @return account to get (:Account)
	 */
	Account getAccountById(Long accountToGeId);

	/**
	 * Hard delete of a account from the Db, using it's id.
	 * 
	 * @param accountToDeleteId (:Id)
	 * @return
	 */
	boolean deleteAccount(Long accountToDeleteId);
}
