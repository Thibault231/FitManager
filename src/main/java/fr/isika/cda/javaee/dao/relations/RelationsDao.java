package fr.isika.cda.javaee.dao.relations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.relations.Planning;
import fr.isika.cda.javaee.entity.relations.Schedulde;
import fr.isika.cda.javaee.entity.users.User;

@Stateless
public class RelationsDao implements IRelationsDao {

	@PersistenceContext
	private EntityManager em;

//*************************************************************
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

//*************************************************************
	@Override
	public Booking getBookingingById(Long bookingId) {
		return em.find(Booking.class, bookingId);
	}

	@Override
	public Planning getPlanningById(Long planningId) {
		return em.find(Planning.class, planningId);
	}

	@Override
	public Schedulde getScheduldeById(Long scheduldeId) {
		return em.find(Schedulde.class, scheduldeId);
	}

	@Override
	public List<Booking> getAllBookings() {
		Query queryOne = em.createQuery("SELECT b FROM Booking b");
		List<Booking> bookingsList = queryOne.getResultList();
		return bookingsList;
	}

	@Override
	public List<Booking> getAllBookingsOfMember(Long memberId) {
		Query queryOne = em.createQuery("SELECT b FROM Booking b WHERE b.member.userId =" + memberId);
		List<Booking> bookingsList = queryOne.getResultList();
		return bookingsList;
	}

	@Override
	public List<Booking> getAllBookingsOfCoach(Long coachId) {
		Query queryOne = em.createQuery("SELECT b FROM Booking b WHERE b.coach.userId =" + coachId);
		List<Booking> bookingsList = queryOne.getResultList();
		return bookingsList;
	}

}
