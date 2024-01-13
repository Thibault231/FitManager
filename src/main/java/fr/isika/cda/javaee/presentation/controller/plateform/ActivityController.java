package fr.isika.cda.javaee.presentation.controller.plateform;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.plateform.ActivityDao;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = -160397842934902381L;

	@Inject
	private IDaoActivity activityDao;

	private ActivityForm activityForm = new ActivityForm();

	public void createActivity() {
		activityDao.createACtivity(activityForm);
		// reset le formulaire
		activityForm = new ActivityForm();
	}

	public void updateActivity(Long id) {
		Activity existingActivity = activityDao.getActivityById(id);
		if (existingActivity != null) {
			existingActivity.setName(activityForm.getName());
			activityDao.updateActivity(existingActivity);
			activityForm = new ActivityForm();
		}
	}

	public void deleteActivity(Long id) {
		activityDao.deleteActivity(id);
	}

	public List<Activity> getAllActivities() {
		return activityDao.getAllActivities();
	}

	public ActivityForm getActivityForm() {
		return activityForm;
	}

	public void setActivityForm(ActivityForm activityForm) {
		this.activityForm = activityForm;
	}

}
