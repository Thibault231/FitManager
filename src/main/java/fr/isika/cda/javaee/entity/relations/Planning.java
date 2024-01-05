package fr.isika.cda.javaee.entity.relations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Planning {

	@Id
	@GeneratedValue
	private Long planningId;

	@OneToMany
	private List<ActivityTest> activitiesList = new ArrayList<ActivityTest>();

	public Long getPlanningId() {
		return planningId;
	}

	public void setPlanningId(Long planningId) {
		this.planningId = planningId;
	}

	public List<ActivityTest> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(List<ActivityTest> activitiesList) {
		this.activitiesList = activitiesList;
	}

}
