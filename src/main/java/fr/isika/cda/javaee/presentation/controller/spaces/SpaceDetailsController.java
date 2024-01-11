package fr.isika.cda.javaee.presentation.controller.spaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.subscription.IDaoSubscription;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.subscription.Subscription;

/**
 * Manage Space objects in views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
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
		// String a = Color.getColorsTemplate().get("red");
		return spaceDao.getAllSpace();
	}

	public Space getSpace() {
		return space;
	}

	// A supprimer plus tard mais c'est pour créer un space de test
	public void creerSpaceDeTest() {
		Space s = new Space(true);
		s.getInfos().getConfiguration().setFitnessName("SpaceTest");
		s.getInfos().getAdministrative().setSiret("10000");
		s.getInfos().getAdministrative().setAddress("236-10000");
		spaceDao.createSpace(s);
	}

	@Inject
	private IDaoSubscription subscriptionDao;

	private Subscription subscription;

	public String showSubscriptionDetails() {

		// 1 - Récupérer la valeur du param "spaceId"
		Map<String, String> paramSubscription = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String subscriptionIdParam = paramSubscription.get("subscriptionId");
		Long subscriptionId = Long.valueOf(subscriptionIdParam);

		// 2 - aller chercher l'objet Salle par cet id (en bdd)
		subscription = subscriptionDao.getSubscriptionById(subscriptionId);

		// 3 - afficher la page Salle (Space) avec les données qu'on vient de trouver
		System.out.println(subscription);

		// 4 - rediriger vers la page du space en question
		return "Subscription.xhtml?faces-redirect=true&amp;subscriptionId=" + subscriptionId;
	}

	public List<Subscription> getAllSubscriptions() {
		// String a = Color.getColorsTemplate().get("red");
		return subscriptionDao.getAllSubscriptions();
	}

	public Subscription getSubscription() {
		return subscription;
	}

}
