package fr.isika.cda.javaee.presentation.controller.subscription;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.subscription.IDaoSubscription;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.subscription.Subscription;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;

/**
 * Manage Subscription objects in views.
 * 
 * @author Nene Thibault
 *
 */
@Named
@SessionScoped
public class SubscriptionController implements Serializable {

	private static final long serialVersionUID = 8496614779097792338L;

	@Inject
	private IDaoSubscription subscriptionDao;

	@Inject
	private IDaoUser userDao;

	@Inject
	private IDaoSpace spaceDao;

	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();

	@PostConstruct
	public void init() {
		this.subscriptionViewModel = new SubscriptionViewModel();
	}

//*****************************************************************************
	public SubscriptionViewModel getSubscriptionViewModel() {
		return subscriptionViewModel;
	}

	public void setSubscriptionViewModel(SubscriptionViewModel subscriptionViewModel) {
		this.subscriptionViewModel = subscriptionViewModel;
	}

//*****************************************************************************
	/**
	 * Create a new subscription and return on manager's dashboard.
	 * 
	 * @return url (:String)
	 */
	public String createSubscriptionInDashboard() {
		createSubscription();
		return "ManagerSpaceDashBoard";
	}

	/**
	 * Create a new subscription and return on the index page of the space.
	 * 
	 * @return url (:String)
	 */
	public String CreateSubscriptionInIndex() {
		createSubscription();
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		return "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	/**
	 * Create a new subscription from a form and persist it.
	 */
	private void createSubscription() {
		Long spaceid = SessionUtils.getSpaceIdFromSession();
		Space currentSpace = spaceDao.getSpaceWithSubscription(spaceid);
		Subscription subscriptionToCreate = subscriptionViewModel.getSubscription();
		subscriptionDao.createSubscription(subscriptionToCreate);
		currentSpace.getSubscriptions().add(subscriptionToCreate);
		spaceDao.updateSpace(currentSpace);
		subscriptionViewModel.setSubscription(new Subscription(true));
	}

	/**
	 * Delete a subscription from the current space, using the subscription's id.
	 * 
	 * @param subscriptionToDeleteId
	 * @return user's dashboard url (:String)
	 */
	public String deleteSubscription(Long subscriptionToDeleteId) {
		subscriptionDao.deleteSubscription(subscriptionToDeleteId);

		subscriptionViewModel.setSubscription(new Subscription(true));
		return SessionUtils.redirectToDashBoard();
	}

	/**
	 * 
	 * @return a list of subcriptions (:List<Subscription>)
	 */
	public List<Subscription> getAllActiveSubscription() {
		Long spaceid = SessionUtils.getSpaceIdFromSession();
		Space currentSpace = spaceDao.getSpaceWithSubscription(spaceid);
		List<Subscription> subscriptionsList = currentSpace.getSubscriptions();
		return subscriptionsList;
	}

	/**
	 * Return a subscription of the current space, using it's Id.
	 * 
	 * @param subscritptionId (:Long)
	 * @return the subscription to get (:Subscription)
	 */
	public Subscription getsubscriptionById(Long subscritptionId) {
		return subscriptionDao.getSubscriptionById(subscritptionId);
	}

	/**
	 * Return a subscription of the current space, using it's name.
	 * 
	 * @param subscriptionName (:String)
	 * @return the subscription to get (:Subscription)
	 */
	public Subscription getSubscriptionByName(String subscriptionName) {
		return subscriptionDao.getSubscriptionByName(subscriptionName);
	}

	/**
	 * Return the subscription of the current member, using it's id from the
	 * session.<br/>
	 * <b>Use this method with connected member only<b>
	 * 
	 * @return the subscription to get (:Subscription)
	 */
	public Subscription getCurrentMemberSubscription() {
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		return subscriptionDao.getSubscriptionById(currentUser.getCurrentSubScriptionId());
	}

	/**
	 * Check if a member has a subscription in the current space.
	 * 
	 * @return (:Boolean)
	 */
	public boolean hasSubscription() {
		return userDao.getUserById(SessionUtils.getUserIdFromSession()).getCurrentSubScriptionId() != null;
	}

	/**
	 * Create a link between the active member, using the session parameters. Use
	 * this method only if the member is logged.
	 * 
	 * @return url (:String)
	 */
	public String subscribe(Long id) {
		// Récupérer l'adhérent courant depuis a session.
		Long currentUserId = SessionUtils.getUserIdFromSession();
		User currentUser = userDao.getUserByIdWithLinkedSpaces(currentUserId);
		// Lier l'adhérent et la souscription
		currentUser.setCurrentSubScriptionId(id);
		// persistance des données.
		userDao.updateUser(currentUser);
		return "AdherentDashboard?faces-redirect=true";
	}

	/**
	 * Get all attributes of all dependencies of a space and stock them in the
	 * viewmodel to be printed in a form.
	 * 
	 * @return url (:String)
	 */
	public String showSubscriptionDetails() {

		// 1 - Récupérer la valeur du param "spaceId"
		Map<String, String> paramSubscription = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String subscriptionIdParam = paramSubscription.get("subscriptionId");
		Long subscriptionId = Long.valueOf(subscriptionIdParam);

		// 1 -Stocker la souscription dans le viewmodel
		this.subscriptionViewModel.setSubscription(subscriptionDao.getSubscriptionById(subscriptionId));

		// 3 - rediriger vers la page du space en question
		return "Subscription.xhtml?faces-redirect=true&amp;subscriptionId=" + subscriptionId;
	}

	/**
	 * Get the update-subscription form and transfer it to service for update and
	 * persistence.
	 * 
	 * @return url (:String)
	 */
	public String updateSuscription() {
		subscriptionDao.updateSubscription(this.subscriptionViewModel.getSubscription());
		return SessionUtils.redirectToDashBoard();
	}

}
