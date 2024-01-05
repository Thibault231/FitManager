package fr.isika.cda.javaee.dao.relations;

import java.util.List;

import fr.isika.cda.javaee.entity.relations.ActivityTest;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.relations.Planning;
import fr.isika.cda.javaee.entity.relations.Schedulde;

public interface IRelationsDao {

	/**
	 * Add a new planning in the database.
	 * 
	 * @param planningToCreate (:Planning)
	 * @return The Id of the created Planning (:Long)
	 */
	Long createPlanning(Planning planningToCreate);

	/**
	 * Return a specific Planning from the database if it exists or null otherwise,
	 * using it's Id.
	 * 
	 * @param planningId (:Long)
	 * @return the planning to get (:Planning)
	 */
	Planning getPlanningById(Long planningId);

	/**
	 * Add a new Schedulde in the database.
	 * 
	 * @param scheduldeToCreate (:Planning)
	 * @return The Id of the created Schedulde (:Long)
	 */
	Long createSchedulde(Schedulde scheduldeToCreate);

	/**
	 * Return a specific Schedule from the database if it exists or null otherwise,
	 * using it's Id.
	 * 
	 * @param scheduldeId (:Long)
	 * @return the schedulde to get (:Schedulde)
	 */
	Schedulde getScheduldeById(Long scheduldeId);

	/**
	 * Add a new Booking in the database.
	 * 
	 * @param bookingToCreate (:Planning)
	 * @return The Id of the created Booking (:Long)
	 */
	Long createBooking(Booking bookingToCreate);

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
