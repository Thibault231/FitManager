package fr.isika.cda.javaee.dao.relations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.Planning;

public class PlanningDao implements IPlanningDao {
	@PersistenceContext
	private EntityManager em;

//*************************************************************
	@Override
	public Long createPlanning(Planning planningToCreate) {
		em.persist(planningToCreate);
		return planningToCreate.getPlanningId();
	}

	@Override
	public Planning getPlanningById(Long planningId) {
		return em.find(Planning.class, planningId);
	}

}
