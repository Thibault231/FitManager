package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;

@Named
@ViewScoped
public class ActivityController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -160397842934902381L;
	
	@Inject
	private ActivityDao activityDao;
	
	private ActivityForm activityForm = new ActivityForm();
	
	public void createActivity() {
		activityDao.createACtivity(activityForm);
		// reset le formulaire
		activityForm = new ActivityForm();
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
