package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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

	@Inject
	private IDaoSubscription subscriptionDao;

	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();;

	@PostConstruct
	public void init() {
		this.subscriptionViewModel = new SubscriptionViewModel();
	}

//*****************************************************************************
	public String createSubscription() {
		Subscription subscriptionToCreate = subscriptionViewModel.getSubscription();
		subscriptionDao.createSubscription(subscriptionToCreate);
		return "ManagerDashBoard";
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

}
