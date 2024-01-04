package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Administrative;
import fr.isika.cda.javaee.entity.spaces.Color;
import fr.isika.cda.javaee.entity.spaces.Space;

@Named
@SessionScoped
public class SpaceDetailsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6047443524460470252L;

	@Inject
	private IDaoSpace spaceDao;

	private Space space;

	public String showSpaceDetails() {

		// 1 - Récupérer la valeur du param "spaceId"
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String spaceIdParam = params.get("spaceId");
		Long spaceId = Long.valueOf(spaceIdParam);

		// 2 - aller chercher l'objet Salle par cet id (en bdd)
		space = spaceDao.getSpaceById(spaceId);

		// 3 - afficher la page Salle (Space) avec les données qu'on vient de trouver
		System.out.println(space);

		// 4 - rediriger vers la page du space en question
		return "Space.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	public List<Space> getAllSpaces() {
		String a = Color.getColorsTemplate().get("red");
		return spaceDao.getAllSpace();
	}

	public Space getSpace() {
		return space;
	}

	// A supprimer plus tard mais c'est pour créer un space de test
	public void creerSpaceDeTest() {
		Administrative a = new Administrative();
		a.setSiret("10000");
		a.setSiren("263-10000");

		Space s = new Space();
//		s.setName("test space");
//		s.setAdministrative(a);

		spaceDao.saveSpaceAndRelations(s);
	}
}
