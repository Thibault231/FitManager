package fr.isika.cda.javaee.entity.relations;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.users.User;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	private Long bookingId;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar registrationDate;

	@OneToOne
	private User member;

	@OneToOne
	private Course linkedCourse;

//***********************************************	
	public Booking() {

	}

	public Booking(boolean isForViewModel) {
		this.member = new User();
		this.linkedCourse = new Course();
	}

//***********************************************	
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

	public Calendar getBirthday() {
		return registrationDate;
	}

	public void setBirthday(Calendar birthday) {
		this.registrationDate = birthday;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Course getLinkedCourse() {
		return linkedCourse;
	}

	public void setLinkedCourse(Course linkedCourse) {
		this.linkedCourse = linkedCourse;
	}

}
