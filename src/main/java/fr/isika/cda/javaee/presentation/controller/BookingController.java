package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoCourse;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.BookingForm;

@Named
@ViewScoped
public class BookingController implements Serializable {

	private static final long serialVersionUID = -1603978429342381L;

	@Inject
	private IBookingDao bookingDao;

	@Inject
	private IDaoUser userDao;

	@Inject
	private IDaoCourse courseDao;

	private BookingForm bookingForm = new BookingForm();

//***********************************************	

	@PostConstruct
	public void init() {
		this.bookingForm = new BookingForm();
	}

//***********************************************	
	public void createBooking() {

		Booking bookingToCreate = new Booking();

		bookingDao.createBooking(bookingToCreate);
	}

	/**
	 * Create a new booking and link through it a course and a member.
	 */
	public void createBookingLinkedToMember() {
		// Get the current user object
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		// Get the current course object
		Course newCourse = new Course();
		newCourse.setName("BodyPump");
		// Create a new booking and link user and course inside.
		Booking bookingToCreate = new Booking(true);
		bookingToCreate.setMember(currentUser);
		bookingToCreate.setSpaceId(SessionUtils.getSpaceIdFromSession());
		bookingToCreate.setLinkedCourse(newCourse);
		bookingToCreate.setRegistrationDate(new Date());
		// Persist booking
		courseDao.save(newCourse);
		bookingDao.createBooking(bookingToCreate);
	}

	public List<Booking> getAllBookingOfAMember() {
		return bookingDao.getAllBookingsOfMember(SessionUtils.getUserIdFromSession());
	}
}
