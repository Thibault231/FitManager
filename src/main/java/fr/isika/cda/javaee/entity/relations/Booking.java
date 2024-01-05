package fr.isika.cda.javaee.entity.relations;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.isika.cda.javaee.entity.users.User;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	private Long bookingId;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar registrationDate;

	@OneToOne
	private User coach;

	@OneToOne
	private User member;

	@OneToOne
	private ActivityTest activity;

//***********************************************	
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
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

	public ActivityTest getActivity() {
		return activity;
	}

	public void setActivity(ActivityTest activity) {
		this.activity = activity;
	}

}
