package fr.isika.cda.javaee.presentation.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoSubscription;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.subscription.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;

@ManagedBean
public class SubscriptionController {

	@Inject
	private IDaoSubscription subscriptionDao;

	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();;

	@PostConstruct
	public void init() {
		this.subscriptionViewModel = new SubscriptionViewModel();
	}

//*****************************************************************************
	public String createSubscription() {
		subscriptionDao.createSubscription(subscriptionViewModel.getSubscription());
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
