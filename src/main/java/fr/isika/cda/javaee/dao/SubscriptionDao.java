package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.subscription.Membership;
import fr.isika.cda.javaee.entity.subscription.Subscription;

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

	@Override
	public void updateSubscription(Subscription subscriptionToUpdate) {
		em.merge(subscriptionToUpdate);
	}

}
