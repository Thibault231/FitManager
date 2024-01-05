package fr.isika.cda.javaee.entity.relations;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.spaces.Space;

@Entity
public class Planning {

	@Id
	@GeneratedValue
	private Long planningId;

	@OneToOne
	private Space space;

	@OneToMany
	private List<ActivityTest> bookingsList;

	public Long getPlanningId() {
		return planningId;
	}

	public void setPlanningId(Long planningId) {
		this.planningId = planningId;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public List<ActivityTest> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(List<ActivityTest> bookingsList) {
		this.bookingsList = bookingsList;
	}

}
