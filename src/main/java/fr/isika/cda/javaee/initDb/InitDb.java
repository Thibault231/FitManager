package fr.isika.cda.javaee.initDb;

import java.time.LocalDateTime;
import java.util.Date;

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
import fr.isika.cda.javaee.entity.users.Sex;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.DefaultConfig;

/**
 * Put Training set of object in the DB at the initialization of the
 * application.
 * 
 * @author Alex Charef Thibault
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
			spaceOne.getInfos().getConfiguration().setFitnessName("KingFit");
			spaceOne.getInfos().getConfiguration().setLogo("kingFitLogo.jpg");
			spaceOne.getInfos().getConfiguration().setSlogan("Muscles for King & Queen");
			spaceOne.getInfos().getAdministrative().setAddress("20 rue du Taure,31500 Toulouse");
			spaceOne.getInfos().getConfiguration().getStyle().setMainColor(DefaultConfig.DEFAULT_MAIN_COLOR);
			spaceOne.getInfos().getConfiguration().getStyle().setSecondColor("red");
			spaceOne.getInfos().getConfiguration().getStyle().setThirdcolor("grey");
			spaceDao.createSpace(spaceOne);

			Space spaceTwo = new Space(true);
			spaceTwo.getInfos().getConfiguration().setFitnessName("Calypso");
			spaceTwo.getInfos().getConfiguration().setLogo("calypsoLogo.jpg");
			spaceTwo.getInfos().getConfiguration().setSlogan("WaterAndFitNess");
			spaceTwo.getInfos().getAdministrative().setAddress("15 Rue Sainte-Catherine, 33000 Bordeaux");
			spaceDao.createSpace(spaceTwo);

			Space spaceThree = new Space(true);
			spaceThree.getInfos().getConfiguration().setFitnessName("Water&Fitness");
			spaceThree.getInfos().getConfiguration().setLogo("muscleYouLogo.jpg");
			spaceThree.getInfos().getConfiguration().setSlogan("Sculptez votre avenir, repoussez vos limites");
			spaceThree.getInfos().getAdministrative().setAddress("25 rue des marais, Saint-Gratien");
			spaceDao.createSpace(spaceThree);

			Space spaceFor = new Space(true);
			spaceFor.getInfos().getConfiguration().setFitnessName("Spartan");
			spaceFor.getInfos().getConfiguration().setLogo("spartanLogo.jpg");
			spaceFor.getInfos().getConfiguration().setSlogan("Stronger than you");
			spaceFor.getInfos().getAdministrative().setAddress("56 rue l'éventail, 72000 Le Mans");
			spaceDao.createSpace(spaceFor);

			Space spaceFive = new Space(true);
			spaceFive.getInfos().getConfiguration().setFitnessName("XperienceZumbas");
			spaceFive.getInfos().getConfiguration().setLogo("XperienceZumbaLogo.jpg");
			spaceFive.getInfos().getConfiguration().setSlogan("ZumbasYouR");
			spaceFive.getInfos().getAdministrative().setAddress("200 route des Banderilles, 31000 Toulouse");
			spaceDao.createSpace(spaceFive);

			Space spaceSix = new Space(true);
			spaceSix.getInfos().getConfiguration().setFitnessName("Explorers");
			spaceSix.getInfos().getConfiguration().setLogo("Explorers.jpg");
			spaceSix.getInfos().getConfiguration().setSlogan("Transformez votre corps, changez votre vie");
			spaceSix.getInfos().getAdministrative().setAddress("12 Rue de la Liberté, 75001 Paris");
			spaceDao.createSpace(spaceSix);

			Space spaceSeven = new Space(true);
			spaceSeven.getInfos().getConfiguration().setFitnessName("FitnessClub");
			spaceSeven.getInfos().getConfiguration().setLogo("FitnessClub.jpg");
			spaceSeven.getInfos().getConfiguration().setSlogan("Soyez fort, soyez en forme, soyez vous-même");
			spaceSeven.getInfos().getAdministrative().setAddress("28 Rue du Faubourg Saint-Honoré, 72000 Le Mans");
			spaceDao.createSpace(spaceSeven);

			Space spaceEight = new Space(true);
			spaceEight.getInfos().getConfiguration().setFitnessName("FitnessGym");
			spaceEight.getInfos().getConfiguration().setLogo("MaSalle.jpg");
			spaceEight.getInfos().getConfiguration().setSlogan("Votre parcours vers la forme physique commence ici");
			spaceEight.getInfos().getAdministrative().setAddress("45 Avenue des Champs-élysées, 75008 Paris");
			spaceDao.createSpace(spaceEight);

			Space spaceNine = new Space(true);
			spaceNine.getInfos().getConfiguration().setFitnessName("XperienceZumbas");
			spaceNine.getInfos().getConfiguration().setLogo("Gym.jpg");
			spaceNine.getInfos().getConfiguration().setSlogan("Dépassez vos limites, atteignez vos objectifs");
			spaceNine.getInfos().getAdministrative().setAddress("22 Rue de la République, 69002 Lyon");
			spaceDao.createSpace(spaceNine);

			Space spaceTen = new Space(true);
			spaceTen.getInfos().getConfiguration().setFitnessName("LaSalle");
			spaceTen.getInfos().getConfiguration().setLogo("LaSalle.jpg");
			spaceTen.getInfos().getConfiguration().setSlogan("Vivez fort, vivez sainement, vivez pleinement");
			spaceTen.getInfos().getAdministrative().setAddress("9 Quai des Berges, 67000 Strasbourg");
			spaceDao.createSpace(spaceTen);

			Space spaceEleven = new Space(true);
			spaceEleven.getInfos().getConfiguration().setFitnessName("FitnessGym");
			spaceEleven.getInfos().getConfiguration().setLogo("FitnessGym.jpg");
			spaceEleven.getInfos().getConfiguration().setSlogan("Brisez la sueur, libérez la force intérieure");
			spaceEleven.getInfos().getAdministrative().setAddress("14 Rue du Vieux Port, 13002 Marseille");
			spaceDao.createSpace(spaceEleven);

			Space spaceTwelve = new Space(true);
			spaceTwelve.getInfos().getConfiguration().setFitnessName("OnAir");
			spaceTwelve.getInfos().getConfiguration().setLogo("OnAir.jpg");
			spaceTwelve.getInfos().getConfiguration().setSlogan("Chaque goutte de sueur vous rapproche de la reussite");
			spaceTwelve.getInfos().getAdministrative().setAddress("5 Place de la Concorde, 75008 Paris");
			spaceDao.createSpace(spaceTwelve);

			Space spaceThirteen = new Space(true);
			spaceThirteen.getInfos().getConfiguration().setFitnessName("Power");
			spaceThirteen.getInfos().getConfiguration().setLogo("Power.jpg");
			spaceThirteen.getInfos().getConfiguration().setSlogan("Inspirez la confiance, expirez les excuses");
			spaceThirteen.getInfos().getAdministrative().setAddress("18 Rue de la Pompe, 49000 Angers");
			spaceDao.createSpace(spaceThirteen);

			Space spaceFourteen = new Space(true);
			spaceFourteen.getInfos().getConfiguration().setFitnessName("SportInside");
			spaceFourteen.getInfos().getConfiguration().setLogo("SportInside.jpg");
			spaceFourteen.getInfos().getConfiguration().setSlogan("La motivation vient de la persévérance");
			spaceFourteen.getInfos().getAdministrative().setAddress("25 Rue du Capitole, 31000 Toulouse");
			spaceDao.createSpace(spaceFourteen);

			Space spaceFifteen = new Space(true);
			spaceFifteen.getInfos().getConfiguration().setFitnessName("Underground");
			spaceFifteen.getInfos().getConfiguration().setLogo("Underground.jpg");
			spaceFifteen.getInfos().getConfiguration()
					.setSlogan("Votre meilleur jour commence ici, dans notre salle de sport");
			spaceFifteen.getInfos().getAdministrative().setAddress("42 Quai de la Douane, 33000 Bordeaux");
			spaceDao.createSpace(spaceFifteen);

			/*
			 * Création de quelques Gestionnaires pour la vue allCourses (Calendar)
			 */
			User userManager = new User(true);
			userManager.getAccount().setLogin("titou@gmail.com");
			userManager.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
			userManager.getAccount().setRole(Role.Gestionnaire);
			userManager.getLinkedSpaces().add(spaceOne);
			// userManager.getLinkedSpaces().add(spaceFive);
			userManager.getProfile().getCivility().setForename("Titou");
			userManager.getProfile().getCivility().setName("Polo");
			userManager.getProfile().getContact().setPhoneNumber("0756229072");
			userManager.getProfile().getAdress().setStreet("29 rue beaurepaire");
			userManager.getProfile().getAdress().setZipCode(31500);
			userManager.getProfile().getAdress().setStreet("Toulouse");
			userDao.createUser(userManager);
			spaceOne.getUsers().add(userManager);
			spaceDao.updateSpace(spaceOne);
			spaceDao.updateSpace(spaceFive);

//			User userManager1 = new User(true);
//			userManager1.getAccount().setLogin("alex@gmail.com");
//			userManager1.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userManager1.getAccount().setRole(Role.Gestionnaire);
//			userManager1.getLinkedSpaces().add(spaceOne);
//			userManager1.getProfile().getCivility().setForename("Alex");
//			userManager1.getProfile().getCivility().setName("Aka");
//			userDao.createUser(userManager1);
//			spaceOne.getUsers().add(userManager1);
//			spaceDao.updateSpace(spaceOne);
//
//			/*
//			 * Création de quelques Coachs pour la vue allCourses (Calendar)
//			 */
//
			User userCoach = new User(true);
			userCoach.getAccount().setLogin("nene@gmail.com");
			userCoach.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
			userCoach.getAccount().setRole(Role.Coach);
			userCoach.getLinkedSpaces().add(spaceOne);
			userCoach.getProfile().getCivility().setForename("Nene");
			userCoach.getProfile().getCivility().setName("Sacko");
			userCoach.getProfile().getCivility().setBirthday(new Date());
			userManager.getProfile().getCivility().setProfilePicture("coach2.jpg");
			userDao.createUser(userCoach);
			spaceOne.getUsers().add(userCoach);
			spaceDao.updateSpace(spaceOne);
//
//			User userCoach1 = new User(true);
//			userCoach1.getAccount().setLogin("Mathieu.gauthier@example.com");
//			userCoach1.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach1.getAccount().setRole(Role.Coach);
//			userCoach1.getLinkedSpaces().add(spaceOne);
//			userCoach1.getProfile().getCivility().setForename("Mathieu");
//			userCoach1.getProfile().getCivility().setName("Gauthier");
//			userCoach1.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach1.jpg");
//			userDao.createUser(userCoach1);
//			spaceOne.getUsers().add(userCoach1);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach2 = new User(true);
//			userCoach2.getAccount().setLogin("emily.renard@gmail.com");
//			userCoach2.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach2.getAccount().setRole(Role.Coach);
//			userCoach2.getLinkedSpaces().add(spaceOne);
//			userCoach2.getProfile().getCivility().setForename("Emily");
//			userCoach2.getProfile().getCivility().setName("Renard");
//			userCoach2.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach14.jpg");
//			userDao.createUser(userCoach2);
//			spaceOne.getUsers().add(userCoach2);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach3 = new User(true);
//			userCoach3.getAccount().setLogin("hugo.petit@hotmail.com");
//			userCoach3.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach3.getAccount().setRole(Role.Coach);
//			userCoach3.getLinkedSpaces().add(spaceOne);
//			userCoach3.getProfile().getCivility().setForename("Hugo");
//			userCoach3.getProfile().getCivility().setName("Petit");
//			userCoach3.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach3.jpg");
//			userDao.createUser(userCoach3);
//			spaceOne.getUsers().add(userCoach3);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach4 = new User(true);
//			userCoach4.getAccount().setLogin("luc.laurent@yahoo.com");
//			userCoach4.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach4.getAccount().setRole(Role.Coach);
//			userCoach4.getLinkedSpaces().add(spaceOne);
//			userCoach4.getProfile().getCivility().setForename("Luc");
//			userCoach4.getProfile().getCivility().setName("Laurent");
//			userCoach4.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach4.jpg");
//			userDao.createUser(userCoach4);
//			spaceOne.getUsers().add(userCoach4);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach5 = new User(true);
//			userCoach5.getAccount().setLogin("camille.moreau@emailprovider.com");
//			userCoach5.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach5.getAccount().setRole(Role.Coach);
//			userCoach5.getLinkedSpaces().add(spaceOne);
//			userCoach5.getProfile().getCivility().setForename("Camille");
//			userCoach5.getProfile().getCivility().setName("Moreau");
//			userCoach5.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach5.jpg");
//			userDao.createUser(userCoach5);
//			spaceOne.getUsers().add(userCoach5);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach6 = new User(true);
//			userCoach6.getAccount().setLogin("sarazin.girard@example.org");
//			userCoach6.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach6.getAccount().setRole(Role.Coach);
//			userCoach6.getLinkedSpaces().add(spaceOne);
//			userCoach6.getProfile().getCivility().setForename("Sarazin");
//			userCoach6.getProfile().getCivility().setName("Girard");
//			userCoach6.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach6.jpg");
//			userDao.createUser(userCoach6);
//			spaceOne.getUsers().add(userCoach6);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach7 = new User(true);
//			userCoach7.getAccount().setLogin("alex.martin@emailservice.net");
//			userCoach7.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach7.getAccount().setRole(Role.Coach);
//			userCoach7.getLinkedSpaces().add(spaceOne);
//			userCoach7.getProfile().getCivility().setForename("Alex");
//			userCoach7.getProfile().getCivility().setName("Martin");
//			userCoach7.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach7.jpg");
//			userDao.createUser(userCoach7);
//			spaceOne.getUsers().add(userCoach7);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach8 = new User(true);
//			userCoach8.getAccount().setLogin("eloi.bernard@emailcompany.org");
//			userCoach8.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach8.getAccount().setRole(Role.Coach);
//			userCoach8.getLinkedSpaces().add(spaceOne);
//			userCoach8.getProfile().getCivility().setForename("Eloi");
//			userCoach8.getProfile().getCivility().setName("Bernard");
//			userCoach8.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach8.jpg");
//			userDao.createUser(userCoach8);
//			spaceOne.getUsers().add(userCoach8);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach9 = new User(true);
//			userCoach9.getAccount().setLogin("antoine.leroux@example.net");
//			userCoach9.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach9.getAccount().setRole(Role.Coach);
//			userCoach9.getLinkedSpaces().add(spaceOne);
//			userCoach9.getProfile().getCivility().setForename("Antoine");
//			userCoach9.getProfile().getCivility().setName("Leroux");
//			userCoach9.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach9.jpg");
//			userDao.createUser(userCoach9);
//			spaceOne.getUsers().add(userCoach9);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach10 = new User(true);
//			userCoach10.getAccount().setLogin("marc.rousseau@gmail.com");
//			userCoach10.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach10.getAccount().setRole(Role.Coach);
//			userCoach10.getLinkedSpaces().add(spaceOne);
//			userCoach10.getProfile().getCivility().setForename("Marc");
//			userCoach10.getProfile().getCivility().setName("Rousseau");
//			userCoach10.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach10.jpg");
//			userDao.createUser(userCoach10);
//			spaceOne.getUsers().add(userCoach10);
//			spaceDao.updateSpace(spaceOne);
//
//			User userCoach11 = new User(true);
//			userCoach11.getAccount().setLogin("pierre.dubois@hotmail.com");
//			userCoach11.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach11.getAccount().setRole(Role.Coach);
//			userCoach11.getLinkedSpaces().add(spaceFive);
//			userCoach11.getProfile().getCivility().setForename("Pierre");
//			userCoach11.getProfile().getCivility().setName("Dubois");
//			userCoach11.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach11.jpg");
//			userDao.createUser(userCoach11);
//			spaceFive.getUsers().add(userCoach11);
//			spaceDao.updateSpace(spaceFive);
//
//			User userCoach12 = new User(true);
//			userCoach12.getAccount().setLogin("annel.lefevre@emailprovider.org");
//			userCoach12.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach12.getAccount().setRole(Role.Coach);
//			userCoach12.getLinkedSpaces().add(spaceFive);
//			userCoach12.getProfile().getCivility().setForename("Annel");
//			userCoach12.getProfile().getCivility().setName("Lefevre");
//			userCoach12.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach12.jpg");
//			userDao.createUser(userCoach12);
//			spaceFive.getUsers().add(userCoach12);
//			spaceDao.updateSpace(spaceFive);
//
//			User userCoach13 = new User(true);
//			userCoach13.getAccount().setLogin("daniel.martin@emailservice.com");
//			userCoach13.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach13.getAccount().setRole(Role.Coach);
//			userCoach13.getLinkedSpaces().add(spaceFive);
//			userCoach13.getProfile().getCivility().setForename("Daniel");
//			userCoach13.getProfile().getCivility().setName("Martin");
//			userCoach13.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach13.jpg");
//			userDao.createUser(userCoach13);
//			spaceFive.getUsers().add(userCoach13);
//			spaceDao.updateSpace(spaceFive);
//
//			User userCoach14 = new User(true);
//			userCoach14.getAccount().setLogin("lily.lambert@example.org");
//			userCoach14.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach14.getAccount().setRole(Role.Coach);
//			userCoach14.getLinkedSpaces().add(spaceFive);
//			userCoach14.getProfile().getCivility().setForename("Lily");
//			userCoach14.getProfile().getCivility().setName("Lambert");
//			userCoach14.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach16.jpg");
//			userDao.createUser(userCoach14);
//			spaceFive.getUsers().add(userCoach14);
//			spaceDao.updateSpace(spaceFive);
//
//			User userCoach15 = new User(true);
//			userCoach15.getAccount().setLogin("chris.dupont@emailcompany.net");
//			userCoach15.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userCoach15.getAccount().setRole(Role.Coach);
//			userCoach15.getLinkedSpaces().add(spaceFive);
//			userCoach15.getProfile().getCivility().setForename("Chris");
//			userCoach15.getProfile().getCivility().setName("Dupont");
//			userCoach15.getProfile().getCivility().setBirthday(new Date());
//			userManager.getProfile().getCivility().setProfilePicture("coach15.jpg");
//			userDao.createUser(userCoach15);
//			spaceFive.getUsers().add(userCoach15);
//			spaceDao.updateSpace(spaceFive);
//
			/*
			 * CrÃ©ation de quelques Adhérents pour la vue allCourses (Calendar)
			 */

			User userMember = new User(true);
			userMember.getAccount().setLogin("charef@gmail.com");
			userMember.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
			userMember.getAccount().setRole(Role.Adherent);
			userMember.getLinkedSpaces().add(spaceFive);
			userMember.getProfile().getCivility().setForename("Charef");
			userMember.getProfile().getCivility().setName("Senouci");
			userMember.getProfile().getCivility().setBirthday(new Date());
			userMember.getProfile().getCivility().setSex(Sex.Homme);
			userMember.getProfile().getAdress().setStreet("18 rue du commandant zizou");
			userMember.getProfile().getAdress().setZipCode(31500);
			userMember.getProfile().getAdress().setStreet("Toulouse");
			userMember.getProfile().getContact().setPhoneNumber("0618462597");
			userManager.getProfile().getCivility().setProfilePicture("ad6.jpg");
			userDao.createUser(userMember);
			spaceFive.getUsers().add(userMember);
			spaceDao.updateSpace(spaceFive);
//
//			User userMember1 = new User(true);
//			userMember1.getAccount().setLogin("alexandre.dubois@email.com");
//			userMember1.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember1.getAccount().setRole(Role.Adherent);
//			userMember1.getLinkedSpaces().add(spaceFive);
//			userMember1.getProfile().getCivility().setForename("Alexandre");
//			userMember1.getProfile().getCivility().setName("Dubois");
//			userMember1.getProfile().getCivility().setBirthday(new Date());
//			userMember1.getProfile().getCivility().setSex(Sex.Homme);
//			userMember1.getProfile().getAdress().setStreet("15 Rue des Tourneurs");
//			userMember1.getProfile().getAdress().setZipCode(31000);
//			userMember1.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0612345678");
//			userManager.getProfile().getCivility().setProfilePicture("ad7.jpg");
//			userDao.createUser(userMember1);
//			spaceFive.getUsers().add(userMember1);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember2 = new User(true);
//			userMember2.getAccount().setLogin("pierre.dupont@email.com");
//			userMember2.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember2.getAccount().setRole(Role.Adherent);
//			userMember2.getLinkedSpaces().add(spaceFive);
//			userMember2.getProfile().getCivility().setForename("Pierre");
//			userMember2.getProfile().getCivility().setName("Dupont");
//			userMember2.getProfile().getCivility().setBirthday(new Date());
//			userMember2.getProfile().getCivility().setSex(Sex.Homme);
//			userMember2.getProfile().getAdress().setStreet("Rue Croix Baragnon");
//			userMember2.getProfile().getAdress().setZipCode(31000);
//			userMember2.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0712345678");
//			userManager.getProfile().getCivility().setProfilePicture("ad8.jpg");
//			userDao.createUser(userMember2);
//			spaceFive.getUsers().add(userMember2);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember3 = new User(true);
//			userMember3.getAccount().setLogin("sophie.martin@email.com");
//			userMember3.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember3.getAccount().setRole(Role.Adherent);
//			userMember3.getLinkedSpaces().add(spaceFive);
//			userMember3.getProfile().getCivility().setForename("Sophie");
//			userMember3.getProfile().getCivility().setName("Martin");
//			userMember3.getProfile().getCivility().setBirthday(new Date());
//			userMember3.getProfile().getCivility().setSex(Sex.Femme);
//			userMember3.getProfile().getAdress().setStreet("6 Rue des Arts");
//			userMember3.getProfile().getAdress().setZipCode(31000);
//			userMember3.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0623456789");
//			userManager.getProfile().getCivility().setProfilePicture("ad1.jpg");
//			userDao.createUser(userMember3);
//			spaceFive.getUsers().add(userMember3);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember4 = new User(true);
//			userMember4.getAccount().setLogin("julien.leroux@email.com");
//			userMember4.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember4.getAccount().setRole(Role.Adherent);
//			userMember4.getLinkedSpaces().add(spaceFive);
//			userMember4.getProfile().getCivility().setForename("Julien");
//			userMember4.getProfile().getCivility().setName("Leroux");
//			userMember4.getProfile().getCivility().setBirthday(new Date());
//			userMember4.getProfile().getCivility().setSex(Sex.Homme);
//			userMember4.getProfile().getAdress().setStreet("4 Rue de la Colombette");
//			userMember4.getProfile().getAdress().setZipCode(31000);
//			userMember4.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0812345678");
//			userManager.getProfile().getCivility().setProfilePicture("ad9.jpg");
//			userDao.createUser(userMember4);
//			spaceFive.getUsers().add(userMember4);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember5 = new User(true);
//			userMember5.getAccount().setLogin("emilie.dubois@email.com");
//			userMember5.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember5.getAccount().setRole(Role.Adherent);
//			userMember5.getLinkedSpaces().add(spaceFive);
//			userMember5.getProfile().getCivility().setForename("Emilie");
//			userMember5.getProfile().getCivility().setName("Dubois");
//			userMember5.getProfile().getCivility().setBirthday(new Date());
//			userMember5.getProfile().getCivility().setSex(Sex.Femme);
//			userMember5.getProfile().getAdress().setStreet("9 Rue de la Pomme");
//			userMember5.getProfile().getAdress().setZipCode(31000);
//			userMember5.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0912345678");
//			userManager.getProfile().getCivility().setProfilePicture("ad2.jpg");
//			userDao.createUser(userMember5);
//			spaceFive.getUsers().add(userMember5);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember6 = new User(true);
//			userMember6.getAccount().setLogin("nicolas.moreau@email.com");
//			userMember6.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember6.getAccount().setRole(Role.Adherent);
//			userMember6.getLinkedSpaces().add(spaceFive);
//			userMember6.getProfile().getCivility().setForename("Nicolas");
//			userMember6.getProfile().getCivility().setName("Moreau");
//			userMember6.getProfile().getCivility().setBirthday(new Date());
//			userMember6.getProfile().getCivility().setSex(Sex.Homme);
//			userMember6.getProfile().getAdress().setStreet("20 Rue du Taur");
//			userMember6.getProfile().getAdress().setZipCode(31000);
//			userMember6.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0723456789");
//			userManager.getProfile().getCivility().setProfilePicture("ad10.jpg");
//			userDao.createUser(userMember6);
//			spaceFive.getUsers().add(userMember6);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember7 = new User(true);
//			userMember7.getAccount().setLogin("marie.girard@email.com");
//			userMember7.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember7.getAccount().setRole(Role.Adherent);
//			userMember7.getLinkedSpaces().add(spaceFive);
//			userMember7.getProfile().getCivility().setForename("Marie");
//			userMember7.getProfile().getCivility().setName("Girard");
//			userMember7.getProfile().getCivility().setBirthday(new Date());
//			userMember7.getProfile().getCivility().setSex(Sex.Femme);
//			userMember7.getProfile().getAdress().setStreet("1 Place Wilson");
//			userMember7.getProfile().getAdress().setZipCode(31000);
//			userMember7.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0823456789");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember7);
//			spaceFive.getUsers().add(userMember7);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember8 = new User(true);
//			userMember8.getAccount().setLogin("thomas.petit@email.com");
//			userMember8.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember8.getAccount().setRole(Role.Adherent);
//			userMember8.getLinkedSpaces().add(spaceFive);
//			userMember8.getProfile().getCivility().setForename("Thomas");
//			userMember8.getProfile().getCivility().setName("Petit");
//			userMember8.getProfile().getCivility().setBirthday(new Date());
//			userMember8.getProfile().getCivility().setSex(Sex.Homme);
//			userMember8.getProfile().getAdress().setStreet("17 Rue du Languedoc");
//			userMember8.getProfile().getAdress().setZipCode(31000);
//			userMember8.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0923456789");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember8);
//			spaceFive.getUsers().add(userMember8);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember9 = new User(true);
//			userMember9.getAccount().setLogin("camille.leclerc@email.com");
//			userMember9.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember9.getAccount().setRole(Role.Adherent);
//			userMember9.getLinkedSpaces().add(spaceFive);
//			userMember9.getProfile().getCivility().setForename("Camille");
//			userMember9.getProfile().getCivility().setName("Leclerc");
//			userMember9.getProfile().getCivility().setBirthday(new Date());
//			userMember9.getProfile().getCivility().setSex(Sex.Homme);
//			userMember9.getProfile().getAdress().setStreet("23 Rue de la Concorde");
//			userMember9.getProfile().getAdress().setZipCode(31000);
//			userMember9.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0634567890");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember9);
//			spaceFive.getUsers().add(userMember9);
//			spaceDao.updateSpace(spaceFive);
//
//			User userMember10 = new User(true);
//			userMember10.getAccount().setLogin("lucas.robert@email.com");
//			userMember10.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember10.getAccount().setRole(Role.Adherent);
//			userMember10.getLinkedSpaces().add(spaceOne);
//			userMember10.getProfile().getCivility().setForename("Lucas");
//			userMember10.getProfile().getCivility().setName("Robert");
//			userMember10.getProfile().getCivility().setBirthday(new Date());
//			userMember10.getProfile().getCivility().setSex(Sex.Homme);
//			userMember10.getProfile().getAdress().setStreet("36 rue Leroix");
//			userMember10.getProfile().getAdress().setZipCode(31000);
//			userMember10.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0734567890");
//			userManager.getProfile().getCivility().setProfilePicture("ad11.jpg");
//			userDao.createUser(userMember10);
//			spaceOne.getUsers().add(userMember10);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember11 = new User(true);
//			userMember11.getAccount().setLogin("claire.dufresne@email.com");
//			userMember11.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember11.getAccount().setRole(Role.Adherent);
//			userMember11.getLinkedSpaces().add(spaceOne);
//			userMember11.getProfile().getCivility().setForename("Claire");
//			userMember11.getProfile().getCivility().setName("Dufresne");
//			userMember11.getProfile().getCivility().setBirthday(new Date());
//			userMember11.getProfile().getCivility().setSex(Sex.Femme);
//			userMember11.getProfile().getAdress().setStreet("13 Rue du Rempart Villeneuve");
//			userMember11.getProfile().getAdress().setZipCode(31000);
//			userMember11.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0834567890");
//			userManager.getProfile().getCivility().setProfilePicture("ad3.jpg");
//			userDao.createUser(userMember11);
//			spaceOne.getUsers().add(userMember11);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember12 = new User(true);
//			userMember12.getAccount().setLogin("antoine.michel@email.com");
//			userMember12.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember12.getAccount().setRole(Role.Adherent);
//			userMember12.getLinkedSpaces().add(spaceOne);
//			userMember12.getProfile().getCivility().setForename("Antoine");
//			userMember12.getProfile().getCivility().setName("Michel");
//			userMember12.getProfile().getCivility().setBirthday(new Date());
//			userMember12.getProfile().getCivility().setSex(Sex.Homme);
//			userMember12.getProfile().getAdress().setStreet("7 avenue Montfermeil");
//			userMember12.getProfile().getAdress().setZipCode(31000);
//			userMember12.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0934567890");
//			userManager.getProfile().getCivility().setProfilePicture("ad12.jpg");
//			userDao.createUser(userMember12);
//			spaceOne.getUsers().add(userMember12);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember13 = new User(true);
//			userMember13.getAccount().setLogin("elise.richard@email.com");
//			userMember13.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember13.getAccount().setRole(Role.Adherent);
//			userMember13.getLinkedSpaces().add(spaceOne);
//			userMember13.getProfile().getCivility().setForename("Elise");
//			userMember13.getProfile().getCivility().setName("Richard");
//			userMember13.getProfile().getCivility().setBirthday(new Date());
//			userMember13.getProfile().getCivility().setSex(Sex.Femme);
//			userMember13.getProfile().getAdress().setStreet("16 Rue des Filatiers");
//			userMember13.getProfile().getAdress().setZipCode(31000);
//			userMember13.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0645678901");
//			userManager.getProfile().getCivility().setProfilePicture("ad4.jpg");
//			userDao.createUser(userMember13);
//			spaceOne.getUsers().add(userMember13);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember14 = new User(true);
//			userMember14.getAccount().setLogin("vincent.lefevre@email.com");
//			userMember14.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember14.getAccount().setRole(Role.Adherent);
//			userMember14.getLinkedSpaces().add(spaceOne);
//			userMember14.getProfile().getCivility().setForename("Vincent");
//			userMember14.getProfile().getCivility().setName("Lefevre");
//			userMember14.getProfile().getCivility().setBirthday(new Date());
//			userMember14.getProfile().getCivility().setSex(Sex.Homme);
//			userMember14.getProfile().getAdress().setStreet("2 Rue des Lois");
//			userMember14.getProfile().getAdress().setZipCode(31000);
//			userMember14.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0745678901");
//			userManager.getProfile().getCivility().setProfilePicture("ad13.jpg");
//			userDao.createUser(userMember14);
//			spaceOne.getUsers().add(userMember14);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember15 = new User(true);
//			userMember15.getAccount().setLogin("laura.martin@email.com");
//			userMember15.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember15.getAccount().setRole(Role.Adherent);
//			userMember15.getLinkedSpaces().add(spaceOne);
//			userMember15.getProfile().getCivility().setForename("Laura");
//			userMember15.getProfile().getCivility().setName("Martin");
//			userMember15.getProfile().getCivility().setBirthday(new Date());
//			userMember15.getProfile().getCivility().setSex(Sex.Femme);
//			userMember15.getProfile().getAdress().setStreet("19 Rue de la Trinité");
//			userMember15.getProfile().getAdress().setZipCode(31000);
//			userMember15.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0845678901");
//			userManager.getProfile().getCivility().setProfilePicture("ad5.jpg");
//			userDao.createUser(userMember15);
//			spaceOne.getUsers().add(userMember15);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember16 = new User(true);
//			userMember16.getAccount().setLogin("arthur.durand@email.com");
//			userMember16.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember16.getAccount().setRole(Role.Adherent);
//			userMember16.getLinkedSpaces().add(spaceOne);
//			userMember16.getProfile().getCivility().setForename("Arthur");
//			userMember16.getProfile().getCivility().setName("Durand");
//			userMember16.getProfile().getCivility().setBirthday(new Date());
//			userMember16.getProfile().getCivility().setSex(Sex.Homme);
//			userMember16.getProfile().getAdress().setStreet("21 Rue de la Dalbade");
//			userMember16.getProfile().getAdress().setZipCode(31000);
//			userMember16.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0945678901");
//			userManager.getProfile().getCivility().setProfilePicture("ad14.jpg");
//			userDao.createUser(userMember16);
//			spaceOne.getUsers().add(userMember16);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember17 = new User(true);
//			userMember17.getAccount().setLogin("léa.bernard@email.com");
//			userMember17.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember17.getAccount().setRole(Role.Adherent);
//			userMember17.getLinkedSpaces().add(spaceOne);
//			userMember17.getProfile().getCivility().setForename("Léa");
//			userMember17.getProfile().getCivility().setName("Bernard");
//			userMember17.getProfile().getCivility().setBirthday(new Date());
//			userMember17.getProfile().getCivility().setSex(Sex.Femme);
//			userMember17.getProfile().getAdress().setStreet("8 Rue des Marchands");
//			userMember17.getProfile().getAdress().setZipCode(31000);
//			userMember17.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0656789012");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember17);
//			spaceOne.getUsers().add(userMember17);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember18 = new User(true);
//			userMember18.getAccount().setLogin("hugo.garcia@email.com");
//			userMember18.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember18.getAccount().setRole(Role.Adherent);
//			userMember18.getLinkedSpaces().add(spaceOne);
//			userMember18.getProfile().getCivility().setForename("Hugo");
//			userMember18.getProfile().getCivility().setName("Garcia");
//			userMember18.getProfile().getCivility().setBirthday(new Date());
//			userMember18.getProfile().getCivility().setSex(Sex.Homme);
//			userMember18.getProfile().getAdress().setStreet("10 Rue de la Pleau");
//			userMember18.getProfile().getAdress().setZipCode(31000);
//			userMember18.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0756789012");
//			userManager.getProfile().getCivility().setProfilePicture("ad15.jpg");
//			userDao.createUser(userMember18);
//			spaceOne.getUsers().add(userMember18);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember19 = new User(true);
//			userMember19.getAccount().setLogin("clémence.renard@email.com");
//			userMember19.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember19.getAccount().setRole(Role.Adherent);
//			userMember19.getLinkedSpaces().add(spaceOne);
//			userMember19.getProfile().getCivility().setForename("Clémence");
//			userMember19.getProfile().getCivility().setName("Renard");
//			userMember19.getProfile().getCivility().setBirthday(new Date());
//			userMember19.getProfile().getCivility().setSex(Sex.Femme);
//			userMember19.getProfile().getAdress().setStreet("14 Rue des Puits Clos");
//			userMember19.getProfile().getAdress().setZipCode(31000);
//			userMember19.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0856789012");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember19);
//			spaceOne.getUsers().add(userMember19);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember20 = new User(true);
//			userMember20.getAccount().setLogin("luc.dupuis@email.com");
//			userMember20.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember20.getAccount().setRole(Role.Adherent);
//			userMember20.getLinkedSpaces().add(spaceOne);
//			userMember20.getProfile().getCivility().setForename("Luc");
//			userMember20.getProfile().getCivility().setName("Dupuis");
//			userMember20.getProfile().getCivility().setBirthday(new Date());
//			userMember20.getProfile().getCivility().setSex(Sex.Homme);
//			userMember20.getProfile().getAdress().setStreet("6 Rue du May");
//			userMember20.getProfile().getAdress().setZipCode(31000);
//			userMember20.getProfile().getAdress().setStreet("31000");
//			userMember.getProfile().getContact().setPhoneNumber("0956789012");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember20);
//			spaceOne.getUsers().add(userMember20);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember21 = new User(true);
//			userMember21.getAccount().setLogin("chloe.leroux@email.com");
//			userMember21.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember21.getAccount().setRole(Role.Adherent);
//			userMember21.getLinkedSpaces().add(spaceOne);
//			userMember21.getProfile().getCivility().setForename("Chloé");
//			userMember21.getProfile().getCivility().setName("Leroux");
//			userMember21.getProfile().getCivility().setBirthday(new Date());
//			userMember21.getProfile().getCivility().setSex(Sex.Femme);
//			userMember21.getProfile().getAdress().setStreet("25 Rue Rivals");
//			userMember21.getProfile().getAdress().setZipCode(31000);
//			userMember21.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0667890123");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember21);
//			spaceOne.getUsers().add(userMember21);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember22 = new User(true);
//			userMember22.getAccount().setLogin("maxime.dubois@email.com");
//			userMember22.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember22.getAccount().setRole(Role.Adherent);
//			userMember22.getLinkedSpaces().add(spaceOne);
//			userMember22.getProfile().getCivility().setForename("Maxime");
//			userMember22.getProfile().getCivility().setName("Dubois");
//			userMember22.getProfile().getCivility().setBirthday(new Date());
//			userMember22.getProfile().getCivility().setSex(Sex.Homme);
//			userMember22.getProfile().getAdress().setStreet("18 Rue des Blanchers");
//			userMember22.getProfile().getAdress().setZipCode(31000);
//			userMember22.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0767890123");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember22);
//			spaceOne.getUsers().add(userMember22);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember23 = new User(true);
//			userMember23.getAccount().setLogin("julia.fournier@email.com");
//			userMember23.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember23.getAccount().setRole(Role.Adherent);
//			userMember23.getLinkedSpaces().add(spaceOne);
//			userMember23.getProfile().getCivility().setForename("Julia");
//			userMember23.getProfile().getCivility().setName("Fournier");
//			userMember23.getProfile().getCivility().setBirthday(new Date());
//			userMember23.getProfile().getCivility().setSex(Sex.Femme);
//			userMember23.getProfile().getAdress().setStreet("11 Rue des Jumeaux");
//			userMember23.getProfile().getAdress().setZipCode(31000);
//			userMember23.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0867890123");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember23);
//			spaceOne.getUsers().add(userMember23);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember24 = new User(true);
//			userMember24.getAccount().setLogin("adrien.morin@email.com");
//			userMember24.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember24.getAccount().setRole(Role.Adherent);
//			userMember24.getLinkedSpaces().add(spaceOne);
//			userMember24.getProfile().getCivility().setForename("Adrien");
//			userMember24.getProfile().getCivility().setName("Morin");
//			userMember24.getProfile().getCivility().setBirthday(new Date());
//			userMember24.getProfile().getCivility().setSex(Sex.Homme);
//			userMember24.getProfile().getAdress().setStreet("15 rue de l'éventail");
//			userMember24.getProfile().getAdress().setZipCode(31000);
//			userMember24.getProfile().getAdress().setStreet("31000");
//			userMember.getProfile().getContact().setPhoneNumber("0967890123");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember24);
//			spaceOne.getUsers().add(userMember24);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember25 = new User(true);
//			userMember25.getAccount().setLogin("ophelie.robert@email.com");
//			userMember25.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember25.getAccount().setRole(Role.Adherent);
//			userMember25.getLinkedSpaces().add(spaceOne);
//			userMember25.getProfile().getCivility().setForename("Ophelie");
//			userMember25.getProfile().getCivility().setName("Robert");
//			userMember25.getProfile().getCivility().setBirthday(new Date());
//			userMember25.getProfile().getCivility().setSex(Sex.Femme);
//			userMember25.getProfile().getAdress().setStreet("15 rue de l'éventail");
//			userMember25.getProfile().getAdress().setZipCode(31000);
//			userMember25.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0678901234");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember25);
//			spaceOne.getUsers().add(userMember25);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember26 = new User(true);
//			userMember26.getAccount().setLogin("anaïs.dupuis@email.com");
//			userMember26.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember26.getAccount().setRole(Role.Adherent);
//			userMember26.getLinkedSpaces().add(spaceOne);
//			userMember26.getProfile().getCivility().setForename("Anaïs");
//			userMember26.getProfile().getCivility().setName("Dupuis");
//			userMember26.getProfile().getCivility().setBirthday(new Date());
//			userMember26.getProfile().getCivility().setSex(Sex.Femme);
//			userMember26.getProfile().getAdress().setStreet("18 rue du general Degaulle");
//			userMember26.getProfile().getAdress().setZipCode(31000);
//			userMember26.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0778901234");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember26);
//			spaceOne.getUsers().add(userMember26);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember27 = new User(true);
//			userMember27.getAccount().setLogin("romain.becker@email.com");
//			userMember27.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember27.getAccount().setRole(Role.Adherent);
//			userMember27.getLinkedSpaces().add(spaceOne);
//			userMember27.getProfile().getCivility().setForename("Romain");
//			userMember27.getProfile().getCivility().setName("Becker");
//			userMember27.getProfile().getCivility().setBirthday(new Date());
//			userMember27.getProfile().getCivility().setSex(Sex.Homme);
//			userMember27.getProfile().getAdress().setStreet("11 Rue des Jumeaux");
//			userMember27.getProfile().getAdress().setZipCode(31000);
//			userMember27.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0878901234");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember27);
//			spaceOne.getUsers().add(userMember27);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember28 = new User(true);
//			userMember28.getAccount().setLogin("manon.leroy@email.com");
//			userMember28.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember28.getAccount().setRole(Role.Adherent);
//			userMember28.getLinkedSpaces().add(spaceOne);
//			userMember28.getProfile().getCivility().setForename("Manon");
//			userMember28.getProfile().getCivility().setName("Leroy");
//			userMember28.getProfile().getCivility().setBirthday(new Date());
//			userMember28.getProfile().getCivility().setSex(Sex.Femme);
//			userMember28.getProfile().getAdress().setStreet("3 avenue bollée");
//			userMember28.getProfile().getAdress().setZipCode(31000);
//			userMember28.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0978901234");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember28);
//			spaceOne.getUsers().add(userMember28);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember29 = new User(true);
//			userMember29.getAccount().setLogin("baptiste.thomas@email.com");
//			userMember29.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember29.getAccount().setRole(Role.Adherent);
//			userMember29.getLinkedSpaces().add(spaceOne);
//			userMember29.getProfile().getCivility().setForename("Baptiste");
//			userMember29.getProfile().getCivility().setName("Thomas");
//			userMember29.getProfile().getCivility().setBirthday(new Date());
//			userMember29.getProfile().getCivility().setSex(Sex.Homme);
//			userMember29.getProfile().getAdress().setStreet("5 rue Auguste Poullain");
//			userMember29.getProfile().getAdress().setZipCode(31000);
//			userMember29.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0689012345");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember29);
//			spaceOne.getUsers().add(userMember29);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember30 = new User(true);
//			userMember30.getAccount().setLogin("sarah.martin@email.com");
//			userMember30.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember30.getAccount().setRole(Role.Adherent);
//			userMember30.getLinkedSpaces().add(spaceOne);
//			userMember30.getProfile().getCivility().setForename("Sarah");
//			userMember30.getProfile().getCivility().setName("Martin");
//			userMember30.getProfile().getCivility().setBirthday(new Date());
//			userMember30.getProfile().getCivility().setSex(Sex.Femme);
//			userMember30.getProfile().getAdress().setStreet("1 impasse des petits mûrs");
//			userMember30.getProfile().getAdress().setZipCode(31000);
//			userMember30.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0789012345");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember30);
//			spaceOne.getUsers().add(userMember30);
//			spaceDao.updateSpace(spaceOne);
//
//			User userMember31 = new User(true);
//			userMember31.getAccount().setLogin("kevin.leroux@email.com");
//			userMember31.getAccount().setPassword(DefaultConfig.DEFAULT_PASSWORD);
//			userMember31.getAccount().setRole(Role.Adherent);
//			userMember31.getLinkedSpaces().add(spaceOne);
//			userMember31.getProfile().getCivility().setForename("Kevin");
//			userMember31.getProfile().getCivility().setName("Leroux");
//			userMember31.getProfile().getCivility().setBirthday(new Date());
//			userMember31.getProfile().getCivility().setSex(Sex.Homme);
//			userMember31.getProfile().getAdress().setStreet("1 impasse des petits mûrs");
//			userMember31.getProfile().getAdress().setZipCode(31000);
//			userMember31.getProfile().getAdress().setStreet("Toulouse");
//			userMember.getProfile().getContact().setPhoneNumber("0889012345");
//			userManager.getProfile().getCivility().setProfilePicture("default.jpg");
//			userDao.createUser(userMember31);
//			spaceOne.getUsers().add(userMember31);
//			spaceDao.updateSpace(spaceOne);
//
			/*
			 * Crétion de quelques souscriptions pour la vue allCourses (Calendar)
			 */
			Subscription subscriptionOne = new Subscription(true);
			subscriptionOne.getPrice().setMonthlyPrice((float) 29.99);
			subscriptionOne.setSubscriptionName("BASIC");
			subscriptionOne
					.setDescription("Acces illimité à la salle de sport\n" + "Participation aux cours collectifs\n"
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
			 * Creation de quelques cours pour la vue allCourses (Calendar)
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

			Course c4 = new Course();
			c4.setName("Cours de kickboxing");
			c4.setLinkedSpaceId(6L);
			c4.setCoach(userCoach);
			c4.setStartDate(LocalDateTime.now());
			c4.setEndDate(LocalDateTime.now().plusHours(2));
			c4.setDescription("kickboxing");
			em.persist(c4);

			Course c5 = new Course();
			c5.setName("Cours de yoga");
			c5.setLinkedSpaceId(14L);
			c5.setCoach(userCoach);
			c5.setStartDate(LocalDateTime.now().plusDays(2));
			c5.setEndDate(LocalDateTime.now().plusDays(2).plusHours(1));
			c5.setDescription("yoga");
			em.persist(c5);

			/*
			 * Fin création des cours
			 */

			System.out.println("************************ End of Initializing DB **********************************");
			initialized = true;
		}
	}
}
