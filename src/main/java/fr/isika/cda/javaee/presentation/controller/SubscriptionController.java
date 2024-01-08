package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoSubscription;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.subscription.Membership;
import fr.isika.cda.javaee.entity.subscription.Subscription;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;

@Named
@ViewScoped
public class SubscriptionController implements Serializable {

	private static final long serialVersionUID = 8496614779097792338L;

	@Inject
	private IDaoSubscription subscriptionDao;

	@Inject
	private IDaoUser userDao;

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
		Subscription subscriptionToCreate = subscriptionViewModel.getSubscription();
		subscriptionDao.createSubscription(subscriptionToCreate);
		return "SpaceAccueilPersonnalisation";
	}

	public List<Subscription> getAllActiveSubscription() {
		List<Subscription> subscriptionsList = subscriptionDao.getAllSubscriptions();
		return subscriptionsList;
	}

	public Subscription getsubscription(Long subscritptionId) {
		return subscriptionDao.getSubscriptionById(subscritptionId);
	}

	public Subscription getSubscription(String subscriptionName) {
		return subscriptionDao.getSubscriptionByName(subscriptionName);
	}

	/**
	 * Create a new subscription and link it to the active member, using the session
	 * parameters. Use this method only if the member is logged.
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
		return "Test-AdherentDashboard";
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
