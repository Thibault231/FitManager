package fr.isika.cda.javaee.dao.relations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.Booking;

/**
 * Manage the persistence of Booking objects in MySQL DB.
 * 
 * @author Thibault Charef
 *
 */
@Stateless
public class BookingDao implements IBookingDao {

	@PersistenceContext
	private EntityManager em;

//*************************************************************
	@Override
	public Long createBooking(Booking bookingToCreate) {
		em.persist(bookingToCreate);
		return bookingToCreate.getBookingId();
	}

	@Override
	public void cancelBooking(Long bookingToCancelId) {
		try {
			Booking bookingToDelete = getBookingingById(bookingToCancelId);
			em.remove(bookingToDelete);
		} catch (NoResultException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public Booking getBookingingById(Long bookingId) {
		return em.createQuery("SELECT b FROM Booking b WHERE b.bookingId = :bookingIdParam", Booking.class)
				.setParameter("bookingIdParam", bookingId).getSingleResult();
	}

	@Override
	public List<Booking> getAllBookings() {
		List<Booking> bookingsList = em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
		return bookingsList;
	}

	@Override
	public List<Booking> getAllBookingsOfMember(Long memberId) {
		return em.createQuery("SELECT b FROM Booking b WHERE b.member.userId =" + memberId, Booking.class)
				.getResultList();
	}

	@Override
	public List<Booking> getAllBookingsOfCoach(Long coachId) {
		return em.createQuery("SELECT b FROM Booking b WHERE b.coach.userId =" + coachId, Booking.class)
				.getResultList();
	}

}
