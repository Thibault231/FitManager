package fr.isika.cda.javaee.dao.plateform;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;

/**
 * Manage the persistence of Activity objects in MySQL DB.
 * 
 * @author Charef
 *
 */
@Stateless
public class ActivityDao implements IDaoActivity {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long createACtivity(ActivityForm activityForm) {
		Activity activity = new Activity();
		activity.setName(activityForm.getName());
		entityManager.persist(activity);
		return activity.getId();
	}

	@Override
	public Activity getActivityById(Long id) {
		return entityManager.find(Activity.class, id);
	}

	@Override
	public void updateActivity(Activity activity) {
		entityManager.merge(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		return entityManager.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
	}

	@Override
	public void deleteActivity(Long id) {
		entityManager.remove(entityManager.find(Activity.class, id));
	}

}
