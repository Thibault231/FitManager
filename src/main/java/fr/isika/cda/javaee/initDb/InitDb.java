package fr.isika.cda.javaee.initDb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.spaces.Space;

@Startup
@Singleton
public class InitDb {
	private static boolean initialized = false;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void init() {
		if (!initialized) {
			System.out.println("***************************** Initialize DB **********************************");
			em.persist(new Space(true));
// em.persist(new GymInstance("1st GYM", "1 st 99999 Town"));
// em.persist(new GymInstance("2nd GYM", "12 av 99999 Town"));
// em.persist(new GymInstance("3rd GYM", "12 av 99999 Town"));
// 
//
// em.persist(new Coach(em.find(GymInstance.class, 1L), new Account("coach@test.fr", "test666", new Profile("prenom","nom"))));
// em.persist(new Coach(em.find(GymInstance.class, 1L), new Account("coach_manager@test.fr", "test666", new Profile("prenom","nom"))));
// em.persist(new Coach(em.find(GymInstance.class, 1L), new Account("athlete_coach_manager@test.fr", "test666", new Profile("prenom","nom"))));
// 
// 
// em.persist(new Athlete(em.find(GymInstance.class, 1L), new Account("athlete@test.fr", "test666", new Profile("prenom","nom")),100));
// em.persist(new Athlete(em.find(GymInstance.class, 2L), em.find(Account.class, 3L), 150));
// 
//
// em.persist(new Manager(em.find(GymInstance.class, 1L), new Account("manager@test.fr", "test666", new Profile("prenom","nom"))));
// em.persist(new Manager(em.find(GymInstance.class, 2L), em.find(Account.class, 2L)));
// em.persist(new Manager(em.find(GymInstance.class, 3L), em.find(Account.class, 3L)));
			System.out.println("************************ End of Initializing DB **********************************");
			initialized = true;
		}
	}
}
