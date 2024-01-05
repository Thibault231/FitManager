package fr.isika.cda.javaee.dao.relations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.relations.Booking;

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

//*************************************************************
	@Override
	public Booking getBookingingById(Long bookingId) {
		return em.find(Booking.class, bookingId);
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
