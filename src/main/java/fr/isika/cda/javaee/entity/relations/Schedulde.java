package fr.isika.cda.javaee.entity.relations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Object keeping all lessons of a user.
 * 
 * @author Thibault
 *
 */
@Entity
public class Schedulde {

	@Id
	@GeneratedValue
	private Long scheduldeId;

	@OneToMany
	private List<Booking> bookingsList;

//*******************************************************************	
	/**
	 * Empty constructor for JEE.
	 */
	public Schedulde() {
	}

	/**
	 * Constructor for Controller and Service
	 * 
	 * @param isActive (: boolean)
	 */
	public Schedulde(boolean isForViewModel) {
		this.bookingsList = new ArrayList<Booking>();
	}

//*******************************************************************	

	public Long getScheduldeId() {
		return scheduldeId;
	}

	public void setScheduldeId(Long scheduldeId) {
		this.scheduldeId = scheduldeId;
	}

	public List<Booking> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(List<Booking> bookingsList) {
		this.bookingsList = bookingsList;
	}

}
