package fr.isika.cda.javaee.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.subscription.Subscription;

public class SubscriptionDao implements IDaoSubscription {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createSubscription(Subscription subscriptionToCreate) {
		em.persist(subscriptionToCreate.getPrice().getMonthlyPrice());
		em.persist(subscriptionToCreate.getSubscriptionName());
		em.persist(subscriptionToCreate.getDescription());
		em.persist(subscriptionToCreate.getEngagement());
		em.persist(subscriptionToCreate.getPromotion());
		em.persist(subscriptionToCreate);
		return subscriptionToCreate.getSubscriptionId();
	}

	@Override
	public boolean deleteSubscription(Long SubscriptionToDeleteId) {
		Subscription subscriptionToDelete = em.find(Subscription.class, SubscriptionToDeleteId);
		if (subscriptionToDelete != null) {
			em.remove(SubscriptionToDeleteId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Subscription getSubscriptionById(Long SubscriptionToGetId) {
		return em.find(Subscription.class, SubscriptionToGetId);
	}

	@Override
	public Subscription getSubscriptionByName(String SubscriptionToGetName) {
		return em.createQuery("SELECT u FROM Subscription u WHERE u.isActive = 1", Subscription.class)
				.getSingleResult();
	}

	@Override
	public List<Subscription> getAllSubscriptions() {
		return em.createQuery("SELECT u FROM Subscription u", Subscription.class).getResultList();
	}

}
