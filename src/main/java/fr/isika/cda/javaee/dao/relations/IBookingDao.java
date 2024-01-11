package fr.isika.cda.javaee.dao.relations;

import java.util.List;

import fr.isika.cda.javaee.entity.relations.Booking;

public interface IBookingDao {

	/**
	 * Add a new Booking in the database.
	 * 
	 * @param bookingToCreate (:Planning)
	 * @return The Id of the created Booking (:Long)
	 */
	Long createBooking(Booking bookingToCreate);

	void cancelBooking(Long bookingToCancelId);

	/**
	 * Return all the Bookings to come, of the database.
	 * 
	 * @return the list of bookings (:List(Booking))
	 */
	List<Booking> getAllBookings();

	/**
	 * Return all the Bookings to come related to a specific Member, using the
	 * member's Id.
	 * 
	 * @param memberId(:Long)
	 * @return the list of bookings (:List(Booking))
	 * @return
	 */
	List<Booking> getAllBookingsOfMember(Long memberId);

	/**
	 * Return all the Bookings to come related to a specific Coach, using the
	 * coach's Id.
	 * 
	 * @param coachId (:Long)
	 * @return the list of bookings (:List(Booking))
	 * 
	 */
	List<Booking> getAllBookingsOfCoach(Long coachId);

	/**
	 * Return a specific Booking from the database if it exists or null otherwise,
	 * using it's Id.
	 * 
	 * @param bookingId (:Long)
	 * @return the booking to get (:Booking)
	 */
	Booking getBookingingById(Long bookingId);
}
