package fr.isika.cda.javaee.dao.relations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.ActivityTest;
import fr.isika.cda.javaee.entity.relations.Planning;

@Stateless
public class PlanningDao implements IPlanningDao {

	@PersistenceContext
	private EntityManager em;

//*************************************************************
	@Override
	public Long createPlanning(Planning planningToCreate) {
		for (ActivityTest activityTest : planningToCreate.getActivitiesList()) {
			em.persist(activityTest);
		}
		em.persist(planningToCreate);
		return planningToCreate.getPlanningId();
	}

	@Override
	public Planning getPlanningById(Long planningId) {
		return em.find(Planning.class, planningId);
	}

}
