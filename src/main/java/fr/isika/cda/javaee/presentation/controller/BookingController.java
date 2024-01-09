package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoCourse;
import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.dao.relations.BookingDao;
import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.BookingForm;

@Named
@ViewScoped
public class BookingController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;

	@Inject
	private IBookingDao iBookingDao;
	
	@Inject
	private IDaoUser userDao;
	
	@Inject
	private IDaoCourse courseDao;

	
	@Inject
	private CourseController courseController;
	
	private BookingForm bookingForm = new BookingForm();

	public void createBooking() {
		Booking bookingToCreate = new Booking(true);
		iBookingDao.createBooking(bookingToCreate);
	}
	
	public void reserveCourse() {
		//1-On récupérer l'adherent et on le lie à la réservation
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		Booking bookingToCreate = new Booking(true);
		bookingToCreate.setMember(currentUser);
		//2-On récupère le cours et on le lie à la réservation
		Long courseId =  Long.valueOf(courseController.getEvent().getId());
		Course currentCourse = courseDao.getCourseById(courseId);
		bookingToCreate.setLinkedCourse(currentCourse);
		
		//3-On persiste la réservation
		iBookingDao.createBooking(bookingToCreate);
		
		courseController.resetEvent();
	}
}
