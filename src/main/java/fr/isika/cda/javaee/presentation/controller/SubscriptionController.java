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
import fr.isika.cda.javaee.entity.subscription.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;

@Named
@ViewScoped
public class SubscriptionController implements Serializable {

	private static final long serialVersionUID = 8496614779097793938L;

	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();;

	@PostConstruct
	public void init() {
		this.subscriptionViewModel = new SubscriptionViewModel();
	}

//*****************************************************************************
	public String createSubscription() {
		Subscription subscriptionToCreate = subscriptionViewModel.getSubscription();
		subscriptionDao.createSubscription(subscriptionToCreate);
		return "SpaceAccueilPersonnalisation";
	}

	public SubscriptionViewModel getSubscriptionViewModel() {
		return subscriptionViewModel;
	}

	public void setSubscriptionViewModel(SubscriptionViewModel subscriptionViewModel) {
		this.subscriptionViewModel = subscriptionViewModel;
	}

	public String deleteSubscription(Long subscriptionToDeleteId) {
		subscriptionDao.deleteSubscription(subscriptionToDeleteId);
		return "ManagerDashBoard";
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
