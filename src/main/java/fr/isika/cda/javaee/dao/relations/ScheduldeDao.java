package fr.isika.cda.javaee.dao.relations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.Schedulde;

public class ScheduldeDao implements IScheduldeDao {
	@PersistenceContext
	private EntityManager em;

//*************************************************************
	@Override
	public Long createSchedulde(Schedulde scheduldeToCreate) {
		em.persist(scheduldeToCreate);
		return scheduldeToCreate.getScheduldeId();
	}

	@Override
	public Schedulde getScheduldeById(Long scheduldeId) {
		return em.find(Schedulde.class, scheduldeId);
	}
}
