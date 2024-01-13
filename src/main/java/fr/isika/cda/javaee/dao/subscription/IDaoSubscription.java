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
	/**
	 * Create a new subscription in the database, using a Subscription object.
	 * 
	 * @param subscriptionToCreate (:Subscription)
	 * @return subscription's id (:Long)
	 */
	Long createSubscription(Subscription subscriptionToCreate);

	/**
	 * Hard delete a subscription from the DB, using the course id.
	 * 
	 * @param subscriptionToDeleteId (:Long)
	 * @return (:boolean)
	 */
	boolean deleteSubscription(Long subscriptionToDeleteId);

	/**
	 * Get a specific subscription from the database, using it's Id attribute.
	 * Return the course if it exists, null otherwise.
	 * 
	 * @param SubscriptionToGetId (:Long)
	 * @return subscription to get (:Subscription)
	 */
	Subscription getSubscriptionById(Long SubscriptionToGetId);

	/**
	 * Get a specific subscription from the database, using it's name attribute.
	 * Return the course if it exists, null otherwise.
	 * 
	 * @param SubscriptionToGetName (:String)
	 * @return subscription to get (:Subscription)
	 */
	Subscription getSubscriptionByName(String SubscriptionToGetName);

	/**
	 * Get all the subscriptions of a specific space, registered in the DB, using
	 * the space Id.
	 * 
	 * @return list of subscriptions (:List<Subscription>)
	 */
	List<Subscription> getAllSubscriptions();

	/**
	 * Update a Subscription in the DB, using the updated Subscrption object.
	 * 
	 * @param subscriptionToUpdate (:Subscription)
	 */
	void updateSubscription(Subscription subscriptionToUpdate);

}
