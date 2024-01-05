package fr.isika.cda.javaee.dao.relations;

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
	 * Add a new Schedulde in the database.
	 * 
	 * @param scheduldeToCreate (:Planning)
	 * @return The Id of the created Schedulde (:Long)
	 */
	Long createSchedulde(Schedulde scheduldeToCreate);

	/**
	 * Add a new Booking in the database.
	 * 
	 * @param bookingToCreate (:Planning)
	 * @return The Id of the created Booking (:Long)
	 */
	Long createBooking(Booking bookingToCreate);

}
