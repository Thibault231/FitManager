package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.subscription.Subscription;

/**
 * SubscriptionViewModel for Subscription controller
 * 
 * @author Nene
 *
 */
public class SubscriptionViewModel {

	private Long subscriptionId;

	private Subscription subscription;

	private Subscription selectedSubscription;

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

	public Subscription getSelectedSubscription() {
		return selectedSubscription;
	}

	public void setSelectedSubscription(Subscription selectedSubscription) {
		this.selectedSubscription = selectedSubscription;
	}

}
