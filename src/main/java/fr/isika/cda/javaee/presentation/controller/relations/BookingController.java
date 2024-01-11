package fr.isika.cda.javaee.presentation.controller.relations;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.plateform.IDaoCourse;
import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.controller.plateform.CourseController;
import fr.isika.cda.javaee.presentation.util.SessionUtils;

/**
 * Manage Booking objects in views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
@Named
@ViewScoped
public class BookingController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;

	@Inject
	private IBookingDao bookingDao;

	@Inject
	private IDaoUser userDao;

	@Inject
	private IDaoCourse courseDao;

	@Inject
	private CourseController courseController;

//***********************************************	

//***********************************************	
	public String createBooking() {
		// Get the current user object
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		// Get the current course object
		Long courseId = Long.valueOf(courseController.getEvent().getId());
		Course currentCourse = courseDao.getCourseById(courseId);
		// Create a new booking and link user and course inside.
		Booking bookingToCreate = new Booking(true);
		bookingToCreate.setMember(currentUser);
		bookingToCreate.setSpaceId(SessionUtils.getSpaceIdFromSession());
		bookingToCreate.setLinkedCourse(currentCourse);
		bookingToCreate.setRegistrationDate(new Date());
		// Persist booking
//		courseDao.saveCourses(currentCourse);
		bookingDao.createBooking(bookingToCreate);
		// Reset event
		courseController.resetEvent();
		return "BookingCalendar.xhtml?faces-redirect=true";
	}

	/**
	 * Create a new booking and link through it a course and a member.
	 */
	public String createBookingLinkedToMember() {
		// Get the current user object
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		// Get the current course object
		Course currentCourse = new Course();
		currentCourse.setName("BodyPump");
		// Create a new booking and link user and course inside.
		Booking bookingToCreate = new Booking(true);
		bookingToCreate.setMember(currentUser);
		bookingToCreate.setSpaceId(SessionUtils.getSpaceIdFromSession());
		bookingToCreate.setLinkedCourse(currentCourse);
		bookingToCreate.setRegistrationDate(new Date());
		// Persist booking
		courseDao.saveCourses(currentCourse);
		bookingDao.createBooking(bookingToCreate);
		return "BookingCalendar.xhtml?faces-redirect=true";
	}

	public String cancelBooking(Long bookingToCancelId) {
		bookingDao.cancelBooking(bookingToCancelId);
		return "AdherentDashboard?faces-redirect=true";
	}

	public List<Booking> getAllBookingOfAMember() {
		return bookingDao.getAllBookingsOfMember(SessionUtils.getUserIdFromSession());
	}
}
