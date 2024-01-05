package fr.isika.cda.javaee.dao.relations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.relations.Planning;
import fr.isika.cda.javaee.entity.relations.Schedulde;

@Stateless
public class RelationsDao implements IRelationsDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createPlanning(Planning planningToCreate) {
		em.persist(planningToCreate);
		return planningToCreate.getPlanningId();
	}

	@Override
	public Long createSchedulde(Schedulde scheduldeToCreate) {
		em.persist(scheduldeToCreate);
		return scheduldeToCreate.getScheduldeId();
	}

	@Override
	public Long createBooking(Booking bookingToCreate) {
		em.persist(bookingToCreate);
		return bookingToCreate.getBookingId();
	}

}
