package fr.isika.cda.javaee.dao.subscription;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.subscription.Subscription;

/**
 * Manage the persistence of Subscription objects in MySQL DB.
 * 
 * @author Charef Thibault
 *
 */
@Stateless
public class SubscriptionDao implements IDaoSubscription {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createSubscription(Subscription subscriptionToCreate) {
		em.persist(subscriptionToCreate.getPrice());
		em.persist(subscriptionToCreate);
		return subscriptionToCreate.getSubscriptionId();
	}

	@Override
	public boolean deleteSubscription(Long subscriptionToDeleteId) {
		Subscription subscriptionToDelete = getSubscriptionById(subscriptionToDeleteId);
		if (subscriptionToDelete != null) {
			em.remove(subscriptionToDelete);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Subscription getSubscriptionById(Long SubscriptionToGetId) {
		return em.createQuery("SELECT u FROM Subscription u WHERE u.subscriptionId = :subscriptionIdParam",
				Subscription.class).setParameter("subscriptionIdParam", SubscriptionToGetId).getSingleResult();
	}

	@Override
	public Subscription getSubscriptionByName(String SubscriptionToGetName) {
		return em
				.createQuery("SELECT u FROM Subscription u WHERE u.subscriptionName = :subscriptionNameParam",
						Subscription.class)
				.setParameter("subscriptionNameParam", SubscriptionToGetName).getSingleResult();
	}

	@Override
	public List<Subscription> getAllSubscriptions() {
		return em.createQuery("SELECT u FROM Subscription u", Subscription.class).getResultList();
	}

	@Override
	public void updateSubscription(Subscription subscriptionToUpdate) {
		em.merge(subscriptionToUpdate);
	}

}
