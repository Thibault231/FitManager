package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

public interface IDaoSpace {

	/**
	 * Add a new Space in the database.
	 * 
	 * @param spaceToCreate (:Space)
	 * @return Created space Id(:Long)
	 */
	Long createSpace(Space spaceToCreate);

	/**
	 * Hard delete of a space from the Db, using it's id.
	 * 
	 * @param SpaceToDeleteId
	 * @return True if success, false otherwise (: boolean)
	 */
	boolean deleteSpace(Long SpaceToDeleteId);

	/**
	 * Return a Space from the database, using it's Id, if it exist or null
	 * otherwise.
	 * 
	 * @param spaceToGetId
	 * @return the space to get (:Space)
	 */
	Space getSpaceById(Long spaceToGetId);

	/**
	 * Return a Space from the database, using it's FitnessName, if it exist or null
	 * otherwise.
	 * 
	 * @param spaceToGetName (:String)
	 * @return the space to get (:Space)
	 */
	Space getSpaceByName(String spaceToGetName);

	/**
	 * Return the list of all Spaces of the plateform.
	 * 
	 * @return the list of spaces to get (:Lis<Space>)
	 */
	List<Space> getAllSpace();

}
