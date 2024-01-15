package fr.isika.cda.javaee.entity.relations;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.users.User;

/**
 * Booking of a lessons space by a member of the fitness.
 * 
 * @author Charef
 *
 */
@Entity
public class Booking {

	@Id
	@GeneratedValue
	private Long bookingId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	private Long spaceId;

	@OneToOne
	private User member;

	@OneToOne
	private Course linkedCourse;

//*******************************************************************	
	/**
	 * Empty constructor for JEE.
	 */
	public Booking() {
	}

	/**
	 * Constructor for Controller and Service
	 * 
	 * @param isActive (: boolean)
	 */
	public Booking(boolean isForViewModel) {
		this.linkedCourse = new Course();
		this.member = new User();
	}

//*******************************************************************	
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Date getBirthday() {
		return registrationDate;
	}

	public void setBirthday(Date birthday) {
		this.registrationDate = birthday;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Course getLinkedCourse() {
		return linkedCourse;
	}

	public void setLinkedCourse(Course linkedCourse) {
		this.linkedCourse = linkedCourse;
	}

	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

}
