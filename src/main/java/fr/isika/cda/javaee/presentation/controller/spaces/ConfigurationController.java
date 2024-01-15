package fr.isika.cda.javaee.presentation.controller.spaces;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.ConfigurationViewModel;

/**
 * Manage Configuration objects in views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
@Named("conf")
@ViewScoped
public class ConfigurationController implements Serializable {

	private static final long serialVersionUID = 8496614779097793940L;

	@Inject
	private IDaoSpace spaceDao;

	private ConfigurationViewModel confViewModel;

	public IDaoSpace getSpaceDao() {
		return spaceDao;
	}

	public void setSpaceDao(IDaoSpace spaceDao) {
		this.spaceDao = spaceDao;
	}

	public ConfigurationViewModel getConfViewModel() {
		return confViewModel;
	}

	public void setConfViewModel(ConfigurationViewModel confViewModel) {
		this.confViewModel = confViewModel;
	}

//**********************************************************
	@PostConstruct
	private void init() {
		confViewModel = new ConfigurationViewModel();
	}

//**********************************************************
	/**
	 * Return a list of colors for space personalization.
	 * 
	 * @return a map of color (: Map<String, String>)
	 */
	public Map<String, String> getColorForConfiguration() {
		Map<String, String> colorsMap = new HashMap<>();
		colorsMap.put("red", "##be2e14");
		colorsMap.put("blue", "##be2e14");
		colorsMap.put("orange", "#f0913c");
		colorsMap.put("green", "#4ebe1e");
		return colorsMap;
	}

	/**
	 * Update the configuration dependency of a space, specificly.
	 * 
	 * @return url (:String)
	 */
	public String updateSpace() {
		// Construire l'objet de l'espace actuel
		Space currentSpace = spaceDao.getSpaceById(SessionUtils.getSpaceIdFromSession());

		// transférer les modifications du confviewmodel dans l'espace actuel

		// persister les changements de l'espace
		spaceDao.updateSpace(currentSpace);

		// retourne à l'accueil de la slle courante
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		return "TemplateFitness.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}
}
