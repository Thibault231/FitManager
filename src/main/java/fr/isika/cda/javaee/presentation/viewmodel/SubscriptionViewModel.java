package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.subscription.Subscription;

public class SubscriptionViewModel {

	private Long subscriptionId;

	private Subscription subscription;

//******************************************************
	public SubscriptionViewModel() {
		this.subscription = new Subscription(true);
	}

//******************************************************
	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}
