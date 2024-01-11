package fr.isika.cda.javaee.presentation.controller.subscription;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
@ViewScoped
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
	public String createSubscription() {
		Long spaceid = SessionUtils.getSpaceIdFromSession();
		Space currentSpace = spaceDao.getSpaceWithSubscription(spaceid);
		Subscription subscriptionToCreate = subscriptionViewModel.getSubscription();
		subscriptionDao.createSubscription(subscriptionToCreate);
		currentSpace.getSubscriptions().add(subscriptionToCreate);
		spaceDao.updateSpace(currentSpace);
		return "ManagerSpaceDashBoard";
	}

	public List<Subscription> getAllActiveSubscription() {
		Long spaceid = SessionUtils.getSpaceIdFromSession();
		Space currentSpace = spaceDao.getSpaceWithSubscription(spaceid);
		List<Subscription> subscriptionsList = currentSpace.getSubscriptions();
		return subscriptionsList;
	}

	public Subscription getsubscriptionById(Long subscritptionId) {
		return subscriptionDao.getSubscriptionById(subscritptionId);
	}

	public Subscription getSubscriptionByName(String subscriptionName) {
		return subscriptionDao.getSubscriptionByName(subscriptionName);
	}

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

	public String deleteSubscription(Long subscriptionToDeleteId) {
		subscriptionDao.deleteSubscription(subscriptionToDeleteId);
		return "ManagerDashBoard";
	}

}
