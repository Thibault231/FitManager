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

	private static final long serialVersionUID = -6047443524460470252L;

	@Inject
	private IDaoSpace spaceDao;

	@Inject
	private IDaoSubscription subscriptionDao;

	private Subscription subscription;

	private Space space;

//**********************************************************
	public Space getSpace() {
		return space;
	}

	public Subscription getSubscription() {
		return subscription;
	}

//**********************************************************	
	/**
	 * Show all details of the current spaces object with all it's dependencies'
	 * attributes.</br>
	 * <b> Use this method for space only </b>
	 * 
	 * @return url (String)
	 */
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

	/**
	 * Return all the spaces of the plateform.
	 * 
	 * @return a list of spaces (:List<Space>)
	 */
	public List<Space> getAllSpaces() {
		return spaceDao.getAllSpace();
	}

	/**
	 * Return all the attributes of the current space's subscriptions.</br>
	 * <b> Use this method for space only </b>
	 * 
	 * @return url (String)
	 */
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

	/**
	 * Return all the subscriptions of the current space.</br>
	 * <b> Use this method for space only </b>
	 * 
	 * @return list of subscriptions (:List<Subscription>)
	 */
	public List<Subscription> getAllSubscriptions() {
		// String a = Color.getColorsTemplate().get("red");
		return subscriptionDao.getAllSubscriptions();
	}

}
