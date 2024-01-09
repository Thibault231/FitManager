package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;

@Stateless
public class ActivityDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long createACtivity(ActivityForm activityForm) {
		Activity activity = new Activity();
		activity.setName(activityForm.getName());
		entityManager.persist(activity);
		return activity.getId();
	}

	public Activity getActivityById(Long id) {
		return entityManager.find(Activity.class, id);
	}
	
	public void updateActivity(Activity activity) {
        entityManager.merge(activity);
    }

	public List<Activity> getAllActivities() {
		return entityManager.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
	}

	public void deleteActivity(Long id) {
		entityManager.remove(entityManager.find(Activity.class, id));
	}

	
	
}
