package fr.isika.cda.javaee.initDb;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;

/**
 * Put Training set of object in the DB at the initialization of the
 * application.
 * 
 * @author Thibault
 *
 */
@Startup
@Singleton
public class InitDb {
	private static boolean initialized = false;

	@PersistenceContext
	EntityManager em;

	@Inject
	private IDaoUser userDao;

	@Inject
	private IDaoSpace spaceDao;

	@PostConstruct
	public void init() {
		if (!initialized) {
			System.out.println("***************************** Initialize DB **********************************");

			Space spaceOne = new Space(true);
			spaceOne.getInfos().getConfiguration().setFitnessName("MyFirstSpace");
			spaceDao.createSpace(spaceOne);

			Space spaceTwo = new Space(true);
			spaceTwo.getInfos().getConfiguration().setFitnessName("MySecondSpace");
			spaceDao.createSpace(spaceTwo);

			User userManager = new User(true);
			userManager.getAccount().setLogin("titou@gmail.com");
			userManager.getAccount().setPassword("31500");
			userManager.getAccount().setRole(Role.Gestionnaire);
			userManager.getLinkedSpaces().add(spaceOne);
			userManager.getProfile().getCivility().setForename("Titou");
			userManager.getProfile().getCivility().setName("Polo");
			userDao.createUser(userManager);
			spaceOne.getUsers().add(userManager);
			spaceDao.updateSpace(spaceOne);

			User userCoach = new User(true);
			userCoach.getAccount().setLogin("nene@gmail.com");
			userCoach.getAccount().setPassword("31500");
			userCoach.getAccount().setRole(Role.Coach);
			userCoach.getLinkedSpaces().add(spaceOne);
			userCoach.getProfile().getCivility().setForename("Nene");
			userCoach.getProfile().getCivility().setName("Sacko");
			userDao.createUser(userCoach);
			spaceOne.getUsers().add(userCoach);
			spaceDao.updateSpace(spaceOne);

			User userMember = new User(true);
			userMember.getAccount().setLogin("charef@gmail.com");
			userMember.getAccount().setPassword("31500");
			userMember.getAccount().setRole(Role.Adherent);
			userMember.getLinkedSpaces().add(spaceOne);
			userMember.getProfile().getCivility().setForename("Charef");
			userMember.getProfile().getCivility().setName("Senouci");
			userDao.createUser(userMember);
			spaceOne.getUsers().add(userMember);
			spaceDao.updateSpace(spaceOne);

			/*
			 * Création de quelques cours pour la vue allCourses (Calendar)
			 */
			Course c = new Course();
			c.setName("Cours de dance");
			c.setLinkedSpaceId(7L);
			c.setCoach(userCoach);
			c.setStartDate(LocalDateTime.now());
			c.setEndDate(LocalDateTime.now().plusHours(2));
			c.setDescription("Break");
			em.persist(c);

			Course c2 = new Course();
			c2.setName("Cours de muscu");
			c2.setLinkedSpaceId(7L);
			c2.setCoach(userCoach);
			c2.setStartDate(LocalDateTime.now().plusDays(1));
			c2.setEndDate(LocalDateTime.now().plusDays(1).plusHours(2));
			c2.setDescription("Muscu");
			em.persist(c2);

			Course c3 = new Course();
			c3.setName("Cours de bodypump");
			c3.setLinkedSpaceId(14L);
			c3.setCoach(userCoach);
			c3.setStartDate(LocalDateTime.now().plusDays(2));
			c3.setEndDate(LocalDateTime.now().plusDays(2).plusHours(1));
			c3.setDescription("Body Pump");
			em.persist(c3);

			/*
			 * Fin création des cours
			 */
			System.out.println("************************ End of Initializing DB **********************************");
			initialized = true;
		}
	}
}
