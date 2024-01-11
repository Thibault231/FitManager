package fr.isika.cda.javaee.dao.subscription;

import java.util.List;

import fr.isika.cda.javaee.entity.subscription.Subscription;

/**
 * Manage the persistence of Subscription objects.
 * 
 * @author Nene Thibault
 *
 */
public interface IDaoSubscription {

	Long createSubscription(Subscription subscriptionToCreate);

	boolean deleteSubscription(Long SubscriptionToDeleteSubscriptionId);

	Subscription getSubscriptionById(Long SubscriptionToGetId);

	Subscription getSubscriptionByName(String SubscriptionToGetName);

	List<Subscription> getAllSubscriptions();

	void updateSubscription(Subscription subscriptionToUpdate);

}
