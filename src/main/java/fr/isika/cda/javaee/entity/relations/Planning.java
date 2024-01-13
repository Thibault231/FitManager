package fr.isika.cda.javaee.entity.relations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.isika.cda.javaee.entity.plateform.Activity;

/**
 * Object keeping all lessons of the fitness space.
 * 
 * @author Charef
 *
 */
@Entity
public class Planning {

	@Id
	@GeneratedValue
	private Long planningId;

	@OneToMany
	private List<Activity> activitiesList = new ArrayList<Activity>();

//*******************************************************************
	/**
	 * Empty constructor for JEE.
	 */
	public Planning() {
	}

	/**
	 * Constructor for Controller and Service
	 * 
	 * @param isActive (: boolean)
	 */
	public Planning(boolean isForViewModel) {
		this.activitiesList = new ArrayList<Activity>();
	}

//*******************************************************************
	public Long getPlanningId() {
		return planningId;
	}

	public void setPlanningId(Long planningId) {
		this.planningId = planningId;
	}

	public List<Activity> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(List<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}

}
