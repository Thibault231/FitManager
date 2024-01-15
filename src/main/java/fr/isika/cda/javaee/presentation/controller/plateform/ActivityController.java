package fr.isika.cda.javaee.presentation.controller.plateform;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.plateform.IDaoActivity;
import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;

/**
 * Manage Activity objects in views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
@Named
@ViewScoped
public class ActivityController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;

	@Inject
	private IDaoActivity activityDao;

	private ActivityForm activityForm = new ActivityForm();

//******************************************************************************************
	public ActivityForm getActivityForm() {
		return activityForm;
	}

	public void setActivityForm(ActivityForm activityForm) {
		this.activityForm = activityForm;
	}

// ******************************************************************************************
	/**
	 * Create a new Activity.
	 */
	public void createActivity() {
		activityDao.createACtivity(activityForm);
		// reset le formulaire
		activityForm = new ActivityForm();
	}

	/**
	 * Hard delete an Activity from the database.
	 * 
	 * @param id (:Long)
	 */
	public void deleteActivity(Long id) {
		activityDao.deleteActivity(id);
	}

	/**
	 * Return the list of all the activivities created on the plateform by all
	 * manager.
	 * 
	 * @return the list of activities (:List<Activity>)
	 */
	public List<Activity> getAllActivities() {
		return activityDao.getAllActivities();
	}

	/**
	 * Update all parameter of an activity, from an update-form and persist it.
	 * 
	 * @param activityToUpdateId (:Long)
	 */
	public void updateActivity(Long activityToUpdateId) {
		Activity existingActivity = activityDao.getActivityById(activityToUpdateId);
		if (existingActivity != null) {
			existingActivity.setName(activityForm.getName());
			activityDao.updateActivity(existingActivity);
			activityForm = new ActivityForm();
		}
	}

}
