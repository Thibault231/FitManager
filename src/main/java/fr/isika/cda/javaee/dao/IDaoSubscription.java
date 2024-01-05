package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.subscription.Subscription;

public interface IDaoSubscription {

	Long createSubscription(Subscription subscriptionToCreate);

	boolean deleteSubscription(Long SubscriptionToDeleteSubscriptionId);

	Subscription getSubscriptionById(Long SubscriptionToGetId);

	Subscription getSubscriptionByName(String SubscriptionToGetName);

	List<Subscription> getAllSubscriptions();

}
