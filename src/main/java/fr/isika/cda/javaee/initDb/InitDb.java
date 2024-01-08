package fr.isika.cda.javaee.initDb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Contact;
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
			Space spaceOne = new Space(true);
			em.persist(spaceOne);

			User userManager = new User(true);
			userManager.getAccount().setLogin("titou@gmail.com");
			userManager.getAccount().setPassword("31500");
			userManager.getAccount().setRole(Role.Gestionnaire);
			userManager.getProfile().getCivility().setForename("Titou");
			userManager.getProfile().getCivility().setName("Polo");
			userDao.createUser(userManager);

			User userCoach = new User(true);
			userCoach.getAccount().setLogin("nene@gmail.com");
			userCoach.getAccount().setPassword("31500");
			userCoach.getAccount().setRole(Role.Coach);
			userCoach.getProfile().getCivility().setForename("Nene");
			userCoach.getProfile().getCivility().setName("Sacko");
			userDao.createUser(userCoach);

			System.out.println("************************ End of Initializing DB **********************************");
			initialized = true;
		}
	}
}
