package fr.isika.cda.javaee.dao.plateform;

import java.util.List;

import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;

/**
 * Manage the persistence of Activity objects.
 * 
 * @author Thibault
 *
 */
public interface IDaoActivity {
	/**
	 * Create a new activity in the database, using an activityForm object.
	 * 
	 * @param activityForm (: ActivityForm)
	 * @return Activity's id (:Long)
	 */
	public Long createACtivity(ActivityForm activityForm);

	/**
	 * Get a specific Activity from the database, using it's Id. Return the course
	 * if it exists, null otherwise.
	 * 
	 * @param activityToGetId (:Long)
	 ** @return Activity's id (:Long)
	 */
	public Activity getActivityById(Long activityToGetId);

	/**
	 * Update an activity in the DB, using the updated course object.
	 * 
	 * @param activityToUpdate (:Activity)
	 */
	public void updateActivity(Activity activityToUpdate);

	/**
	 * Get all the activities of a specific space, linked to a specific coach, using
	 * the space Id and coach Id.
	 * 
	 * @return list of activities (:List<Activity>)
	 */
	public List<Activity> getAllActivities();

	/**
	 * Hard delete an activity from the DB, using the course id.
	 * 
	 * @param activityToDeleteId (:Long)
	 */
	public void deleteActivity(Long activityToDeleteId);

}