package fr.isika.cda.javaee.dao.relations;

import fr.isika.cda.javaee.entity.relations.Planning;

public interface IPlanningDao {
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

}
