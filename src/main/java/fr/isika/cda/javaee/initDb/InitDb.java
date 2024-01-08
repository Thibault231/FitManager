package fr.isika.cda.javaee.initDb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;

@Startup
@Singleton
public class InitDb {
	private static boolean initialized = false;

	@PersistenceContext
	EntityManager em;

	@Inject
	private IDaoUser userDao;

	@PostConstruct
	public void init() {
		if (!initialized) {
			System.out.println("***************************** Initialize DB **********************************");
//			em.persist(new Space(true));
			User userManager = new User(true);
			userManager.getAccount().setLogin("titou@gmail.com");
			userManager.getAccount().setPassword("31500");
			userManager.getAccount().setRole(Role.Gestionnaire);
			userManager.getProfile().getCivility().setForename("Titou");
			userManager.getProfile().getCivility().setName("Polo");
			// userDao.createUser(userManager);

			System.out.println("************************ End of Initializing DB **********************************");
			initialized = true;
		}
	}
}
