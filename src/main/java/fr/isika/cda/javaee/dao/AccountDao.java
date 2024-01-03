package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.users.Account;

	@Stateless
	public class AccountDao implements IDaoAccount {

		@PersistenceContext
		private EntityManager em;

		public Long createAccount(Account accountToCreate) {
			em.persist(accountToCreate);
			return accountToCreate.getAccountId();
		}

		public boolean deleteAccount(Long accountToDeleteId) {
			Account accountTodelete = em.find(Account.class, accountToDeleteId);
			if (accountTodelete != null) {
				em.remove(accountTodelete);
				return true;
			} else {
				return false;
			}
		}

		public Account getAccountById(Long accountToGetId) {
			Account accountToGet = em.find(Account.class, accountToGetId);
			return accountToGet;
		}
}
