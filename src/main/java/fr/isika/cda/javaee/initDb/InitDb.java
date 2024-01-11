package fr.isika.cda.javaee.initDb;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.subscription.IDaoSubscription;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.subscription.Subscription;
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

	@Inject
	private IDaoSubscription subDao;

	@PostConstruct
	public void init() {
		if (!initialized) {
			System.out.println("***************************** Initialize DB **********************************");
			/*
			 * Création de quelques espaces pour la vue allCourses (Calendar)
			 */
			Space spaceOne = new Space(true);
			spaceOne.getInfos().getConfiguration().setFitnessName("Calypso");
			spaceOne.getInfos().getConfiguration().setLogo("calypsoLogo.jpg");
			spaceOne.getInfos().getConfiguration().setSlogan("Water&Fitness");
			spaceOne.getInfos().getAdministrative().setAddress("20 rue du Taure TOULOUSE");
			spaceDao.createSpace(spaceOne);

			Space spaceTwo = new Space(true);
			spaceTwo.getInfos().getConfiguration().setFitnessName("KingFit");
			spaceTwo.getInfos().getConfiguration().setLogo("kingFitLogo.jpg");
			spaceTwo.getInfos().getConfiguration().setSlogan("Muscles for King & Queen");
			spaceTwo.getInfos().getAdministrative().setAddress("20 rue du Taure TOULOUSE");
			spaceDao.createSpace(spaceTwo);

			Space spaceThree = new Space(true);
			spaceThree.getInfos().getConfiguration().setFitnessName("Water&Fitness");
			spaceThree.getInfos().getConfiguration().setLogo("muscleYouLogo.jpg");
			spaceThree.getInfos().getConfiguration().setSlogan("Water&Fitness");
			spaceThree.getInfos().getAdministrative().setAddress("25 rue des marais SAINT-GRATIEN");
			spaceDao.createSpace(spaceThree);

			Space spaceFor = new Space(true);
			spaceFor.getInfos().getConfiguration().setFitnessName("Spartan");
			spaceFor.getInfos().getConfiguration().setLogo("spartanLogo.jpg");
			spaceFor.getInfos().getConfiguration().setSlogan("Stronger than you");
			spaceFor.getInfos().getAdministrative().setAddress("17 rue l'éventail LE MANS");
			spaceDao.createSpace(spaceFor);

			Space spaceFive = new Space(true);
			spaceFive.getInfos().getConfiguration().setFitnessName("XperienceZumbas");
			spaceFive.getInfos().getConfiguration().setLogo("XperienceZumbaLogo.jpg");
			spaceFive.getInfos().getConfiguration().setSlogan("ZumbasYouR");
			spaceFive.getInfos().getAdministrative().setAddress("200 route des Banderilles PAU");
			spaceDao.createSpace(spaceFive);

			/*
			 * Création de quelques utilisateurs pour la vue allCourses (Calendar)
			 */
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
			 * Création de quelques souscriptions pour la vue allCourses (Calendar)
			 */
			Subscription subscriptionOne = new Subscription(true);
			subscriptionOne.getPrice().setMonthlyPrice((float) 29.99);
			subscriptionOne.setSubscriptionName("BASIC");
			subscriptionOne
					.setDescription("Accès illimité à la salle de sport\n" + "Participation aux cours collectifs\n"
							+ "Utilisation de machines performantes\n" + "Accès aux vestiaires et aux douches");
			subscriptionOne.setEngagement("Engagement 12 mois");
			subDao.createSubscription(subscriptionOne);
			spaceOne.getSubscriptions().add(subscriptionOne);
			spaceDao.updateSpace(spaceOne);

			Subscription subscriptionTwo = new Subscription(true);
			subscriptionTwo.getPrice().setMonthlyPrice((float) 39.99);
			subscriptionTwo.setSubscriptionName("CONFORT");
			subscriptionTwo
					.setDescription("Accès illimité à la salle de sport\n" + "Participation aux cours collectifs\n"
							+ "Utilisation de machines performantes\n" + "Accès aux vestiaires et aux douches\n"
							+ "Cours individuels avec des coachs\n" + "Accès à toutes les salles du réseau");
			subscriptionTwo.setEngagement("Engagement 12 mois");
			subDao.createSubscription(subscriptionTwo);
			spaceOne.getSubscriptions().add(subscriptionTwo);
			spaceDao.updateSpace(spaceOne);

			/*
			 * Création de quelques cours pour la vue allCourses (Calendar)
			 */
			Course c = new Course();
			c.setName("Cours de dance");
			c.setLinkedSpaceId(6L);
			c.setCoach(userCoach);
			c.setStartDate(LocalDateTime.now());
			c.setEndDate(LocalDateTime.now().plusHours(2));
			c.setDescription("Break");
			em.persist(c);

			Course c2 = new Course();
			c2.setName("Cours de muscu");
			c2.setLinkedSpaceId(6L);
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
