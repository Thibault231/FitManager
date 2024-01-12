package fr.isika.cda.javaee.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Sex;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;

/**
 * Service for space management between userDao and Controllers
 * 
 * @author Thibault
 *
 */
@Stateless
public class SpaceServices {
	@Inject
	private IDaoSpace spaceDao;

	/**
	 * Update all the attributes of all the dependencies of a space and persit them.
	 * 
	 * @param spaceToUpdate (:Space)
	 */
	public void updateUserOnPlateform(Space spaceToUpdate) {
		Space currentSpace = spaceDao.getSpaceById(SessionUtils.getSpaceIdFromSession());
		// Administrative dependency
		if (spaceToUpdate.getInfos().getAdministrative().getAddress() != null) {
			String newAdress = spaceToUpdate.getInfos().getAdministrative().getAddress();
			currentSpace.getInfos().getAdministrative().setAddress(newAdress);
		}
		String newSiret = spaceToUpdate.getInfos().getAdministrative().getSiret();
		currentSpace.getInfos().getAdministrative().setSiret(newSiret);

		// Configuration dependency
		if (spaceToUpdate.getInfos().getConfiguration().getFitnessName() != null) {
			String newName = spaceToUpdate.getInfos().getConfiguration().getFitnessName();
			currentSpace.getInfos().getConfiguration().setFitnessName(newName);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getSlogan() != null) {
			String newSlogan = spaceToUpdate.getInfos().getConfiguration().getSlogan();
			currentSpace.getInfos().getConfiguration().setSlogan(newSlogan);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getLogo() != null) {
			String newLogo = spaceToUpdate.getInfos().getConfiguration().getLogo();
			currentSpace.getInfos().getConfiguration().setLogo(newLogo);
		}
		String newMainText = spaceToUpdate.getInfos().getConfiguration().getMainText();
		currentSpace.getInfos().getConfiguration().setMainText(newMainText);
		String newMainPicture = spaceToUpdate.getInfos().getConfiguration().getMainPicture();
		currentSpace.getInfos().getConfiguration().setMainPicture(newMainPicture);
		String newCarrouselOne = spaceToUpdate.getInfos().getConfiguration().getCarrouselOne();
		currentSpace.getInfos().getConfiguration().setCarrouselOne(newCarrouselOne);
		String newCarrouselTwo = spaceToUpdate.getInfos().getConfiguration().getCarrouselTwo();
		currentSpace.getInfos().getConfiguration().setCarrouselTwo(newCarrouselTwo);
		String newCarrouselThree = spaceToUpdate.getInfos().getConfiguration().getCarrouselThree();
		currentSpace.getInfos().getConfiguration().setCarrouselThree(newCarrouselThree);
		String newWelcomeText = spaceToUpdate.getInfos().getConfiguration().getWelcomeText();
		currentSpace.getInfos().getConfiguration().setWelcomeText(newWelcomeText);

		// Style dependency
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getMainColor() != null) {
			String newMainColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getMainColor();
			currentSpace.getInfos().getConfiguration().getStyle().setMainColor(newMainColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getSecondColor() != null) {
			String newSecondColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getSecondColor();
			currentSpace.getInfos().getConfiguration().getStyle().setSecondColor(newSecondColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getThirdcolor() != null) {
			String newThirdColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getThirdcolor();
			currentSpace.getInfos().getConfiguration().getStyle().setThirdcolor(newThirdColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getFont() != null) {
			String newFont = spaceToUpdate.getInfos().getConfiguration().getStyle().getFont();
			currentSpace.getInfos().getConfiguration().getStyle().setFont(newFont);
		}
		// Persist update
		spaceDao.updateSpace(currentSpace);
	}

	/**
	 * Update style attributes of a space and persit them.
	 * 
	 * @param spaceToUpdate (:Space)
	 */
	public void updateUserOnIndex(Space spaceToUpdate) {
		Space currentSpace = spaceDao.getSpaceById(SessionUtils.getSpaceIdFromSession());

		// Configuration dependency
		String newMainText = spaceToUpdate.getInfos().getConfiguration().getMainText();
		currentSpace.getInfos().getConfiguration().setMainText(newMainText);
		String newMainPicture = spaceToUpdate.getInfos().getConfiguration().getMainPicture();
		currentSpace.getInfos().getConfiguration().setMainPicture(newMainPicture);
		String newCarrouselOne = spaceToUpdate.getInfos().getConfiguration().getCarrouselOne();
		currentSpace.getInfos().getConfiguration().setCarrouselOne(newCarrouselOne);
		String newCarrouselTwo = spaceToUpdate.getInfos().getConfiguration().getCarrouselTwo();
		currentSpace.getInfos().getConfiguration().setCarrouselTwo(newCarrouselTwo);
		String newCarrouselThree = spaceToUpdate.getInfos().getConfiguration().getCarrouselThree();
		currentSpace.getInfos().getConfiguration().setCarrouselThree(newCarrouselThree);
		String newWelcomeText = spaceToUpdate.getInfos().getConfiguration().getWelcomeText();
		currentSpace.getInfos().getConfiguration().setWelcomeText(newWelcomeText);

		// Style dependency
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getMainColor() != null) {
			String newMainColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getMainColor();
			currentSpace.getInfos().getConfiguration().getStyle().setMainColor(newMainColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getSecondColor() != null) {
			String newSecondColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getSecondColor();
			currentSpace.getInfos().getConfiguration().getStyle().setSecondColor(newSecondColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getThirdcolor() != null) {
			String newThirdColor = spaceToUpdate.getInfos().getConfiguration().getStyle().getThirdcolor();
			currentSpace.getInfos().getConfiguration().getStyle().setThirdcolor(newThirdColor);
		}
		if (spaceToUpdate.getInfos().getConfiguration().getStyle().getFont() != null) {
			String newFont = spaceToUpdate.getInfos().getConfiguration().getStyle().getFont();
			currentSpace.getInfos().getConfiguration().getStyle().setFont(newFont);
		}
		// Persist update
		spaceDao.updateSpace(currentSpace);
	}
}
