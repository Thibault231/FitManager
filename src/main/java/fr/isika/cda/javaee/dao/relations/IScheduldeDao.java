package fr.isika.cda.javaee.dao.relations;

import fr.isika.cda.javaee.entity.relations.Schedulde;

public interface IScheduldeDao {
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
}
