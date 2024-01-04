package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

public interface IDaoSpace {

	/**
	 * 
	 * @param space
	 * @return
	 */
	Long createSpace(Space spaceToCreate);

	Long saveSpaceAndRelations(Space space);

	boolean deleteSpace(Long SpaceToDeleteIdSubscription);

	Space getSpaceByIdSubscription(Long spaceToGetIdSubscription);

	Space getSpaceByName(String spaceToGetName);

	List<Space> getAllSpace();

	Space getSpaceById(Long spaceId);
}
